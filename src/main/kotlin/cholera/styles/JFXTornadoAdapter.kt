package cholera.styles

import com.jfoenix.controls.*
import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.control.ButtonBar
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import tornadofx.*

fun EventTarget.jfxcheckbox(text: String? = null, property: Property<Boolean>? = null, op: JFXCheckBox.() -> Unit = {}) = JFXCheckBox(text).attachTo(this, op) {
    if (property != null) it.bind(property)
}

inline fun <T : Node> T.attachTo(
        parent: EventTarget,
        after: T.() -> Unit,
        before: (T) -> Unit
) = this.also(before).attachTo(parent, after)

fun ButtonBar.jfxbutton(text: String = "", type: ButtonBar.ButtonData? = null, graphic: Node? = null, op: JFXButton.() -> Unit = {}) = JFXButton(text).also {
    if (type != null) ButtonBar.setButtonData(it, type)
    if (graphic != null) it.graphic = graphic
    buttons += it
    op(it)
}

fun EventTarget.jfxprogressbar(initialValue: Double? = null, op: JFXProgressBar.() -> Unit = {}) = JFXProgressBar().attachTo(this, op) {
    if (initialValue != null) it.progress = initialValue
}

fun EventTarget.jfxtextfield(property: ObservableValue<String>, op: JFXTextField.() -> Unit = {}) = jfxtextfield().apply {
    bind(property)
    op(this)
}

fun EventTarget.jfxtextfield(value: String? = null, op: TextField.() -> Unit = {}) = JFXTextField().attachTo(this, op) {
    if (value != null) it.text = value
}

fun EventTarget.jfxtextarea(value: String? = null, op: TextArea.() -> Unit = {}) = JFXTextArea().attachTo(this, op) {
    if (value != null) it.text = value
}