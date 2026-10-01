package com.example.cinemaxapp.feature.search

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.cinemaxapp.feature.search.SearchViewModel
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.SearchPerson

// ════════════════════════════════════════════════════════════════════════════
// PUBLIC ENTRY POINT
// ════════════════════════════════════════════════════════════════════════════


@Composable
fun SearchScreen(
    onMovieClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState  by viewModel.uiState.collectAsStateWithLifecycle()
    val inputQuery by viewModel.inputQuery.collectAsStateWithLifecycle()

    SearchContent(
        uiState       = uiState,
        inputQuery    = inputQuery,
        onQueryChange = viewModel::onInputQueryChange,
        onSearch      = viewModel::onSearch,
        onClear       = viewModel::onClear,
        onMovieClick  = onMovieClick,
    )
}

// ════════════════════════════════════════════════════════════════════════════
// ROOT CONTENT
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun SearchContent(
    uiState: SearchUiState,
    inputQuery: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onMovieClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.dark),
    ) {
        // ── Search Bar ────────────────────────────────────────────

        Spacer(
            modifier = Modifier
                .height(52.dp)
        )


        SearchBar(
            query     = inputQuery,
            onQueryChange = onQueryChange,
            onSearch  = onSearch,
            onCancel  = onClear,
            modifier  = Modifier.padding(horizontal = 24.dp),
        )

        Spacer(
            modifier = Modifier
                .height(32.dp)
        )

        // ── Body —
        when (uiState) {
            is SearchUiState.Idle -> {
                // Empty screen — nothing shown below the search bar
            }

            is SearchUiState.Results -> {
                val lazyMovies = uiState.moviesFlow.collectAsLazyPagingItems()
                SearchResultsBody(
                    persons      = uiState.persons,
                    lazyMovies   = lazyMovies,
                    onMovieClick = onMovieClick,
                )
            }

            is SearchUiState.NoResults -> {
                NoResultsContent()
            }
        }
    }
}

// ════════════════════════════════════════════════════════════════════════════
// SEARCH BAR
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier          = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextField(
            value         = query,
            onValueChange = onQueryChange,
            modifier      = Modifier
                .weight(1f)
                .height(52.dp),
            placeholder = {
                Text(
                    text  = "Search",
                    style = CinemaxTheme.typography.h5Regular,
                    color = CinemaxTheme.colors.grey,
                )
            },
            leadingIcon = {
                Icon(
                    painter           = painterResource(CinemaxIcons.Search),
                    contentDescription = "Search icon",
                    tint               = CinemaxTheme.colors.grey,
                    modifier           = Modifier.size(16.dp),
                )
            },
            singleLine    = true,
            shape         = RoundedCornerShape(24.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onSearch(query)
                },
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor   = CinemaxTheme.colors.soft,
                unfocusedContainerColor = CinemaxTheme.colors.soft,
                focusedIndicatorColor   = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor  = Color.Transparent,
                focusedTextColor        = CinemaxTheme.colors.white,
                unfocusedTextColor      = CinemaxTheme.colors.white,
                cursorColor             = CinemaxTheme.colors.blueAccent,
            ),
            textStyle = CinemaxTheme.typography.h5Regular,
        )

        Text(
            text      = "Cancel",
            style     = CinemaxTheme.typography.h5Medium,
            color     = CinemaxTheme.colors.blueAccent,
            modifier  = Modifier.clickable {
                focusManager.clearFocus()
                onCancel()
            },
        )
    }
}

