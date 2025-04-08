package com.plcoding.bookpedia.core.presentation.utill

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


val DarkBlue = Color(0xFF0B405E)
val SolidWhite = Color(0xFFFFFFFF)
val SolidBlack = Color(0xff000000)
val SandYellow = Color(0xFFFFBD64)
val LightBlue = Color(0xFF9AD9FF)

val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    onPrimary = SolidWhite,
    secondary = SandYellow,
    onSecondary = DarkBlue,
    surface = SolidWhite,
    onSurface = SolidBlack
)

val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    onPrimary = SolidWhite,
    secondary = SandYellow,
    onSecondary = DarkBlue,
    surface = SolidBlack,
    onSurface = SolidWhite
)





