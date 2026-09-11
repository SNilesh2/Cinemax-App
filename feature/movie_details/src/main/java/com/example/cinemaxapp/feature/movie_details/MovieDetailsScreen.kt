package com.example.cinemaxapp.feature.movie_details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.core.model.CreditPerson
import com.example.cinemaxapp.core.model.MovieDetails


// PUBLIC ENTRY POINT


@Composable
fun MovieDetailsScreen(
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is MovieDetailsUiState.Loading -> MovieDetailsLoadingContent()
        is MovieDetailsUiState.Error   -> MovieDetailsErrorContent(state.message)
        is MovieDetailsUiState.Success -> MovieDetailsContent(
            movieDetails = state.movieDetails,
            onBackClick  = { navController.popBackStack() },
        )
    }
}



// LOADING STATE

@Composable
private fun MovieDetailsLoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.dark),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = CinemaxTheme.colors.orange,
        )
    }
}


// ERROR STATE

@Composable
private fun MovieDetailsErrorContent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.dark),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text  = message,
            color = CinemaxTheme.colors.grey,
            style = CinemaxTheme.typography.h5Regular,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp),
        )
    }
}



// SUCCESS STATE — MAIN CONTENT

@Composable
private fun MovieDetailsContent(
    movieDetails: MovieDetails,
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.dark)
    ) {

        // ─────────────────────────────────────────────
        // Background movie poster
        // ─────────────────────────────────────────────
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetails.posterUrl.ifBlank { null })
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp)
        )

        // ─────────────────────────────────────────────
        // Dark gradient overlay
        // ─────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            CinemaxTheme.colors.dark.copy(alpha = 0.65f),
                            CinemaxTheme.colors.dark.copy(alpha = 0.90f),
                            CinemaxTheme.colors.dark
                        )
                    )
                )
        )

        // ─────────────────────────────────────────────
        // Existing Movie Details content
        // ─────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Spacer(
                modifier = Modifier.height(52.dp)
            )

            // Top bar
            MovieDetailsTopBar(
                title = movieDetails.title,
                onBackClick = onBackClick,
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Hero poster
            MovieDetailsPoster(
                posterUrl = movieDetails.posterUrl,
                title = movieDetails.title,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Metadata
            MovieMetadataRow(
                releaseYear = movieDetails.releaseYear,
                runtime = movieDetails.runtime,
                primaryGenre = movieDetails.genres.firstOrNull() ?: "",
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Rating
            RatingRow(
                rating = movieDetails.rating
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Action buttons
            ActionButtonsRow(
                homepage = movieDetails.homepage,
                context = context,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Story Line
            StoryLineSection(
                overview = movieDetails.overview,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Cast & Crew
            if (
                movieDetails.cast.isNotEmpty() ||
                movieDetails.crew.isNotEmpty()
            ) {
                CastAndCrewSection(
                    cast = movieDetails.cast,
                    crew = movieDetails.crew,
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}



// TOP BAR

@Composable
private fun MovieDetailsTopBar(
    title: String,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp ,top = 8.dp ,bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Back button
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CinemaxTheme.colors.soft)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(CinemaxIcons.BackArrow),
                contentDescription = "Back",
                tint = CinemaxTheme.colors.white,
            )
        }

        // Title
        Text(
            text     = title,
            style    = CinemaxTheme.typography.h4SemiBold,
            color    = CinemaxTheme.colors.white,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp),
        )

        // Favorite button (UI only — no backend)
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CinemaxTheme.colors.soft)
                .clickable { /* Future: toggle wishlist */ },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(CinemaxIcons.Heart),
                contentDescription = "Add to favourites",
                tint = CinemaxTheme.colors.white,
            )
        }
    }
}



// HERO POSTER

@Composable
private fun MovieDetailsPoster(
    posterUrl: String,
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 85.dp),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterUrl.ifBlank { null })
                .crossfade(true)
                .build(),
            contentDescription = title,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(12.dp)),
        )
    }
}



// METADATA ROW  — "2021  |  148 Minutes  |  Action"


@Composable
private fun MovieMetadataRow(
    releaseYear: String,
    runtime: String,
    primaryGenre: String,
) {
    Row(
        modifier            = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment   = Alignment.CenterVertically,
    ) {
        if (releaseYear.isNotBlank()) {
            MetadataItem(icon = CinemaxIcons.Calendar , text = releaseYear)
        }
        if (releaseYear.isNotBlank() && runtime.isNotBlank()) {
            MetadataDivider()
        }
        if (runtime.isNotBlank()) {
            MetadataItem(icon = CinemaxIcons.Clock, text = runtime)
        }
        if ((releaseYear.isNotBlank() || runtime.isNotBlank()) && primaryGenre.isNotBlank()) {
            MetadataDivider()
        }
        if (primaryGenre.isNotBlank()) {
            MetadataItem(icon = CinemaxIcons.Film , text = primaryGenre)
        }
    }
}

@Composable
private fun MetadataItem(icon : Int , text: String) {
    Icon(
        painter = painterResource(icon),
        contentDescription = text,
        tint = CinemaxTheme.colors.grey,
    )

    Spacer(modifier = Modifier.width(4.dp))

    Text(
        text  = text,
        style = CinemaxTheme.typography.h6Medium,
        color = CinemaxTheme.colors.grey,
    )
}

