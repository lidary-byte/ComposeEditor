package entity

/**
 * @Author : lcc
 * @CreateData : 2024/1/25
 * @Description:
 */
data class DiskInfoEntity(
    val path: String = "",
    val totalSize: String = "",
    val usageSize: String = "",
    val freeSpaceSize: String = "",
    val canRead: Boolean = false,
    val canWrite: Boolean = false
) {
    val permissions: String
        get() {
            return when {
                canRead && canWrite -> "可读写"
                canRead && !canWrite -> "只读"
                !canRead && canWrite -> "只写"
                else -> "无读写权限"
            }
        }
}