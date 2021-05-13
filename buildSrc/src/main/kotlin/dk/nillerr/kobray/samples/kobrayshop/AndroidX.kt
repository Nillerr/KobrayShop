package dk.nillerr.kobray.samples.kobrayshop

object AndroidX {
    object Compose {
        const val version = "1.0.0-beta06"

        const val ui = "androidx.compose.ui:ui:$version"
        // Tooling support (Previews, etc.)
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
        const val foundation = "androidx.compose.foundation:foundation:$version"
        // Material Design
        const val material = "androidx.compose.material:material:$version"
        // Material design icons
        const val materialIcons = "androidx.compose.material:material-icons-core:$version"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
    }

    object Activity {
        // Integration with activities
        const val compose = "androidx.activity:activity-compose:1.3.0-alpha07"
    }

    object Lifecycle {
        // Integration with ViewModels
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha01"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha04"
    }
}