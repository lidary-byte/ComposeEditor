package page

import common.toProjectFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
//import widget.filetree.FileTree
import java.io.File
import java.nio.file.Files

/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
class HomePageViewModel : ViewModel() {
//    val rootDiskSize = MutableStateFlow<List<String>>(emptyList())
//    val fileTree = MutableStateFlow(FileTree(File("").toProjectFile()))
//
//    init {
//        checkDisk()
//    }
//
//    fun checkDisk() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val rootDiskSize = File.listRoots()?.map { it.absolutePath } ?: emptyList()
//            val fileTree = FileTree(File(rootDiskSize.firstOrNull() ?: "").toProjectFile())
//            this@HomePageViewModel.fileTree.emit(fileTree)
//            this@HomePageViewModel.rootDiskSize.emit(rootDiskSize)
//        }
//    }
//
//
//    private fun listChildFile(file: File, childFileList: MutableList<DiskInfoEntity>) {
//        if (file.isDirectory) {
//            childFileList.add(
//                DiskInfoEntity(
//                    file.path,
//                    file.totalSpace.readableFileSize(),
//                    (file.totalSpace - file.freeSpace).readableFileSize(),
//                    file.freeSpace.readableFileSize(),
//                    file.canRead(), file.canWrite(),
//                    type = FileType.FILE_FOLDER
//                )
//            )
//            file.listFiles()?.forEach {
//                listChildFile(it, mutableListOf())
//            }
//        } else if (file.isFile) {
//            childFileList.add(
//                DiskInfoEntity(
//                    file.path,
//                    file.totalSpace.readableFileSize(),
//                    (file.totalSpace - file.freeSpace).readableFileSize(),
//                    file.freeSpace.readableFileSize(),
//                    file.canRead(), file.canWrite(),
//                    type = FileType.FILE
//                )
//            )
//        }
//    }
}