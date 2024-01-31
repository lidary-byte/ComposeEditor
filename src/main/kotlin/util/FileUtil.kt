package util

import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow


/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */

/**
 * 格式化文件大小
 */
fun Long.readableFileSize(): String {
    if (this <= 0) {
        return "0B"
    }

    val units = arrayOf("B", "kB", "MB", "GB", "TB")
    val digitGroups = (log10(this.toDouble()) / log10(1024.0)).toInt()

    val decimalFormat = DecimalFormat("#,##0.00")
//    decimalFormat.setRoundingMode(RoundingMode.DOWN)
    return decimalFormat.format(this / 1024.0.pow(digitGroups.toDouble())) + units[digitGroups]
}
