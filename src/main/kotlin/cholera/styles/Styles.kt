package cholera.styles

import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val PROGRESS_BAR by cssclass()
        val heading by cssclass()
        val checkedColor by cssproperty<Color>("-jfx-checked-color")
        const val SOFT_GREY = "#F3F3F3"
        const val VIVID_GREY = "#2B579A"
        const val DEEP_BLUE = "#2B579A"
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }

        form {
            backgroundColor += c(SOFT_GREY)
            checkBox {
                checkedColor.value = c(DEEP_BLUE)
            }
            button{
                borderColor += box(c(DEEP_BLUE))
                borderInsets += box(0.5.px)
                borderWidth += box(1.5.px)
                and(hover){
                    borderRadius += box(5.px)
                    effect = DropShadow()
                }
            }
        }
        PROGRESS_BAR{
            select(track){
                backgroundInsets += box(0.px)
                backgroundRadius += box(0.px)
            }
            select(bar){
                backgroundColor += c(DEEP_BLUE)
            }
        }


    }
}