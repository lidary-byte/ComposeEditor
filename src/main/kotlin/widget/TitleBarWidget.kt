package widget

import MainViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.EditorIcons
import common.IntUiThemes
import org.jetbrains.jewel.ui.component.*
import org.jetbrains.jewel.window.DecoratedWindowScope
import org.jetbrains.jewel.window.TitleBar
import org.jetbrains.jewel.window.newFullscreenControls
import java.awt.Desktop
import java.net.URI

/**
 * @Author : lcc
 * @CreateData : 2024/1/29
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DecoratedWindowScope.TitleBarWidget(mainViewModel: MainViewModel) {
    val theme by mainViewModel.theme.collectAsState()
    val chooseFile by mainViewModel.chooseFile.collectAsState()
    TitleBar(Modifier.newFullscreenControls(), gradientStartColor = Color(0xFFF5D4C1)) {
        Row(
            Modifier.align(Alignment.Start)
                .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon("ic_logo.svg", "logo", EditorIcons::class.java, modifier = Modifier.size(28.dp))

            if (chooseFile.file.isNotEmpty()) {
                Dropdown(Modifier.height(30.dp), menuContent = {
                    chooseFile.file.forEachIndexed { index, file ->
                        selectableItem(
                            selected = index == chooseFile.currentIndex,
                            onClick = {
                                mainViewModel.selectFile(index)
                            },
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
//                        val painterProvider =
//                            rememberResourcePainterProvider(it.icon, StandaloneSampleIcons::class.java)
//                        val painter by painterProvider.getPainter(Size(20))
//                        Icon(painter, "icon")
                                Text(file.name)
                            }
                        }
                    }
                }) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
//                        val painterProvider =
//                            rememberResourcePainterProvider(
//                                MainViewModel.currentView.icon,
//                                StandaloneSampleIcons::class.java,
//                            )
//                        val painter by painterProvider.getPainter(Size(20))
//                        Icon(painter, "icon")
                        Text(chooseFile.file.getOrNull(chooseFile.currentIndex)?.name ?: "")

                    }
                }
            }
        }

        Text(title)

        Row(Modifier.align(Alignment.End)) {
            Tooltip({
                Text("https://github.com/lidary-byte/ComposeEditor")
            }) {
                IconButton({
                    Desktop.getDesktop().browse(URI.create("https://github.com/lidary-byte/ComposeEditor"))
                }, Modifier.size(40.dp).padding(5.dp)) {
                    Icon(
                        "ic_github.svg",
                        "", EditorIcons::class.java
                    )
                }
            }

            Tooltip({
                when (theme) {
                    IntUiThemes.Light -> Text("Switch to light theme with light header")
                    IntUiThemes.LightWithLightHeader -> Text("Switch to dark theme")
                    IntUiThemes.Dark -> Text("Switch to light theme")
                }
            }) {
                IconButton({
                    mainViewModel.themeMode(
                        when (theme) {
                            IntUiThemes.Light -> IntUiThemes.LightWithLightHeader
                            IntUiThemes.LightWithLightHeader -> IntUiThemes.Dark
                            IntUiThemes.Dark -> IntUiThemes.Light
                        }
                    )
                }, Modifier.size(40.dp).padding(5.dp)) {
                    when (theme) {
                        IntUiThemes.Light -> Icon(
                            "ic_light.svg",
                            "Themes", EditorIcons::class.java
                        )

                        IntUiThemes.LightWithLightHeader -> Icon(
                            "ic_light_header.svg",
                            "Themes", EditorIcons::class.java
                        )

                        IntUiThemes.Dark -> Icon(
                            "ic_dark.svg",
                            "Themes", EditorIcons::class.java
                        )
                    }
                }
            }
        }
    }
}
