package common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp

/**
 * @Author : lcc
 * @CreateData : 2024/1/26
 * @Description:
 */

class Settings {
    var fontSize by mutableStateOf(13.sp)
    val maxLineSymbols = 120
    var isDark by mutableStateOf(false)
}