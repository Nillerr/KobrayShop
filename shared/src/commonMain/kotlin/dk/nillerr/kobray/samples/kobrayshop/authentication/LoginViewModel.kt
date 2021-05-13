package dk.nillerr.kobray.samples.kobrayshop.authentication

import dk.nillerr.kobray.*

@ObservableObject
class LoginViewModel(private val loginService: LoginService) : ViewModel() {

    val username = AnyMutableStateFlow("")
    val password = AnyMutableStateFlow("")

    private var _isLoggingIn = AnyMutableStateFlow(false)
    val isLoggingIn = _isLoggingIn.toAnyStateFlow()

    fun login() = launch {
        _isLoggingIn.value = true

        try {
            loginService.login(username.value, password.value)
        } finally {
            _isLoggingIn.value = false
        }
    }

}

fun LoginViewModel() = LoginViewModel(loginService = LoginService())