package page

import entity.DiskInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import readableFileSize
import java.io.File

/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
class HomePageViewModel : ViewModel() {
    val rootDiskSize = MutableStateFlow(mutableListOf(DiskInfoEntity()))
    val childFileSize = MutableStateFlow(mutableListOf(""))

    init {
        checkDisk()
    }

    fun checkDisk() {
        viewModelScope.launch(Dispatchers.IO) {
            val rootDiskSize = mutableListOf<DiskInfoEntity>()
            val childSize = mutableListOf<String>()
            File.listRoots().forEach {
                it.listFiles()?.forEach {
                    childSize.add("磁盘路径:${it.path} --------- 磁盘总空间大小:${it.freeSpace} ---------- 剩余磁盘空间大小:${it.freeSpace.readableFileSize()}")
                }
                rootDiskSize.add(
                    DiskInfoEntity(
                        it.path,
                        it.totalSpace.readableFileSize(),
                        (it.totalSpace - it.freeSpace).readableFileSize(),
                        it.freeSpace.readableFileSize(),
                        it.canRead(), it.canWrite()
                    )
                )
            }
            childFileSize.emit(childSize)
            this@HomePageViewModel.rootDiskSize.emit(rootDiskSize)
        }
    }

}