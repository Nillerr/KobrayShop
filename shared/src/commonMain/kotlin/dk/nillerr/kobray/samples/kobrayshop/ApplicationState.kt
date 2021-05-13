package dk.nillerr.kobray.samples.kobrayshop

import dk.nillerr.kobray.samples.kobrayshop.authentication.LoginViewModel
import dk.nillerr.kobray.samples.kobrayshop.session.SessionViewModel

sealed class ApplicationState {
    data class LoggedOut(val viewModel: LoginViewModel) : ApplicationState()
    data class LoggedIn(val viewModel: SessionViewModel) : ApplicationState()
}