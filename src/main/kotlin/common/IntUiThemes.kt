package common

enum class IntUiThemes {
    Light, LightWithLightHeader, Dark;

    fun isDark() =
        this == Dark

    fun isLightHeader() =
        this == LightWithLightHeader


}
