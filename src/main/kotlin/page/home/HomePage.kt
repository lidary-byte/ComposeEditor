package page.home

import MainViewModel
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.jewel.foundation.ExperimentalJewelApi
import org.jetbrains.jewel.foundation.lazy.tree.asTree
import org.jetbrains.jewel.ui.component.LazyTree
import org.jetbrains.jewel.ui.component.Text
import page.home.widget.EditorEmptyView
import page.home.widget.EditorTabWidget
import page.home.widget.EditorWidget

/**
 * @Author : lcc
 * @CreateData : 2024/1/30
 * @Description:
 */

@OptIn(ExperimentalJewelApi::class)
@Composable
fun HomePage(mainViewModel: MainViewModel) {
    val homeViewModel = viewModel(emptyList()) { HomePageViewModel() }

    val chooseFile by mainViewModel.chooseFile.collectAsState()

    val homePageUiStatus by homeViewModel.homePageUiStatus.collectAsState()

    val isExpanded by mainViewModel.isExpanded.collectAsState()
    val alpha by animateFloatAsState(if (isExpanded) 1f else 0f, SpringSpec(stiffness = Spring.StiffnessLow))
    val catalogueWidget by animateDpAsState(
        if (isExpanded) 400.dp else 0.dp,
        SpringSpec(stiffness = Spring.StiffnessLow)
    )

    Row {
        if (chooseFile.file.isNotEmpty()) {
            LazyTree(
                chooseFile.file[chooseFile.currentIndex].asTree(),
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(vertical = 12.dp)
                    .width(catalogueWidget)
                    .graphicsLayer(alpha = alpha),
                onElementDoubleClick = {
                    if (it.data.isFile) {
                        homeViewModel.openFile(it.data)
                    }
                }
            ) {
                Box(
                    modifier = Modifier.width(400.dp)
                        .fillMaxWidth()
                ) {
                    Text(it.data.name)
                }
            }
        }



        Box(modifier = Modifier.weight(1f)) {
            if (homePageUiStatus.openFileList.isEmpty()) {
                EditorEmptyView(mainViewModel)
            } else {
                Column(Modifier.fillMaxSize()) {
                    EditorTabWidget(homeViewModel)
                    Spacer(Modifier.height(1.dp))
                    EditorWidget(homePageUiStatus.openFileList[homePageUiStatus.selectTab])
                }
            }

        }
    }
}