package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val PaperColorScheme = lightColorScheme(
    primary = InkBlack,
    onPrimary = InkWhite,
    primaryContainer = PaperAged,
    onPrimaryContainer = InkBlack,
    secondary = PaperRustRed,
    onSecondary = InkWhite,
    secondaryContainer = PaperSand,
    onSecondaryContainer = InkBlack,
    tertiary = PaperPrussianBlue,
    onTertiary = InkWhite,
    background = PaperWarm,
    onBackground = InkBlack,
    surface = PaperCard,
    onSurface = InkBlack,
    surfaceVariant = PaperDark,
    onSurfaceVariant = InkCharcoal,
    outline = PaperBorder,
    error = PaperRustRed,
    onError = InkWhite
)

@Composable
fun WerewolfTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = PaperColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = PaperWarm.toArgb()
            window.navigationBarColor = PaperWarm.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
