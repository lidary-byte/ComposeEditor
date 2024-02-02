package page.home

import MainViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.jewel.foundation.ExperimentalJewelApi
import org.jetbrains.jewel.foundation.lazy.tree.asTree
import org.jetbrains.jewel.foundation.lazy.tree.rememberTreeState
import org.jetbrains.jewel.ui.component.HorizontalSplitLayout
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
    val scrollState = rememberTreeState()

    val homeViewModel = viewModel(emptyList()) { HomePageViewModel() }

    val chooseFile by mainViewModel.chooseFile.collectAsState()

    val homePageUiStatus by homeViewModel.homePageUiStatus.collectAsState()

    HorizontalSplitLayout(
        minRatio = 0.1f,
        first = {
            if (chooseFile.file.isNotEmpty()) {
                LazyTree(
                    chooseFile.file[chooseFile.currentIndex].asTree(),
                    modifier = it,
                    treeState = scrollState,
                    onElementDoubleClick = {
                        if (it.data.isFile) {
                            homeViewModel.openFile(it.data)
                        }
                    }
                ) { tree ->
                    Text(tree.data.name, maxLines = 1, overflow = TextOverflow.Clip, modifier = it.fillMaxWidth())
                }
            } else {
                Text("", modifier = it)
            }

        }, second = {
            Box(modifier = it.fillMaxWidth()) {
                if (homePageUiStatus.openFileList.isEmpty()) {
                    EditorEmptyView(mainViewModel)
                } else {
                    Column(Modifier.fillMaxSize()) {
                        EditorTabWidget(homeViewModel)
                        Spacer(Modifier.height(1.dp))
                        EditorWidget(mainViewModel, homePageUiStatus.openFileList[homePageUiStatus.selectTab])
                    }
                }

            }
        }
    )

}