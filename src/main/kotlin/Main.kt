import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import common.IntUiThemes
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.standalone.theme.IntUiTheme
import org.jetbrains.jewel.intui.standalone.theme.darkThemeDefinition
import org.jetbrains.jewel.intui.standalone.theme.lightThemeDefinition
import org.jetbrains.jewel.intui.window.decoratedWindow
import org.jetbrains.jewel.intui.window.styling.dark
import org.jetbrains.jewel.intui.window.styling.light
import org.jetbrains.jewel.intui.window.styling.lightWithLightHeader
import org.jetbrains.jewel.ui.ComponentStyling
import org.jetbrains.jewel.window.DecoratedWindow
import org.jetbrains.jewel.window.styling.TitleBarStyle
import page.home.HomePage
import widget.TitleBarWidget
import javax.swing.UIManager


@OptIn(ExperimentalTextApi::class)
fun main() = application {
    // 设置ui风格
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    PreComposeApp {
        val textStyle = TextStyle(fontFamily = FontFamily("Inter"))
        val mainViewModel = viewModel(keys = emptyList()) {
            MainViewModel()
        }
        val theme by mainViewModel.theme.collectAsState()

        IntUiTheme(
            if (theme.isDark()) {
                JewelTheme.darkThemeDefinition(defaultTextStyle = textStyle)
            } else {
                JewelTheme.lightThemeDefinition(defaultTextStyle = textStyle)
            },
            ComponentStyling.decoratedWindow(
                titleBarStyle = when (theme) {
                    IntUiThemes.Light -> TitleBarStyle.light()
                    IntUiThemes.LightWithLightHeader -> TitleBarStyle.lightWithLightHeader()
                    IntUiThemes.Dark -> TitleBarStyle.dark()
                }
            ),
            true
        ) {
            DecoratedWindow(
                state = rememberWindowState(
                    position = WindowPosition.Aligned(Alignment.Center),
                    width = 1200.dp,
                    height = 600.dp
                ),
                icon = painterResource("ic_logo.svg"),
                onCloseRequest = ::exitApplication,
                title = "Compose Editor"
            ) {
                TitleBarWidget(mainViewModel)
                Box(
                    Modifier.fillMaxSize()
                        .background(JewelTheme.globalColors.paneBackground)
                ) {
                    HomePage(mainViewModel)
                }
            }
        }

    }
}
