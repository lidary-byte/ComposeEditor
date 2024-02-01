import androidx.compose.foundation.isSystemInDarkTheme
import common.IntUiThemes
import entity.FileEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import org.fife.ui.rsyntaxtextarea.Theme
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


    private val _chooseFile = MutableStateFlow(FileEntity())
    val chooseFile
        get() = _chooseFile.asStateFlow()


    private val _codeTheme = MutableStateFlow<Theme?>(
        Theme.load(
            javaClass.getResourceAsStream(
                "/org/fife/ui/rsyntaxtextarea/themes/idea.xml"
            )
        )
    )

    val codeTheme
        get() = _codeTheme.asStateFlow()

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


    var fontT: Theme? = null
    fun themeMode(theme: IntUiThemes) {
        this._theme.tryEmit(theme)
        _codeTheme.value = when (theme) {
            IntUiThemes.Light, IntUiThemes.LightWithLightHeader -> Theme.load(
                javaClass.getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/idea.xml"
                )
            )

            IntUiThemes.Dark -> Theme.load(
                javaClass.getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"
                )
            )
        }

    }

}

