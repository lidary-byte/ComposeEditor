import common.IntUiThemes
import entity.FileEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import java.io.File

/**
 * @Author : lcc
 * @CreateData : 2024/1/26
 * @Description:
 */
class MainViewModel : ViewModel() {

    private val _theme = MutableStateFlow(IntUiThemes.LightWithLightHeader)
    val theme
        get() = _theme.asStateFlow()


    private val _isExpanded = MutableStateFlow(false)
    val isExpanded
        get() = _isExpanded.asStateFlow()


    private val _chooseFile = MutableStateFlow(FileEntity())
    val chooseFile
        get() = _chooseFile.asStateFlow()


    fun addFile(file: List<File>) {

        if (file.isEmpty()) return

        val fileEntity = if (_chooseFile.value.file.isEmpty()) {
            this._isExpanded.tryEmit(true)
            FileEntity(0, file.toMutableList())
        } else {
            FileEntity((_chooseFile.value.file.size + file.size) - 1, _chooseFile.value.file.apply {
                addAll(file)
            })
        }
        this._chooseFile.tryEmit(fileEntity)

    }

    fun selectFile(index: Int) {
        this._chooseFile.tryEmit(this._chooseFile.value.apply {
            currentIndex = index
        })
    }


    fun changeExpanded(isExpanded: Boolean) {
        this._isExpanded.tryEmit(isExpanded)
    }


    fun themeMode(theme: IntUiThemes) {
        this._theme.tryEmit(theme)
    }

}

