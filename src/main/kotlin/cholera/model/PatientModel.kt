package cholera.model

import tornadofx.*

class PatientModel(var patient: Patient) : ViewModel() {
    val name = bind { patient.nameProperty }
    val age = bind { patient.ageProperty }
    val hasDiarrhea = bind { patient.hasDiarreaProperty }
    val hasRiceWaterStools = bind { patient.hasRiceWaterStoolsProperty }
    val hasLethargic = bind { patient.hasLethargicProperty }
    val hasWeakPulse = bind { patient.hasWeakPulseProperty }
    val hasRespiratoryDistress = bind { patient.hasRespiratoryDistressProperty }
    val hasSunkenEyes = bind { patient.hasSunkenEyesProperty }
    val hasDrinksPoorly = bind { patient.hasDrinksPoorlyProperty }
    val hasSkinPinchGoesBackVerySlowly = bind { patient.hasSkinPinchGoesBackVerySlowlyProperty }
    val isIrritable = bind { patient.isIrritableProperty }
    val hasRapidPulse = bind { patient.hasRapidPulseProperty }
    val isThirsty = bind { patient.isThirstyProperty }
    val hasSkinPinchGoesBackSlowly = bind { patient.hasSkinPinchGoesBackSlowlyProperty }
}