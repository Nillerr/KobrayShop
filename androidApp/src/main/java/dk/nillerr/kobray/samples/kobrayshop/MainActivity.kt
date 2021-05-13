package dk.nillerr.kobray.samples.kobrayshop

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dk.nillerr.kobray.samples.kobrayshop.androidx.viewModel

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = viewModel(this) { ApplicationViewModel() }

        setContent {
            ApplicationScreen(viewModel = viewModel)
        }
    }
}
