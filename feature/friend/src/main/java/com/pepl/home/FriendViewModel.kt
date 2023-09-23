//package com.pepl.home
//
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.SharedFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.flow
//
//@HiltViewModel
//class FriendViewModel @Inject constructor(
//    getFriendsUseCase: GetFriendsUseCase,
//) {
//
//    private val _errorFlow = MutableSharedFlow<Throwable>()
//    val errorFlow: SharedFlow<Throwable> get() = _errorFlow
//
//    val friendsUiState: StateFlow<FriendsUiState> = flow { emit(getFriendsUseCase()) }
//        .map { friends ->
//            if (friends.isNotEmpty()) {
//                FriendsUiState.Friends(friends)
//            } else {
//                FriendsUiState.Empty
//            }
//        }
//        .catch { throwable ->
//            _errorFlow.emit(throwable)
//        }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = FriendsUiState.Loading,
//        )
//}