@Composable
private fun MetadataDivider() {
    Spacer(modifier = Modifier.width(8.dp))
    Text(
        text  = "|",
        style = CinemaxTheme.typography.h6Medium,
        color = CinemaxTheme.colors.grey,
    )
    Spacer(modifier = Modifier.width(12.dp))
}



// RATING ROW  — "★ 7.9"

@Composable
private fun RatingRow(rating: Double) {
    Row(
        modifier            = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment   = Alignment.CenterVertically,
    ) {
        Icon(
            painter            = painterResource(CinemaxIcons.Star),
            contentDescription = "Rating star",
            tint               = CinemaxTheme.colors.orange,
            modifier           = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text  = String.format("%.1f", rating),
            style = CinemaxTheme.typography.h5SemiBold,
            color = CinemaxTheme.colors.orange,
        )
    }
}



// ACTION BUTTONS ROW  —  [▶ Play]  [⬇]  [⬡]

@Composable
private fun ActionButtonsRow(
    homepage: String,
    context: Context,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 66.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment   = Alignment.CenterVertically,
    ) {
        // ── Play button (UI only — video not implemented) ─────────────────
        Button(
            onClick = { /* Future: open trailer/video */ },
            shape   = RoundedCornerShape(32.dp),
            colors  = ButtonDefaults.buttonColors(containerColor = CinemaxTheme.colors.orange),
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
        ) {
            Icon(
                imageVector        = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint               = CinemaxTheme.colors.white,
                modifier           = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text  = "Play",
                style = CinemaxTheme.typography.h5Medium,
                color = CinemaxTheme.colors.white,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // ── Download button (UI only) ─────────────────────────────────────
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(CinemaxTheme.colors.soft)
                .clickable { /* Future: download */ },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter            = painterResource(CinemaxIcons.Download),
                contentDescription = "Download",
                tint               = CinemaxTheme.colors.orange,
                modifier           = Modifier.size(24.dp),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // ── External link button ──────────────────────────────────────────
        // Enabled only when homepage is available.
        // Opens the TMDB `homepage` field in an external browser.
        val hasHomepage = homepage.isNotBlank()
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    if (hasHomepage) CinemaxTheme.colors.soft
                    else CinemaxTheme.colors.soft.copy(alpha = 0.4f),
                )
                .clickable(enabled = hasHomepage) {
                    openUrlExternally(context = context, url = homepage)
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(CinemaxIcons.External),
                contentDescription = if (hasHomepage) "Open official website" else "No website available",
                tint               = if (hasHomepage) CinemaxTheme.colors.blueAccent
                else CinemaxTheme.colors.grey,
                modifier           = Modifier.size(16.dp),
            )
        }
    }
}


private fun openUrlExternally(context: Context, url: String) {
    if (url.isBlank()) return

    try {
        val uri = Uri.parse(url)

        if (uri.scheme !in listOf("http", "https")) {
            Toast.makeText(
                context,
                "Invalid link",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No browser found to open the link", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Link unavailable", Toast.LENGTH_SHORT).show()
    }
}



// STORY LINE SECTION

@Composable
private fun StoryLineSection(overview: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        Text(
            text  = "Story Line",
            style = CinemaxTheme.typography.h3SemiBold,
            color = CinemaxTheme.colors.white,
        )

        Spacer(modifier = Modifier.height(8.dp))

        val displayedOverview = overview.ifBlank { "No overview available." }

        Text(
            text       = displayedOverview,
            style      = CinemaxTheme.typography.h5Regular,
            color      = CinemaxTheme.colors.grey,
            maxLines   = if (isExpanded) Int.MAX_VALUE else 5,
            overflow   = TextOverflow.Ellipsis,
        )

        if (overview.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text     = if (isExpanded) "Less" else "More",
                style    = CinemaxTheme.typography.h5Medium,
                color    = CinemaxTheme.colors.blueAccent,
                modifier = Modifier.clickable { isExpanded = !isExpanded },
            )
        }
    }
}



// CAST & CREW SECTION

@Composable
private fun CastAndCrewSection(
    cast: List<CreditPerson>,
    crew: List<CreditPerson>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text     = "Cast and Crew",
            style    = CinemaxTheme.typography.h3SemiBold,
            color    = CinemaxTheme.colors.white,
            modifier = Modifier.padding(horizontal = 24.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Combine cast first, then crew — in a single horizontal LazyRow
        val allCredits = cast + crew
        LazyRow(
            contentPadding    = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(allCredits, key = { it.id + "_" + it.role }) { person ->
                CreditPersonCard(person = person)
            }
        }
    }
}



// CREDIT PERSON CARD  — circular photo + name + role

@Composable
private fun CreditPersonCard(person: CreditPerson) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(40.dp)
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(person.profileUrl.ifBlank { null })
                .crossfade(true)
                .build(),
            contentDescription = person.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(CinemaxTheme.colors.soft),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = person.name,
                style = CinemaxTheme.typography.h5SemiBold,
                color = CinemaxTheme.colors.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            if (person.role.isNotBlank()) {
                Text(
                    text = person.role,
                    style = CinemaxTheme.typography.h7Medium,
                    color = CinemaxTheme.colors.grey,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
