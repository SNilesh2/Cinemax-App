package com.example.cinemaxapp.feature.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.core.model.Movie
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape

// PUBLIC ENTRY POINT

@Composable
fun WishlistScreen(
    onBackClick: () -> Unit,
    onMovieClick: (String) -> Unit,
    viewModel: WishlistViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WishlistContent(
        uiState      = uiState,
        onBackClick  = onBackClick,
        onMovieClick = onMovieClick,
        onRemoveClick = { movieId ->
            viewModel.onRemoveFromWishlist(movieId.toIntOrNull() ?: return@WishlistContent)
        },
    )
}


// ROOT CONTENT — dispatches to state-specific screens


@Composable
private fun WishlistContent(
    uiState: WishlistUiState,
    onBackClick: () -> Unit,
    onMovieClick: (String) -> Unit,
    onRemoveClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.dark),
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        // Top bar — always visible regardless of state
        WishlistTopBar(onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(24.dp))

        // State-specific content
        when (uiState) {
            is WishlistUiState.Loading -> WishlistLoadingContent()
            is WishlistUiState.Empty   -> WishlistEmptyContent()
            is WishlistUiState.Error   -> WishlistErrorContent(uiState.message)
            is WishlistUiState.Success -> WishlistMovieList(
                movies        = uiState.movies,
                onMovieClick  = onMovieClick,
                onRemoveClick = onRemoveClick,
            )
        }
    }
}


// TOP BAR

@Composable
private fun WishlistTopBar(
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment    = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // Back button — soft background rounded box (matches MovieDetailsTopBar style)
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CinemaxTheme.colors.soft)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter            = painterResource(CinemaxIcons.BackArrow),
                contentDescription = "Back",
                tint               = CinemaxTheme.colors.white,
            )
        }

        // Screen title
        Text(
            text      = "Wishlist",
            style     = CinemaxTheme.typography.h4SemiBold,
            color     = CinemaxTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier  = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
        )

        // Invisible spacer to keep title centered
        Spacer(modifier = Modifier.size(32.dp))
    }
}


// LOADING STATE

@Composable
private fun WishlistLoadingContent() {
    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = CinemaxTheme.colors.blueAccent,
        )
    }
}


// EMPTY STATE — "There Is No Movie Yet!"

@Composable
private fun WishlistEmptyContent() {
    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier            = Modifier.padding(horizontal = 62.dp),
        ) {
            // Empty box icon (using an available icon or placeholder)
            Image(
                painter            = painterResource(id = CinemaxIcons.Empty),
                contentDescription = "Empty",
                //tint               = CinemaxTheme.colors.blueAccent,
                modifier           = Modifier.size(76.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text      = "There Is No Movie Yet!",
                style     = CinemaxTheme.typography.h3SemiBold,
                color     = CinemaxTheme.colors.white,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text      = "Find your movie by Type title,\ncategories, years, etc",
                style     = CinemaxTheme.typography.h5Medium,
                color     = CinemaxTheme.colors.grey,
                textAlign = TextAlign.Center,
            )
        }
    }
}


// ERROR STATE

@Composable
private fun WishlistErrorContent(message: String) {
    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text     = message,
            color    = CinemaxTheme.colors.grey,
            style    = CinemaxTheme.typography.h5Regular,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp),
        )
    }
}


// SUCCESS STATE — LIST OF WISHLISTED MOVIES

@Composable
private fun WishlistMovieList(
    movies:        List<Movie>,
    onMovieClick:  (String) -> Unit,
    onRemoveClick: (String) -> Unit,
) {
    LazyColumn(
        modifier              = Modifier.fillMaxSize(),
        verticalArrangement   = Arrangement.spacedBy(16.dp),
        contentPadding        = PaddingValues(
            start  = 24.dp,
            end    = 24.dp,
            bottom = 24.dp,
        ),
    ) {
        items(
            items = movies,
            key   = { it.id },
        ) { movie ->
            WishlistMovieCard(
                movie         = movie,
                onMovieClick  = { onMovieClick(movie.id) },
                onRemoveClick = { onRemoveClick(movie.id) },
            )
        }
    }
}


// MOVIE CARD — matches Figma reference design

@Composable
private fun WishlistMovieCard(
    movie:         Movie,
    onMovieClick:  () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CinemaxTheme.colors.soft)
            .clickable { onMovieClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // ── Poster thumbnail with play icon overlay ─────────────
        Box(
            modifier = Modifier
                .width(121.dp)
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(8.dp))
                .background(CinemaxTheme.colors.dark),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model              = movie.posterUrl.ifBlank { null },
                contentDescription = movie.title,
                contentScale       = ContentScale.Crop,
                modifier           = Modifier.fillMaxSize(),
            )

            // Play icon overlay
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(CinemaxTheme.colors.white.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter            = painterResource(CinemaxIcons.Play), // placeholder — reused as shape
                    contentDescription = "Play",
                    tint               = CinemaxTheme.colors.white,
                    modifier           = Modifier.size(24.dp),
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // ── Movie info column ────────────────────────────────────
        Column(
            modifier = Modifier.weight(1f),
        ) {
            // Genre
            Text(
                text     = movie.category,
                style    = CinemaxTheme.typography.h6Medium,
                color    = CinemaxTheme.colors.whiteGrey,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Title
            Text(
                text     = movie.title,
                style    = CinemaxTheme.typography.h5SemiBold,
                color    = CinemaxTheme.colors.white,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Bottom info row: Format + Rating + Remove Heart
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier              = Modifier.fillMaxWidth(),
            ) {
                // Format label
                Text(
                    text  = "Movie",
                    style = CinemaxTheme.typography.h6Medium,
                    color = CinemaxTheme.colors.grey,
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Star + Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter            = painterResource(CinemaxIcons.Star),
                        contentDescription = "Star",
                        tint               = CinemaxTheme.colors.orange,
                        modifier           = Modifier.size(14.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text  = String.format("%.1f", movie.rating),
                        style = CinemaxTheme.typography.h6Medium,
                        color = CinemaxTheme.colors.orange,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Remove from Wishlist (heart button — always red, always filled)
                IconButton(
                    onClick  = onRemoveClick,
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        painter            = painterResource(CinemaxIcons.Heart),
                        contentDescription = "Remove from Wishlist",
                        tint               = CinemaxTheme.colors.red,
                        modifier           = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}
