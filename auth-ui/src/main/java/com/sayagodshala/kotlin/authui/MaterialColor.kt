package com.sayagodshala.kotlin.authui

import com.sayagodshala.kotlin.authui.R

enum class MaterialColor private constructor(val textPrimaryColor: Int, val textSecondaryColor: Int, val regular: Int, val light: Int, val dark: Int) {
    RED(R.color.white, R.color.dark_grey, R.color.mat_red, R.color.mat_red_light_dark, R.color.mat_red_dark),
    PINK(R.color.white, R.color.dark_grey, R.color.mat_pink, R.color.mat_pink_light_dark, R.color.mat_pink_dark),
    PURPLE(R.color.white, R.color.dark_grey, R.color.mat_purple, R.color.mat_purple_light_dark, R.color.mat_purple_dark),
    DEEP_PURPLE(R.color.white, R.color.dark_grey, R.color.mat_deep_purple, R.color.mat_deep_purple_light_dark, R.color.mat_deep_purple_dark),
    INDIGO(R.color.white, R.color.dark_grey, R.color.mat_indigo, R.color.mat_indigo_light_dark, R.color.mat_indigo_dark),
    BLUE(R.color.white, R.color.dark_grey, R.color.mat_blue, R.color.mat_blue_light_dark, R.color.mat_blue_dark),
    LIGHT_BLUE(R.color.white, R.color.dark_grey, R.color.mat_light_blue, R.color.mat_light_blue_light_dark, R.color.mat_light_blue_dark),
    CYAN(R.color.white, R.color.dark_grey, R.color.mat_cyan, R.color.mat_cyan_light_dark, R.color.mat_cyan_dark),
    TEAL(R.color.white, R.color.dark_grey, R.color.mat_teal, R.color.mat_teal_light_dark, R.color.mat_teal_dark),
    BLUE_GREY(R.color.white, R.color.dark_grey, R.color.mat_blue_grey, R.color.mat_blue_grey_light_dark, R.color.mat_blue_grey_dark),
    YELLOW(R.color.black, R.color.dark_grey, R.color.mat_yellow, R.color.mat_yellow_light_dark, R.color.mat_yellow_dark),
    AMBER(R.color.black, R.color.dark_grey, R.color.mat_amber, R.color.mat_amber_light_dark, R.color.mat_amber_dark),
    BROWN(R.color.white, R.color.dark_grey, R.color.mat_brown, R.color.mat_brown_light_dark, R.color.mat_brown_dark),
    GREEN(R.color.white, R.color.dark_grey, R.color.mat_green, R.color.mat_green_light_dark, R.color.mat_green_dark),
    GREY(R.color.white, R.color.dark_grey, R.color.mat_grey, R.color.mat_grey_light_dark, R.color.mat_grey_dark),
    WHITE(R.color.black, R.color.dark_grey, R.color.white, R.color.grey, R.color.dark_grey)
}


