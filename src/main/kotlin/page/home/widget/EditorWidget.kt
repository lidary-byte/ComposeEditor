package page.home.widget

import MainViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import org.fife.ui.rsyntaxtextarea.FileLocation
import org.fife.ui.rsyntaxtextarea.TextEditorPane
import org.fife.ui.rtextarea.RTextScrollPane
import util.extToSyntaxConstants
import java.io.File
import javax.swing.BorderFactory


/**
 * @Author : lcc
 * @CreateData : 2024/1/30
 * @Description:
 */
@Composable
fun EditorWidget(mainViewModel: MainViewModel, file: File) {
    val codeTheme by mainViewModel.codeTheme.collectAsState()
    SwingPanel(modifier = Modifier.fillMaxSize(), factory = {
        val textArea = TextEditorPane()
            .apply {
                load(FileLocation.create(file))
                syntaxEditingStyle = file.extToSyntaxConstants
                // 可折叠
                isCodeFoldingEnabled = true
                markOccurrences = true
                isMarginLineEnabled = true
                codeTheme?.apply(this)
            }
        RTextScrollPane(textArea).apply {
            border = BorderFactory.createEmptyBorder()
        }


    })
}

