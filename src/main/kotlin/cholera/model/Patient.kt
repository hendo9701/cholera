package cholera.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Patient(
        name: String = "",
        age: String = "",
        hasDiarrhea: Boolean = false,
        hasRiceWaterStools: Boolean = false,
        hasLethargic: Boolean = false,
        hasWeakPulse: Boolean = false,
        hasRespiratoryDistress: Boolean = false,
        hasSunkenEyes: Boolean = false,
        hasDrinksPoorly: Boolean = false,
        hasSkinPinchGoesBackVerySlowly: Boolean = false,
        isIrritable: Boolean = false,
        hasRapidPulse: Boolean = false,
        isThirsty: Boolean = false,
        hasSkinPinchGoesBackSlowly: Boolean = false,
        diagnosis: String = ""
) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    var name by nameProperty
    val ageProperty = SimpleStringProperty(this, "age", age)
    var age by ageProperty
    val hasDiarreaProperty = SimpleBooleanProperty(this,"hasDiarrhea", hasDiarrhea)
    var hasDiarrhea by hasDiarreaProperty
    val hasRiceWaterStoolsProperty = SimpleBooleanProperty(this, "hasRiceWaterStools", hasRiceWaterStools)
    var hasRiceWaterStools by hasRiceWaterStoolsProperty
    val hasLethargicProperty = SimpleBooleanProperty(this, "hasLethargic", hasLethargic)
    var hasLethargic by hasLethargicProperty
    val hasWeakPulseProperty = SimpleBooleanProperty(this, "hasWeakPulse", hasWeakPulse)
    var hasWeakPulse by hasWeakPulseProperty
    val hasRespiratoryDistressProperty = SimpleBooleanProperty(this, "hasRespiratoryDistress", hasRespiratoryDistress)
    var hasRespiratoryDistress by hasRespiratoryDistressProperty
    val hasSunkenEyesProperty = SimpleBooleanProperty(this, "hasSunkenEyes", hasSunkenEyes)
    var hasSunkenEyes by hasSunkenEyesProperty
    val hasDrinksPoorlyProperty = SimpleBooleanProperty(this, "hasDrinksPoorly", hasDrinksPoorly)
    var hasDrinksPoorly by hasDrinksPoorlyProperty
    val hasSkinPinchGoesBackVerySlowlyProperty = SimpleBooleanProperty(this, "hasSkinPinchGoesBackVerySlowly", hasSkinPinchGoesBackVerySlowly)
    var hasSkinPinchGoesBackVerySlowly by hasSkinPinchGoesBackVerySlowlyProperty
    val isIrritableProperty = SimpleBooleanProperty(this, "isIrritable", isIrritable)
    var isIrritable by isIrritableProperty
    val hasRapidPulseProperty = SimpleBooleanProperty(this, "hasRapidPulse", hasRapidPulse)
    var hasRapidPulse by hasRapidPulseProperty
    val isThirstyProperty = SimpleBooleanProperty(this, "isThirsty", isThirsty)
    var isThirsty by isThirstyProperty
    val hasSkinPinchGoesBackSlowlyProperty = SimpleBooleanProperty(this, "hasSkinPinchGoesBackSlowly", hasSkinPinchGoesBackSlowly)
    var hasSkinPinchGoesBackSlowly by hasSkinPinchGoesBackSlowlyProperty
    val diagnosisProperty = SimpleStringProperty(this, "diagnosis", diagnosis)
    var diagnosis by diagnosisProperty

    fun getSimplifiedName(): String {
        return name.toLowerCase().split(" ").joinToString(separator = "_")
    }

    fun symptoms(): List<String>{
        val simplifiedName = getSimplifiedName()
        val symptoms = mutableListOf<String>()
        if (hasDiarrhea)
            symptoms += "symptom($simplifiedName, diarrhea)."
        if (hasRiceWaterStools)
            symptoms += "symptom($simplifiedName, rice_water_stools)."
        if (hasLethargic)
            symptoms += "symptom($simplifiedName, lethargic)."
        if (hasWeakPulse)
            symptoms += "symptom($simplifiedName, weak_pulse)."
        if (hasRespiratoryDistress)
            symptoms += "symptom($simplifiedName, respiratory_distress)."
        if (hasSunkenEyes)
            symptoms += "symptom($simplifiedName, sunken_eyes)."
        if (hasDrinksPoorly)
            symptoms += "symptom($simplifiedName, drinks_poorly)."
        if (hasSkinPinchGoesBackVerySlowly)
            symptoms += "symptom($simplifiedName, skin_pinch_goes_back_very_slowly)."
        if (isIrritable)
            symptoms += "symptom($simplifiedName, irritable)."
        if (hasRapidPulse)
            symptoms += "symptom($simplifiedName, rapid_pulse)."
        if (isThirsty)
            symptoms += "symptom($simplifiedName, thirsty)."
        if (hasSkinPinchGoesBackSlowly)
            symptoms += "symptom($simplifiedName, skin_pinch_goes_back_slowly)."
        return symptoms
    }
}