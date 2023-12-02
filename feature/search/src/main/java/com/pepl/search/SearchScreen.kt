package com.pepl.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SearchRoute(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    SearchScreen(
        padding = padding,
        searchUiState = searchUiState,
    )
}

@Composable
private fun SearchScreen(
    padding: PaddingValues,
    searchUiState: SearchUiState,
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState)
            .padding(bottom = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "준비중입니다:)"
        )
    }
}