import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import common.LocalTheme
import common.Settings
import common.Theme
import common.toProjectFile
import entity.CodeViewerEntity
import page.home.CodeViewerWidget
import entity.edit.Editors
import entity.FileTree
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.viewmodel.viewModel
import widget.StatusBarWidget
import java.io.File


@Composable
@Preview
fun App() {


    val codeViewer = remember {
        val editors = Editors()

        CodeViewerEntity(
            editors = editors,
            fileTree = FileTree(File("C:\\Users\\lidar\\Desktop\\ComposeEditer").toProjectFile(), editors),
            settings = Settings()
        )
    }
    PreComposeApp {
        val mainViewModel = viewModel(keys = emptyList()) {
            MainViewModel()
        }

        val darkMode by mainViewModel.isDark.collectAsState()

        DisableSelection {
            val theme = if (darkMode ?: isSystemInDarkTheme()) Theme.dark else Theme.light
            CompositionLocalProvider(
                LocalTheme provides theme,
            ) {
                MaterialTheme(
                    colors = theme.materialColors
                ) {
                    Column {
                        StatusBarWidget(codeViewer.settings, mainViewModel)
                        CodeViewerWidget(codeViewer)
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "Editor", state = WindowState(
            position = WindowPosition.Aligned(
                Alignment.Center
            )
        )
    ) {
        App()
    }
}
