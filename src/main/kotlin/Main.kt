import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import moe.tlaster.precompose.PreComposeApp
import page.HomePage


@Composable
@Preview
fun App() {
    PreComposeApp {
        MaterialTheme {
            HomePage()
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "DiskSpace", state = WindowState(
            position = WindowPosition.Aligned(
                Alignment.Center
            )
        )
    ) {
        App()
    }
}
