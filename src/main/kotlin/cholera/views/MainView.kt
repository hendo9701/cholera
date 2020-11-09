package cholera.views

import cholera.controller.PatientController
import cholera.model.Patient
import cholera.model.PatientModel
import cholera.styles.*
import cholera.util.makeIcon
import com.jfoenix.controls.JFXButton.*
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons
import javafx.application.Platform
import javafx.geometry.Orientation
import javafx.scene.control.Tooltip
import javafx.scene.image.Image
import tornadofx.*

class MainView : View("Cholera diagnosis") {

    val controller: PatientController by inject()
    val model: PatientModel = PatientModel(Patient())
    val status: TaskStatus by inject()
    val diagnosis = jfxtextarea {
        isWrapText = true
        isEditable = false
    }

    init {
        setStageIcon(Image(javaClass.getResourceAsStream("/cholera.png")))
    }

    override val root = form {
        fieldset("Personal info:", labelPosition = Orientation.VERTICAL) {
            field("Name") {
                jfxtextfield(model.name) {
                    promptText = "Enter patient name"
                    validator {
                        if (it.isNullOrBlank()) error("Name is required") else null
                    }
                }
            }
            field("Age") {
                jfxtextfield(model.age) {
                    promptText = "Enter patient age"
                    filterInput { it.controlNewText.isInt() }
                    validator { if (it.isNullOrBlank()) error("Age is required") else null }
                }
            }
        }
        fieldset("Symptoms:") {
            gridpane {
                field {
                    jfxcheckbox(text = "Diarrhea", property = model.hasDiarrhea)
                    gridpaneConstraints {
                        columnRowIndex(0, 0)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Rice water stools", property = model.hasRiceWaterStools)
                    gridpaneConstraints {
                        columnRowIndex(1, 0)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Respiratory distress", property = model.hasRespiratoryDistress)
                    gridpaneConstraints {
                        columnRowIndex(2, 0)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Lethargic", property = model.hasLethargic)
                    gridpaneConstraints {
                        columnRowIndex(0, 1)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Weak pulse", property = model.hasWeakPulse)
                    gridpaneConstraints {
                        columnRowIndex(1, 1)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Sunken eyes", property = model.hasSunkenEyes)
                    gridpaneConstraints {
                        columnRowIndex(2, 1)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Drinks poorly", property = model.hasDrinksPoorly)
                    gridpaneConstraints {
                        columnRowIndex(0, 2)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Skin pinch goes back very slowly", property = model.hasSkinPinchGoesBackVerySlowly)
                    gridpaneConstraints {
                        columnRowIndex(1, 2)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Irritable", property = model.isIrritable)
                    gridpaneConstraints {
                        columnRowIndex(2, 2)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Rapid pulse", property = model.hasRapidPulse)
                    gridpaneConstraints {
                        columnRowIndex(0, 3)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Thirsty", property = model.isThirsty)
                    gridpaneConstraints {
                        columnRowIndex(1, 3)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    jfxcheckbox(text = "Skin pinch goes back slowly", property = model.hasSkinPinchGoesBackSlowly)
                    gridpaneConstraints {
                        columnRowIndex(2, 3)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
            }
        }
        fieldset("Diagnosis:") {
            field {
                this += diagnosis
            }
        }
        buttonbar {
            jfxprogressbar {
                visibleWhen { status.running }
                progressProperty().bind(status.progress)
                prefWidth = 100.0
                addClass(Styles.PROGRESS_BAR)
            }
            jfxbutton {
                isDefaultButton = true
                buttonType = ButtonType.RAISED
                graphic = makeIcon(FontAwesomeIcons.SEARCH)
                tooltip = Tooltip("Query")
                enableWhen { model.valid }
                action {
                    model.commit()
                    var result = ""
                    runAsync(status) {
                        result = controller.analyze(model.patient)
                    }.setOnSucceeded {
                        Platform.runLater {
                            diagnosis.text = result
                        }
                    }
                }
            }
            jfxbutton {
                isCancelButton = true
                buttonType = ButtonType.RAISED
                graphic = makeIcon(FontAwesomeIcons.REFRESH)
                tooltip("Refresh")
                enableWhen { model.dirty }
                action {
                    model.rollback()
                    diagnosis.clear()
                }
            }
        }
    }
}