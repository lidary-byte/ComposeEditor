package page.home.widget

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.jewel.ui.component.Text
import java.io.File

/**
 * @Author : lcc
 * @CreateData : 2024/1/30
 * @Description:
 */
@Composable
fun EditorWidget(file: File) {
    Box(Modifier.fillMaxSize().padding(12.dp)) {
        val scrollState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = scrollState
        ) {
            file.forEachLine {
                item { Text(it) }
            }
        }

        VerticalScrollbar(
            rememberScrollbarAdapter(scrollState),
            Modifier.align(Alignment.CenterEnd)
        )
    }
}