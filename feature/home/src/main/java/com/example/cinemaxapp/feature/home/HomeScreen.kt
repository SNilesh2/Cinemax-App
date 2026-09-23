package com.example.cinemaxapp.feature.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier,
        uiState = uiState,
        onCategorySelect = viewModel::onCategorySelected,
        onSearchQueryChange = viewModel::onSearchQueryChanged,
        onMovieClick = onMovieClick,
        onSeeAllClick = onSeeAllClick,
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onCategorySelect: (String) -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},
    onMovieClick: (String) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.dark)
    ) {
        when (uiState) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = CinemaxTheme.colors.blueAccent,
                )
            }
            is HomeUiState.Error -> {
                Text(
                    text = uiState.message,
                    color = CinemaxTheme.colors.white,
                    style = CinemaxTheme.typography.h4Medium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is HomeUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        //.padding(bottom = 100.dp) // Space for bottom bar
                ) {
                    Spacer(modifier = Modifier.height(52.dp))

                    // 1. Header Section
                    HomeHeader(
                        userName = uiState.userName,
                        avatarUrl = uiState.userAvatarUrl,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 2. Search Bar Section
                    HomeSearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = onSearchQueryChange,
                        onFilterClick = {},
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 3. Featured Movie Carousel & Indicators
                    if (uiState.featuredBanners.isNotEmpty()) {
                        FeaturedCarouselSection(
                            banners = uiState.featuredBanners,
                            onBannerClick = { onMovieClick(it.id) },
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // 4. Categories Section
                    CategoriesSection(
                        categories = uiState.categories,
                        selectedCategoryId = uiState.selectedCategoryId,
                        onCategorySelect = onCategorySelect,
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    // 5. Most Popular Section & Movie Cards
                    MostPopularSection(
                        movies = uiState.popularMovies,
                        onSeeAllClick = onSeeAllClick,
                        onMovieClick = { onMovieClick(it.id) },
                    )
                }
            }
        }
    }
}

// ============================================================
// 1. HEADER COMPONENT
// ============================================================

@Composable
private fun HomeHeader(
    userName: String,
    avatarUrl: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Circular User Profile Image
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(CinemaxTheme.colors.soft),
                contentAlignment = Alignment.Center,
            ) {
                if (avatarUrl.isNotEmpty()) {
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "User Avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Icon(
                        painter = painterResource(id = CinemaxIcons.Profile),
                        contentDescription = "User Profile",
                        tint = CinemaxTheme.colors.grey,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Hello, $userName",
                    style = CinemaxTheme.typography.h4SemiBold,
                    color = CinemaxTheme.colors.white,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Let's stream your favorite movie",
                    style = CinemaxTheme.typography.h6Medium,
                    color = CinemaxTheme.colors.grey,
                )
            }
        }
    }
}

// ============================================================
// 2. SEARCH BAR COMPONENT
// ============================================================

@Composable
private fun HomeSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    onSearchAction: () -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(52.dp),
        shape = RoundedCornerShape(24.dp),
        color = CinemaxTheme.colors.soft,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = CinemaxIcons.Search),
                contentDescription = "Search",
                tint = CinemaxTheme.colors.grey,
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = CinemaxTheme.typography.h5Regular.copy(
                    color = CinemaxTheme.colors.white
                ),
                cursorBrush = SolidColor(CinemaxTheme.colors.white),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchAction() }
                ),
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (query.isEmpty()) {
                            Text(
                                text = "Search a title..",
                                style = CinemaxTheme.typography.h5Regular,
                                color = CinemaxTheme.colors.grey,
                                maxLines = 1,
                            )
                        }
                        innerTextField()
                    }
                }
            )

            HorizontalDivider(
                modifier = Modifier
                    .height(20.dp)
                    .width(1.dp),
                color = CinemaxTheme.colors.grey.copy(alpha = 0.3f),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = CinemaxIcons.Filter),
                contentDescription = "Filter",
                tint = CinemaxTheme.colors.white,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onFilterClick() },
            )
        }
    }
}

