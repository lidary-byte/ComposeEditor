package page.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import java.io.File
import kotlin.math.max


/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
class HomePageViewModel : ViewModel() {


    private val _homePageUiStatus = MutableStateFlow(HomePageStatus())
    val homePageUiStatus
        get() = _homePageUiStatus.asStateFlow()


    fun openFile(file: File?) {
        file ?: return
        _homePageUiStatus.update {
            if (it.openFileList.indexOf(file) == -1) {
                val newList = it.openFileList + file
                it.copy(
                    selectTab = newList.lastIndex,
                    openFileList = newList
                )
            } else it
        }
    }


    fun closeFile(file: File, index: Int) {
        _homePageUiStatus.update {
            if (it.openFileList.isEmpty()) return
            it.copy(
                selectTab = if (it.selectTab >= index) {
                    val maxPossibleIndex = max(0, it.openFileList.lastIndex)
                    (it.selectTab - 1)
                        .coerceIn(0..maxPossibleIndex)
                } else it.selectTab,
                openFileList = it.openFileList - file
            )
        }
    }


    fun selectTab(index: Int) {
        _homePageUiStatus.update { it.copy(selectTab = index) }
    }
}


data class HomePageStatus(val selectTab: Int = 0, val openFileList: List<File> = listOf())

