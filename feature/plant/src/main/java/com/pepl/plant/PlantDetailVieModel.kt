package com.pepl.plant

import androidx.lifecycle.ViewModel
import com.pepl.domain.usecase.GetLastGardenUseCase
import com.pepl.domain.usecase.GetPlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val getLastGardenUseCase: GetLastGardenUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _plantDetailUiState =
        MutableStateFlow<PlantDetailUiState>(PlantDetailUiState.Loading)
    val plantDetailUiState: StateFlow<PlantDetailUiState> = _plantDetailUiState.asStateFlow()

}