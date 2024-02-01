package util

import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import java.io.File
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


val File?.extToSyntaxConstants: String
    get() = when (this?.extension) {
        "c" -> SyntaxConstants.SYNTAX_STYLE_C
        "cpp" -> SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS
        "css" -> SyntaxConstants.SYNTAX_STYLE_CSS
        "csv" -> SyntaxConstants.SYNTAX_STYLE_CSV
        "dart" -> SyntaxConstants.SYNTAX_STYLE_DART
        "go" -> SyntaxConstants.SYNTAX_STYLE_GO
        "groovy" -> SyntaxConstants.SYNTAX_STYLE_GROOVY
        "hosts" -> SyntaxConstants.SYNTAX_STYLE_HOSTS
        "java" -> SyntaxConstants.SYNTAX_STYLE_JAVA
        "json" -> SyntaxConstants.SYNTAX_STYLE_JSON
        "jsp" -> SyntaxConstants.SYNTAX_STYLE_JSP
        "js" -> SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT
        "kt" -> SyntaxConstants.SYNTAX_STYLE_KOTLIN
        "md" -> SyntaxConstants.SYNTAX_STYLE_MARKDOWN
        "sql" -> SyntaxConstants.SYNTAX_STYLE_SQL
        "xml" -> SyntaxConstants.SYNTAX_STYLE_XML
        "html" -> SyntaxConstants.SYNTAX_STYLE_HTML
        else -> SyntaxConstants.SYNTAX_STYLE_NONE
    }