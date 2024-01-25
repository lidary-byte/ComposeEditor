package page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entity.DiskInfoEntity
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.viewModel

/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(
    viewModel: HomePageViewModel = viewModel(keys = emptyList()) {
        HomePageViewModel()
    }
) {

    val rootDiskSize by viewModel.rootDiskSize.asStateFlow().collectAsState()
    val b by viewModel.childFileSize.asStateFlow().collectAsState()
    LazyColumn {
        stickyHeader {
            RowItem(DiskInfoEntity("名称", "大小", "已分配", "未使用"))
        }

        items(rootDiskSize) {
            RowItem(it)
        }
//        Button({
//            viewModel.checkDisk()
//        }) {
//            Text("获取文件大小")
//        }
//
//        LazyColumn {
//            items(a) {
//                Text(it)
//            }
//            items(b) {
//                Text(it)
//            }
//        }
    }

}


@Composable
fun RowItem(diskInfoEntity: DiskInfoEntity) {
    Row(modifier = Modifier.padding(4.dp)) {
        Text(diskInfoEntity.path, modifier = Modifier.weight(1f))
        Text(diskInfoEntity.totalSize, modifier = Modifier.weight(1f))
        Text(diskInfoEntity.usageSize, modifier = Modifier.weight(1f))
        Text(diskInfoEntity.freeSpaceSize, modifier = Modifier.weight(1f))
        Text(diskInfoEntity.permissions, modifier = Modifier.weight(1f))
    }
}


