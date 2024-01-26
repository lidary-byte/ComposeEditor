package entity

import kotlinx.coroutines.CoroutineScope

/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
interface FileEntity {
    val name: String
    val totalSize: String
    val canRead: Boolean
    val canWrite: Boolean
//    val type: FileType
    val children: List<FileEntity>
    val isDirectory: Boolean
    val hasChildren: Boolean

    fun readLines(scope: CoroutineScope): TextLines
    val permissions: String
        get() {
            return when {
//                    canRead && canWrite -> "可读写"
//                    canRead && !canWrite -> "只读"
//                !canRead && canWrite -> "只写"
                else -> "无读写权限"
            }
        }
}

enum class FileType {
    DISK, FILE_FOLDER, FILE, NONE
}

interface TextLines {
    val size: Int
    fun get(index: Int): String
}

object EmptyTextLines : TextLines {
    override val size: Int
        get() = 0

    override fun get(index: Int): String = ""
}