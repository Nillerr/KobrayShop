package dk.nillerr.kobray.samples.kobrayshop.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()

    val isLoggingIn by viewModel.isLoggingIn.collectAsState()

    LoginContent(
        username = username,
        onUsernameChange = { viewModel.username.value = it },
        password = password, isLoggingIn = isLoggingIn,
        onPasswordChange = { viewModel.password.value = it },
        onSubmit = { viewModel.login() }
    )
}

@Composable
fun LoginContent(
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isLoggingIn: Boolean,
    onSubmit: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            enabled = !isLoggingIn,
            label = { Text("Username") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            enabled = !isLoggingIn,
            label = { Text("Password") }
        )
        OutlinedButton(
            onClick = onSubmit,
            enabled = !isLoggingIn,
            content = { Text("Sign in") }
        )
    }
}