// ============================================================
// 3 & 4. FEATURED MOVIE CAROUSEL COMPONENT
// ============================================================

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedMovieCarousel(
    banners: List<FeaturedBanner>,
    onBannerClick: (FeaturedBanner) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    FeaturedCarouselSection(
        banners = banners,
        onBannerClick = onBannerClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FeaturedCarouselSection(
    banners: List<FeaturedBanner>,
    onBannerClick: (FeaturedBanner) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { banners.size })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 36.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val banner = banners[page]

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onBannerClick(banner) }
            ) {
                // Banner Background Image
                AsyncImage(
                    model = banner.bannerImageUrl,
                    contentDescription = banner.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )

                // Dark Gradient Overlay for text contrast
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.85f)
                                ),
                                startY = 60f
                            )
                        )
                )

                // Title & Release Date Overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = banner.title,
                        style = CinemaxTheme.typography.h3SemiBold,
                        color = CinemaxTheme.colors.white,
                        maxLines = 2,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = banner.releaseDateText,
                        style = CinemaxTheme.typography.h6Medium,
                        color = CinemaxTheme.colors.whiteGrey,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Carousel Indicators
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(banners.size) { index ->
                val isSelected = pagerState.currentPage == index
                val width = if (isSelected) 24.dp else 6.dp
                val color by animateColorAsState(
                    targetValue = if (isSelected) CinemaxTheme.colors.blueAccent else CinemaxTheme.colors.blueAccent.copy(alpha = 0.3f),
                    label = "indicatorColor",
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .height(6.dp)
                        .width(width)
                        .clip(RoundedCornerShape(3.dp))
                        .background(color)
                )
            }
        }
    }
}

// ============================================================
// 5. CATEGORIES SECTION COMPONENT
// ============================================================

@Composable
private fun CategoriesSection(
    categories: List<MovieCategory>,
    selectedCategoryId: String,
    onCategorySelect: (String) -> Unit,
) {
    Column {
        Text(
            text = "Categories",
            style = CinemaxTheme.typography.h3SemiBold,
            color = CinemaxTheme.colors.white,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                val isSelected = category.id == selectedCategoryId

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) CinemaxTheme.colors.soft else Color.Transparent
                        )
                        .clickable { onCategorySelect(category.id) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = category.name,
                        style = CinemaxTheme.typography.h5Medium,
                        color = if (isSelected) CinemaxTheme.colors.blueAccent else CinemaxTheme.colors.whiteGrey
                    )
                }
            }
        }
    }
}

// ============================================================
// 6 & 7. MOST POPULAR MOVIE CARDS SECTION
// ============================================================

@Composable
private fun MostPopularSection(
    movies: List<Movie>,
    onSeeAllClick: () -> Unit,
    onMovieClick: (Movie) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Most popular",
                style = CinemaxTheme.typography.h3SemiBold,
                color = CinemaxTheme.colors.white,
            )

            Text(
                text = "See All",
                style = CinemaxTheme.typography.h5Medium,
                color = CinemaxTheme.colors.blueAccent,
                modifier = Modifier.clickable { onSeeAllClick() },
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(movies) { movie ->
                MoviePosterCard(
                    movie = movie,
                    onClick = { onMovieClick(movie) },
                )
            }
        }
    }
}

@Composable
private fun MoviePosterCard(
    movie: Movie,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(135.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .width(135.dp)
                .height(178.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(CinemaxTheme.colors.soft)
        ) {
            // Poster Image
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

            // Rating Badge (Top Right)
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                color = CinemaxTheme.colors.soft.copy(alpha = 0.85f),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = CinemaxIcons.Star),
                        contentDescription = "Rating",
                        tint = CinemaxTheme.colors.orange,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = movie.rating.toString(),
                        style = CinemaxTheme.typography.h6SemiBold,
                        color = CinemaxTheme.colors.orange,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = movie.title,
            style = CinemaxTheme.typography.h5SemiBold,
            color = CinemaxTheme.colors.white,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = movie.category,
            style = CinemaxTheme.typography.h6Medium,
            color = CinemaxTheme.colors.grey,
        )
    }
}

