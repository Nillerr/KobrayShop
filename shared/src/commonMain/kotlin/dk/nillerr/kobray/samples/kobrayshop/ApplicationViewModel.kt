package dk.nillerr.kobray.samples.kobrayshop

import dk.nillerr.kobray.AnyMutableStateFlow
import dk.nillerr.kobray.ObservableObject
import dk.nillerr.kobray.ViewModel
import dk.nillerr.kobray.samples.kobrayshop.authentication.LoginViewModel
import dk.nillerr.kobray.samples.kobrayshop.session.SessionViewModel
import dk.nillerr.kobray.samples.kobrayshop.session.User
import dk.nillerr.kobray.toAnyStateFlow

@ObservableObject
class ApplicationViewModel(
    private val loginViewModelFactory: () -> LoginViewModel,
    private val sessionViewModelFactory: (User) -> SessionViewModel
) : ViewModel() {

    private val _state = AnyMutableStateFlow<ApplicationState>(ApplicationState.LoggedOut(loginViewModelFactory()))
    val state = _state.toAnyStateFlow()

    fun logout() {
        _state.value = ApplicationState.LoggedOut(loginViewModelFactory())
    }

    fun startSession(user: User) {
        _state.value = ApplicationState.LoggedIn(sessionViewModelFactory(user))
    }

}

fun ApplicationViewModel() = ApplicationViewModel(
    loginViewModelFactory = { LoginViewModel() },
    sessionViewModelFactory = { SessionViewModel(it) }
)