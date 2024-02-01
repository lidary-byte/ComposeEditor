package page.home.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.unit.dp
import org.fife.ui.rsyntaxtextarea.FileLocation
import org.fife.ui.rsyntaxtextarea.TextEditorPane
import org.fife.ui.rtextarea.RTextScrollPane
import util.extToSyntaxConstants
import java.awt.BorderLayout
import java.io.File
import javax.swing.JPanel


/**
 * @Author : lcc
 * @CreateData : 2024/1/30
 * @Description:
 */
@Composable
fun EditorWidget(file: File) {
    Box(Modifier.fillMaxSize().padding(12.dp)) {
//        val scrollState = rememberLazyListState()
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            state = scrollState
//        ) {
//            file.forEachLine {
//                item {
//
//                }
//            }
//        }
//
//        VerticalScrollbar(
//            rememberScrollbarAdapter(scrollState),
//            Modifier.align(Alignment.CenterEnd)
//        )

        SwingPanel(modifier = Modifier.fillMaxSize(), factory = {
            JPanel(BorderLayout()).apply {
                val textArea = TextEditorPane()
                    .apply {
                        load(FileLocation.create(file))
                        syntaxEditingStyle = file.extToSyntaxConstants
                        // 可折叠
                        isCodeFoldingEnabled = true
                        markOccurrences = true
                        isMarginLineEnabled = true

                    }
                val sp = RTextScrollPane(textArea)
                add(sp)
            }
        })
    }
}