// ════════════════════════════════════════════════════════════════════════════
// RESULTS BODY  (Figma design 1 — actors + movies; Figma design 2 — movies only)
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun SearchResultsBody(
    persons: List<SearchPerson>,
    lazyMovies: LazyPagingItems<Movie>,
    onMovieClick: (String) -> Unit,
) {
    // Determine if both sections are truly empty (for NoResults rendering)
    val moviesEmpty = lazyMovies.loadState.refresh !is LoadState.Loading && lazyMovies.itemCount == 0
    val bothEmpty   = moviesEmpty && persons.isEmpty()

    if (bothEmpty && lazyMovies.loadState.refresh !is LoadState.Loading) {
        NoResultsContent()
        return
    }

    LazyColumn(
        modifier      = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {

        // ── Actors row (shown only when persons exist) ─────────────────
        if (persons.isNotEmpty()) {
            item(key = "actors_header") {
                SectionHeader(
                    title    = "Actors",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                )
            }

            item(key = "actors_row") {
                LazyRow(
                    contentPadding       = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(items = persons, key = { it.id }) { person ->
                        PersonCard(person = person)
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
        }

        // ── Movie Related header ────────────────────────────────────────
        if (lazyMovies.itemCount > 0 || lazyMovies.loadState.refresh is LoadState.Loading) {
            item(key = "movies_header") {
                Row(
                    modifier              = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically,
                ) {
                    SectionHeader(title = "Movie Related")
                    Text(
                        text  = "See All",
                        style = CinemaxTheme.typography.h5Medium,
                        color = CinemaxTheme.colors.blueAccent,
                    )
                }
            }
        }

        // ── Movie cards (Paging 3) ──────────────────────────────────────
        items(count = lazyMovies.itemCount, key = { lazyMovies[it]?.id ?: it }) { index ->
            val movie = lazyMovies[index] ?: return@items
            SearchMovieCard(
                movie      = movie,
                modifier   = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                onClick    = { onMovieClick(movie.id) },
            )
        }

        // Loading spinner while first page loads
        item {
            if (lazyMovies.loadState.refresh is LoadState.Loading) {
                Box(
                    modifier       = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = CinemaxTheme.colors.blueAccent)
                }
            }
        }

        // Footer spinner while appending next page
        item {
            if (lazyMovies.loadState.append is LoadState.Loading) {
                Box(
                    modifier        = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color    = CinemaxTheme.colors.blueAccent,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

// ════════════════════════════════════════════════════════════════════════════
// PERSON CARD — circular image + name below
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun PersonCard(
    person: SearchPerson,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier          = modifier.width(82.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model              = person.profileUrl.ifBlank { null },
            contentDescription = person.name,
            contentScale       = ContentScale.Crop,
            placeholder        = painterResource(CinemaxIcons.Profile),
            error              = painterResource(CinemaxIcons.Profile),
            modifier           = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(CinemaxTheme.colors.soft),
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = person.name,
            style     = CinemaxTheme.typography.h6SemiBold,
            color     = CinemaxTheme.colors.white,
            maxLines  = 1,
            overflow  = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }
}

// ════════════════════════════════════════════════════════════════════════════
// SEARCH MOVIE CARD — horizontal card matching Figma layout
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun SearchMovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier          = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // ── Poster ─────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .width(112.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp))
                .background(CinemaxTheme.colors.soft),
        ) {
            AsyncImage(
                model              = movie.posterUrl.ifBlank { null },
                contentDescription = movie.title,
                contentScale       = ContentScale.Crop,
                modifier           = Modifier.fillMaxSize(),
            )

            // Star rating badge (bottom-left)
            RatingBadge(
                rating   = movie.rating,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(6.dp),
            )
        }

        // ── Info panel ─────────────────────────────────────────────────
        Column(
            modifier            = Modifier
                .weight(1f)
                .padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            // Title
            Text(
                text     = movie.title,
                style    = CinemaxTheme.typography.h4SemiBold,
                color    = CinemaxTheme.colors.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            // Year
            if (movie.releaseDate.isNotBlank()) {
                MetaRow(
                    iconRes = CinemaxIcons.Calendar,
                    label   = movie.releaseDate.take(4),
                )
            }

            // Runtime (shows exact runtime if available in Room, else "- Minutes")
            val runtimeText = movie.runtime?.let { if (it > 0) "$it Minutes" else null } ?: "- Minutes"
            MetaRow(
                iconRes = CinemaxIcons.Clock,
                label   = runtimeText,
            )

            // Category | Movie (e.g. "Action | Movie", or "Movie" if no genre available)
            val typeText = if (movie.category.isNotBlank()) "${movie.category} | Movie" else "Movie"
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment     = Alignment.CenterVertically,
            ) {
                Icon(
                    painter           = painterResource(CinemaxIcons.Film),
                    contentDescription = null,
                    tint               = CinemaxTheme.colors.grey,
                    modifier           = Modifier.size(14.dp),
                )
                Text(
                    text  = typeText,
                    style = CinemaxTheme.typography.h6Regular,
                    color = CinemaxTheme.colors.grey,
                )
            }
        }
    }
}

// ════════════════════════════════════════════════════════════════════════════
// NO RESULTS (Figma design 3)
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun NoResultsContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier              = modifier
            .fillMaxSize()
            .padding(horizontal = 62.dp),
        verticalArrangement   = Arrangement.Center,
        horizontalAlignment   = Alignment.CenterHorizontally,
    ) {
        // Magnifying glass illustration using icon + accent styling
        Box(
            modifier           = Modifier
                .size(76.dp),
            contentAlignment   = Alignment.Center,
        ) {
            Icon(
                painter           = painterResource(CinemaxIcons.SearchCloud),
                contentDescription = "cloud search",
                tint               = Color.Unspecified,
                modifier           = Modifier.size(76.dp),
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text      = "We Are Sorry, We Can\nNot Find The Movie :(",
            style     = CinemaxTheme.typography.h3SemiBold,
            color     = CinemaxTheme.colors.whiteGrey,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text      = "Find your movie by Type title,\ncategories, years, etc",
            style     = CinemaxTheme.typography.h6Medium,
            color     = CinemaxTheme.colors.grey,
            textAlign = TextAlign.Center,
        )
    }
}

// ════════════════════════════════════════════════════════════════════════════
// SHARED SUB-COMPOSABLES
// ════════════════════════════════════════════════════════════════════════════

@Composable
private fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text     = title,
        style    = CinemaxTheme.typography.h3SemiBold,
        color    = CinemaxTheme.colors.white,
        modifier = modifier,
    )
}


@Composable
private fun RatingBadge(
    rating: Double,
    modifier: Modifier = Modifier,
) {
    if (rating <= 0.0) return
    Row(
        modifier          = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.3f))
            .padding(horizontal = 6.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Icon(
            painter           = painterResource(CinemaxIcons.Star),
            contentDescription = "rating",
            tint               = CinemaxTheme.colors.orange,
            modifier           = Modifier.size(16.dp),
        )
        Text(
            text  = String.format("%.1f", rating / 2.0),
            style = CinemaxTheme.typography.h6SemiBold,
            color = CinemaxTheme.colors.orange,
        )
    }
}


@Composable
private fun MetaRow(
    iconRes: Int,
    label: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier          = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter           = painterResource(iconRes),
            contentDescription = null,
            tint               = CinemaxTheme.colors.grey,
            modifier           = Modifier.size(14.dp),
        )
        Text(
            text  = label,
            style = CinemaxTheme.typography.h6Regular,
            color = CinemaxTheme.colors.grey,
        )
    }
}
