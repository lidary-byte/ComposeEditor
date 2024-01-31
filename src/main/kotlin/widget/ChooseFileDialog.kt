package widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

/**
 * @Author : lcc
 * @CreateData : 2024/1/30
 * @Description:
 */
enum class ChooseFileDialogMode {
    LOAD, SAVE
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ChooseFileDialog(
    mode: ChooseFileDialogMode = ChooseFileDialogMode.LOAD,
    title: String = "Choose File Dialog",
    extensions: List<FileNameExtensionFilter> = listOf(),
    onResult: (files: List<File>) -> Unit
) {
    DisposableEffect(Unit) {
        val job = GlobalScope.launch(Dispatchers.Swing) {
            val fileChooser = JFileChooser()
            fileChooser.dialogTitle = title
            fileChooser.isMultiSelectionEnabled = mode == ChooseFileDialogMode.LOAD
            fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
            fileChooser.isAcceptAllFileFilterUsed = extensions.isEmpty()
            extensions.forEach { fileChooser.addChoosableFileFilter(it) }
            val returned: Int = if (mode == ChooseFileDialogMode.LOAD) {
                fileChooser.showOpenDialog(null)
            } else {
                fileChooser.showSaveDialog(null)
            }

            onResult(when (returned) {
                JFileChooser.APPROVE_OPTION -> {
                    if (mode == ChooseFileDialogMode.LOAD) {
                        val files = fileChooser.selectedFiles.filter { it.canRead() }
                        files.ifEmpty {
                            listOf()
                        }
                    } else {
                        if (!fileChooser.fileFilter.accept(fileChooser.selectedFile)) {
                            val ext = (fileChooser.fileFilter as FileNameExtensionFilter).extensions[0]
                            fileChooser.selectedFile = File(fileChooser.selectedFile.absolutePath + ".$ext")
                        }
                        listOf(fileChooser.selectedFile)
                    }
                };
                else -> listOf()
            })
        }

        onDispose {
            job.cancel()
        }
    }

}