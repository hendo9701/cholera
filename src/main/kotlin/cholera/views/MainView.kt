package cholera.views

import cholera.controller.PatientController
import cholera.model.Patient
import cholera.model.PatientModel
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.image.Image
import tornadofx.*

class MainView : View("Cholera diagnosis") {

    val controller: PatientController by inject()
    val model: PatientModel = PatientModel(Patient())
    val status: TaskStatus by inject()

    init{
        setStageIcon(Image(javaClass.getResourceAsStream("/cholera.png")))
    }

    override val root = form {
        fieldset("Personal info:", labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(model.name) {
                    promptText = "Enter patient name"
                    validator {
                        if (it.isNullOrBlank()) error("Name is required") else null
                    }
                }
            }
            field("Age") {
                textfield(model.age) {
                    promptText = "Enter patient age"
                    filterInput { it.controlNewText.isInt() }
                    validator { if (it.isNullOrBlank()) error("Age is required") else null }
                }
            }
        }
        fieldset("Symptoms:") {
            gridpane {
                field {
                    checkbox(text = "Diarrhea", property = model.hasDiarrhea)
                    gridpaneConstraints {
                        columnRowIndex(0, 0)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Rice water stools", property = model.hasRiceWaterStools)
                    gridpaneConstraints {
                        columnRowIndex(1, 0)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Respiratory distress", property = model.hasRespiratoryDistress)
                    gridpaneConstraints {
                        columnRowIndex(2, 0)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Lethargic", property = model.hasLethargic)
                    gridpaneConstraints {
                        columnRowIndex(0, 1)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Weak pulse", property = model.hasWeakPulse)
                    gridpaneConstraints {
                        columnRowIndex(1, 1)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Sunken eyes", property = model.hasSunkenEyes)
                    gridpaneConstraints {
                        columnRowIndex(2, 1)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Drinks poorly", property = model.hasDrinksPoorly)
                    gridpaneConstraints {
                        columnRowIndex(0, 2)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Skin pinch goes back very slowly", property = model.hasSkinPinchGoesBackVerySlowly)
                    gridpaneConstraints {
                        columnRowIndex(1, 2)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Irritable", property = model.isIrritable)
                    gridpaneConstraints {
                        columnRowIndex(2, 2)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Rapid pulse", property = model.hasRapidPulse)
                    gridpaneConstraints {
                        columnRowIndex(0, 3)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Thirsty", property = model.isThirsty)
                    gridpaneConstraints {
                        columnRowIndex(1, 3)
                        marginLeft = 5.0
                        columnSpan = 1
                    }
                }
                field {
                    checkbox(text = "Skin pinch goes back slowly", property = model.hasSkinPinchGoesBackSlowly)
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
                textarea(model.diagnosis) {
                    isWrapText = true
                    isEditable = false
                }
            }
        }
        buttonbar {
            label("Analyzing") {
                visibleWhen { status.running }
            }
            progressbar {
                visibleWhen { status.running }
                progressProperty().bind(status.progress)
            }
            button("Analyze") {
                isDefaultButton = true
                enableWhen { model.valid }
                action {
                    model.commit()
                    runAsync(status) {
                        model.diagnosis.value = controller.analyze(model.patient)
                    }
                }
            }
            button("Reset") {
                enableWhen { model.dirty }
                action {
                    model.rollback()
                }
            }
        }
    }
}