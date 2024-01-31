package page.home.widget

import MainViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.jewel.ui.component.OutlinedButton
import widget.ChooseFileDialog

@Composable
fun EditorEmptyView(mainViewModel: MainViewModel) {

    var isOpen by remember { mutableStateOf(false) }
    if (isOpen) {
        ChooseFileDialog {
            isOpen = false
            mainViewModel.addFile(it)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "欢迎使用Compose Editor",
            fontSize = 20.sp
        )

        OutlinedButton({
            isOpen = true
        }, modifier = Modifier.padding(top = 12.dp)) {
            Text("打开文件")
        }
    }
}