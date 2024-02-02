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
        "as" -> SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT
        "ams" -> SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86
        "bbcode" -> SyntaxConstants.SYNTAX_STYLE_BBCODE
        "c" -> SyntaxConstants.SYNTAX_STYLE_C
        "clj" -> SyntaxConstants.SYNTAX_STYLE_CLOJURE
        "cpp" -> SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS
        "cs" -> SyntaxConstants.SYNTAX_STYLE_CSHARP
        "css" -> SyntaxConstants.SYNTAX_STYLE_CSS
        "csv" -> SyntaxConstants.SYNTAX_STYLE_CSV
        "d" -> SyntaxConstants.SYNTAX_STYLE_D
        "dart" -> SyntaxConstants.SYNTAX_STYLE_DART
        "pas" -> SyntaxConstants.SYNTAX_STYLE_DELPHI
        "dtd" -> SyntaxConstants.SYNTAX_STYLE_DTD
        "f", "f90", "f95" -> SyntaxConstants.SYNTAX_STYLE_FORTRAN
        "go" -> SyntaxConstants.SYNTAX_STYLE_GO
        "groovy" -> SyntaxConstants.SYNTAX_STYLE_GROOVY
        "hbs", "handlebars" -> SyntaxConstants.SYNTAX_STYLE_HANDLEBARS
        "hosts" -> SyntaxConstants.SYNTAX_STYLE_HOSTS
        "htaccess " -> SyntaxConstants.SYNTAX_STYLE_HTACCESS
        "html" -> SyntaxConstants.SYNTAX_STYLE_HTML
        "ini" -> SyntaxConstants.SYNTAX_STYLE_INI
        "java" -> SyntaxConstants.SYNTAX_STYLE_JAVA
        "js" -> SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT
        "json" -> SyntaxConstants.SYNTAX_STYLE_JSON
        "jsp" -> SyntaxConstants.SYNTAX_STYLE_JSP
        "kt" -> SyntaxConstants.SYNTAX_STYLE_KOTLIN
        "tex" -> SyntaxConstants.SYNTAX_STYLE_LATEX

        "less" -> SyntaxConstants.SYNTAX_STYLE_LESS
        "lisp", "cl", "lsp" -> SyntaxConstants.SYNTAX_STYLE_LISP
        "lua" -> SyntaxConstants.SYNTAX_STYLE_LUA
        "md", "markdown" -> SyntaxConstants.SYNTAX_STYLE_MARKDOWN
        "mxml" -> SyntaxConstants.SYNTAX_STYLE_MXML
        "nsi" -> SyntaxConstants.SYNTAX_STYLE_NSIS
        "pl", "pm" -> SyntaxConstants.SYNTAX_STYLE_PERL
        "php" -> SyntaxConstants.SYNTAX_STYLE_PHP
        "proto" -> SyntaxConstants.SYNTAX_STYLE_PROTO

        "properties" -> SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE
        "py" -> SyntaxConstants.SYNTAX_STYLE_PYTHON
        "rb" -> SyntaxConstants.SYNTAX_STYLE_RUBY
        "sas" -> SyntaxConstants.SYNTAX_STYLE_SAS
        "scala" -> SyntaxConstants.SYNTAX_STYLE_SCALA
        "sql" -> SyntaxConstants.SYNTAX_STYLE_SQL
        "tcl" -> SyntaxConstants.SYNTAX_STYLE_TCL
        "ts" -> SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT
        "vb" -> SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC
        "bat" -> SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH
        "xml" -> SyntaxConstants.SYNTAX_STYLE_XML
        "yaml", "yml" -> SyntaxConstants.SYNTAX_STYLE_YAML


        else -> when (this?.name) {
            "Dockerfile" -> SyntaxConstants.SYNTAX_STYLE_DOCKERFILE
            "Makefile" -> SyntaxConstants.SYNTAX_STYLE_MAKEFILE
            else -> SyntaxConstants.SYNTAX_STYLE_NONE
        }
    }