//package com.pepl.home
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.padding
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.flow.collectLatest
//
//@Composable
//internal fun FriendRoute(
//    padding: PaddingValues,
//    onSessionClick: () -> Unit,
//    onContributorClick: () -> Unit,
//    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
//    viewModel: FriendViewModel = hiltViewModel(),
//) {
//    val sponsorUitState by viewModel.sponsorUiState.collectAsStateWithLifecycle()
//
//    LaunchedEffect(true) {
//        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
//    }
//
//    FriendScreen(
//        padding = padding,
//        sponsorUitState = sponsorUitState,
//        onSessionClick = onSessionClick,
//        onContributorClick = onContributorClick,
//    )
//}
//
//@Composable
//private fun FriendScreen(
//    padding: PaddingValues,
//    sponsorUiState: SponsorsUiState,
//    onSessionClick: () -> Unit,
//    onContributorClick: () -> Unit,
//) {
//    val scrollState = rememberScrollState()
//    Column(
//        Modifier
//            .padding(padding)
//            .padding(horizontal = 8.dp)
//            .verticalScroll(scrollState)
//            .padding(bottom = 4.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//    ) {
//        SessionCard(onClick = onSessionClick)
//        ContributorCard(onClick = onContributorClick)
//        SponsorCard(uiState = sponsorsUiState)
//    }
//}