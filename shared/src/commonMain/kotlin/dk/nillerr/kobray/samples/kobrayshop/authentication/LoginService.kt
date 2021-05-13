package dk.nillerr.kobray.samples.kobrayshop.authentication

import kotlinx.coroutines.delay

class LoginService {
    suspend fun login(username: String, password: String) {
        delay(2000)
    }
}