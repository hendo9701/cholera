package cholera.util

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons
import tornadofx.*

const val DEEP_BLUE = "#2B579A"
fun makeIcon(name: FontAwesomeIcons): FontAwesomeIcon {
    return FontAwesomeIcon().apply {
        setIcon(name)
        style {
            fill = c(DEEP_BLUE)
            fontSize = 1.5.em
            fontFamily = "FontAwesome"
        }
    }
}