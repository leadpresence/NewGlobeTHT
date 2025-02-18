package com.leadpresence.newglobetht.presentation.ui.pupils
//
//
import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.presentation.ui.PupilViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

@Composable
fun PupilsScreen(
    viewModel: PupilViewModel = koinViewModel()
) {
    val pupilItems = viewModel.pupilPagingFlow.collectAsLazyPagingItems()

    // Handle refresh and initial load
    val pullRefreshState = rememberPullRefreshState(
        refreshing = pupilItems.loadState.refresh is LoadState.Loading,
        onRefresh = { pupilItems.refresh() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
//            pullToRefresh(pupilItems.loadState.refresh is LoadState.Loading)
//            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = pupilItems.itemCount,
                key = { index -> pupilItems[index]?.pupilId ?: index }
            ) { index ->
                pupilItems[index]?.let { pupil ->
                    PupilListItem(
                        pupil = pupil,
                        modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null)
                    )
                }
            }

            // Handle loading states
            pupilItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val error = loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = error.error.localizedMessage ?: "Error loading items",
                                onRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val error = loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = error.error.localizedMessage ?: "Error loading more items",
                                onRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }

        // Pull refresh indicator


//        PullToRefreshBox(
//            onRefresh = {},
//            isRefreshing = pupilItems.loadState.refresh is LoadState.Loading,
//            modifier = Modifier.align(Alignment.TopCenter),
//            content = {
//                Indicator(
//                    modifier = Modifier.align(Alignment.TopCenter),
//                    isRefreshing =  pupilItems.loadState.refresh is LoadState.Loading,,
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    color = MaterialTheme.colorScheme.onPrimaryContainer,
//                    state = pullRefreshState
//                )
//            },
//        )
    }
}

@Composable
fun PupilListItem(pupil: Pupil, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = pupil.image,
                contentDescription = "Pupil image",
                modifier = Modifier.size(64.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pupil.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pupil.country,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun rememberPullRefreshState(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    refreshThreshold: Dp = 80.dp
): PullRefreshState {
    // Convert dp threshold to pixels
    val density = LocalDensity.current
    val refreshThresholdPx = with(density) { refreshThreshold.toPx() }

    // Remember pull state
    val state = remember {
        PullRefreshState(
            refreshThresholdPx = refreshThresholdPx,
            onRefresh = onRefresh
        )
    }

    // Update state values when they change
    LaunchedEffect(refreshing) {
        state.refreshing = refreshing
    }

    LaunchedEffect(refreshThresholdPx) {
        state.refreshThresholdPx = refreshThresholdPx
    }

    LaunchedEffect(onRefresh) {
        state.onRefresh = onRefresh
    }

    return state
}
// State class to hold pull refresh values
class PullRefreshState(
    refreshThresholdPx: Float,
    onRefresh: () -> Unit
) {
    var refreshing by mutableStateOf(false)
    var progress by mutableFloatStateOf(0f)
    var refreshThresholdPx by mutableFloatStateOf(refreshThresholdPx)
    var onRefresh by mutableStateOf(onRefresh)

    // Handle drag gesture
    suspend fun onPull(pullDelta: Float) {
        if (refreshing) return

        progress = (progress + pullDelta).coerceIn(0f, 1f)
    }

    // Handle release
    suspend fun onRelease() {
        if (refreshing) return

        if (progress >= 1f) {
            refreshing = true
            onRefresh()
        }
        progress = 0f
    }
}
//@Composable
//fun PupilsScreen(
////    viewModel: PupilViewModel,
//    viewModel: PupilViewModel = koinViewModel()
////    onPupilClick: (Long) -> Unit,
////    onBackClick: () -> Unit
//
//) {
//
//    val pupils = viewModel.pupils.collectAsLazyPagingItems()
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Pupils${     pupils.itemCount}") },
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        },
//    ) {
//        LazyColumn {
//
//            items(
//                count = pupils.itemCount,
//                key = { index ->
//                    val post = pupils[index]
//                    post?.pupilId ?: index
//                }
//            ) { index ->
//                val pupil = pupils[index]
//
//                if (pupil != null) {
//                    PupilItem(pupil = pupil)
//                }
//            }
//        }
//    }
//
////    val uiState by viewModel.uiState.collectAsState()
//////    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
////    val pupils = viewModel.pupils.collectAsLazyPagingItems()
////
////
////    Box(modifier = Modifier.fillMaxSize()) {
////
////        when (uiState) {
////            is UiState.Initial -> {
////                // Show nothing or initial state
////            }
////            is UiState.Loading -> {
////                CircularProgressIndicator(
////                    modifier = Modifier.align(Alignment.Center)
////                )
////            }
////            is UiState.Success -> {
////                LazyColumn {
////                    item {
////                        ScreenHeader(
////                            title = "Pupils",
////                            subtitle = "Registered pupils",
////                            onBackClick = onBackClick,
////                            modifier = Modifier.fillMaxWidth()
////                        )
////                    }
////                    items(
////                        count = pupils.itemCount,
////                        key = { index ->
////                            val pupil = pupils[index]
////                            pupil?.id ?: index
////                        }
////                    ) { index ->
////                        val pupil = pupils[index]
////                        pupil?.let {
////                            PupilItem(
////                                pupil = it,
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .padding(16.dp)
////                            )
////                        }
////                    }
////
////                    // Add loading footer
////                    when (pupils.loadState.append) {
////                        is LoadState.Loading -> {
////                            item { LoadingFooter() }
////                        }
////                        is LoadState.Error -> {
////                            item {
////                                ErrorFooter(
////                                    onRetry = { pupils.retry() }
////                                )
////                            }
////                        }
////                        else -> {}
////                    }
////                }
////            }
////            is UiState.Error -> {
////                ErrorMessage(
////                    message = (uiState as UiState.Error).message,
////                    onRetry = { viewModel.retry() },
////                    modifier = Modifier.align(Alignment.Center)
////                )
////            }
////        }
//
//
//
////    }
//
//}
//
//@Composable
//private fun PupilItem(
//    pupil: Pupil,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier,
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text(
//                text = pupil.name,
//                style = MaterialTheme.typography.titleMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            // Add other pupil details here
//        }
//    }
//}
//
//@Composable
//private fun LoadingFooter() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        CircularProgressIndicator(
//            modifier = Modifier.align(Alignment.Center)
//        )
//    }
//}
//
//@Composable
//private fun ErrorFooter(
//    onRetry: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Error loading more items",
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.error
//        )
//        Button(
//            onClick = onRetry,
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Text("Retry")
//        }
//    }
//}
//
//@Composable
//private fun ErrorMessage(
//    message: String,
//    onRetry: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier.padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = message,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.error,
//            textAlign = TextAlign.Center
//        )
//        Button(
//            onClick = onRetry,
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text("Retry")
//        }
//    }
//}