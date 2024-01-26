import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import common.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

/**
 * @Author : lcc
 * @CreateData : 2024/1/26
 * @Description:
 */
class MainViewModel : ViewModel() {
    var fontSize by mutableStateOf(13.sp)
    val maxLineSymbols = 120
    val isDark = MutableStateFlow<Boolean?>(null)

    fun themeMode(isDarkMode: Boolean) {
        isDark.tryEmit(isDarkMode)
    }

}


class AppSetting {
    var fontSize by mutableStateOf(13.sp)
    val maxLineSymbols = 120
    var isDark by mutableStateOf(false)
}