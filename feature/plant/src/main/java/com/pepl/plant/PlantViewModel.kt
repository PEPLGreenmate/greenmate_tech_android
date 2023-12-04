package com.pepl.plant

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepl.domain.usecase.GetLastGardenUseCase
import com.pepl.domain.usecase.GetPlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlantViewModel @Inject constructor(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val getLastGardenUseCase: GetLastGardenUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _plantUiState =
        MutableStateFlow<PlantUiState>(PlantUiState.Loading)
    val plantUiState: StateFlow<PlantUiState> = _plantUiState.asStateFlow()

    fun getPlants(){
        viewModelScope.launch {
            getLastGardenUseCase().flatMapLatest { lastGardenId ->
                flow { emit(getPlantsUseCase(lastGardenId)) }
                    .flatMapLatest { plants ->
                        if (lastGardenId.isEmpty()) {
                            flowOf(PlantUiState.Empty)
                        } else if (plants.isEmpty()) {
                            flowOf(
                                PlantUiState.GardenEmpty(
                                    gardenId = lastGardenId
                                )
                            )
                        } else {
                            flowOf(
                                PlantUiState.Plants(
                                    gardenId = lastGardenId,
                                    plants = plants
                                )
                            )
                        }
                    }
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { combinedUiState ->
                _plantUiState.value = combinedUiState
            }
        }
    }
}