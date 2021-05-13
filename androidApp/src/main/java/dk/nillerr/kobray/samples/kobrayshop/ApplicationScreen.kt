package dk.nillerr.kobray.samples.kobrayshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dk.nillerr.kobray.samples.kobrayshop.authentication.LoginScreen
import dk.nillerr.kobray.samples.kobrayshop.session.SessionScreen

@Composable
fun ApplicationScreen(viewModel: ApplicationViewModel) {
    val state by viewModel.state.collectAsState()

    val currentState = state
    when (currentState) {
        is ApplicationState.LoggedOut -> LoginScreen(viewModel = currentState.viewModel)
        is ApplicationState.LoggedIn -> SessionScreen(viewModel = currentState.viewModel)
    }
}