package entity

import kotlinx.coroutines.CoroutineScope

/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
data class DiskEntity(
    override val name: String = "",
    override val totalSize: String = "",
    val usageSize: String = "",
    val freeSpaceSize: String = "",
    override val canRead: Boolean = false,
    override val canWrite: Boolean = false,
    override val children: List<FileEntity> = emptyList(),
    override val isDirectory: Boolean = true,
    override val hasChildren: Boolean = true
) : FileEntity {
    override fun readLines(scope: CoroutineScope): TextLines {
        return EmptyTextLines
    }

}