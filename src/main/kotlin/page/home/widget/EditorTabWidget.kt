package page.home.widget


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.ui.component.SimpleTabContent
import org.jetbrains.jewel.ui.component.TabData
import org.jetbrains.jewel.ui.component.TabStrip
import org.jetbrains.jewel.ui.component.Text
import org.jetbrains.jewel.ui.theme.defaultTabStyle
import page.home.HomePageViewModel

/**
 * @Author : lcc
 * @CreateData : 2024/1/30
 * @Description:
 */


@Composable
fun EditorTabWidget(homePageViewModel: HomePageViewModel) {
    val homeUiStatus by homePageViewModel.homePageUiStatus.collectAsState()

    val tabs = remember(homeUiStatus) {
        homeUiStatus.openFileList.mapIndexed { index, file ->
            TabData.Editor(
                selected = homeUiStatus.selectTab == index,
                content = { tabState ->
                    SimpleTabContent(
                        state = tabState,
                        modifier = Modifier,
                        label = { Text(file.name) },
                    )
                },
                onClose = {
                    homePageViewModel.closeFile(file,index)
                },
                onClick = {
                    homePageViewModel.selectTab(index)
                },
            )
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(JewelTheme.defaultTabStyle.metrics.tabHeight)
            .fillMaxWidth()
    ) {
        TabStrip(tabs, modifier = Modifier.weight(1f))
    }

}



