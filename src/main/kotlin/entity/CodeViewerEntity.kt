package entity

import common.Settings
import entity.edit.Editors

class CodeViewerEntity(
    val editors: Editors,
    val fileTree: FileTree,
    val settings: Settings
)