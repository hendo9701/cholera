package cholera.controller

import cholera.model.Patient
import org.projog.api.Projog
import org.projog.api.QueryResult
import tornadofx.*
import java.io.*
import java.util.stream.Collectors

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PatientController : Controller() {

    fun analyze(patient: Patient): String {
        val knowledgeFile = insertFactsIntoKnowledgeFile(patient)
        val diagnosis = StringBuilder()
        Projog().let { prolog: Projog ->
            prolog.consultFile(knowledgeFile)
            val choleraQuery = prolog.query("disease(Person, cholera).")
            val hasCholera = exists("Person", patient.getSimplifiedName(), choleraQuery.result)
            if (!hasCholera) {
                return "-Patient ${patient.name} does not have cholera disease"
            }
            diagnosis
                    .append("Patient ${patient.name} has cholera disease")
                    .append("\n")
                    .append("Treatments:")
                    .append("\n")

            val severeDehydratedQuery = prolog.query("symptom(Patient, severe_dehydration).")
            val isSevereDehydrated = exists("Patient", patient.getSimplifiedName(), severeDehydratedQuery.result)
            if (isSevereDehydrated) {
                diagnosis.append(treatmentsForDisease(patient.age, "severe_dehydration", prolog))
            } else {
                val moderateDehydrationQuery = prolog.query("symptom(Patient, moderate_dehydration).")
                val isModerateDehydrated = exists("Patient", patient.getSimplifiedName(), moderateDehydrationQuery.result)
                if (isModerateDehydrated) {
                    diagnosis.append(treatmentsForDisease(patient.age, "moderate_dehydration", prolog))
                } else {
                    diagnosis.append(treatmentsForDisease(patient.age, "mild_dehydration", prolog))
                }
            }

        }
        knowledgeFile.delete()
        return diagnosis.toString()
    }

    private fun treatmentsForDisease(age: String, disease: String, interpreter: Projog): String {
        val treatmentQuery = interpreter.query("treatment($disease, T).")
        val knownTreatments = findAll("T", treatmentQuery.result)
        val collect = StringBuilder()
        knownTreatments.map { treatment: String ->
            collect
                    .append("\t-"+normalize(treatment))
                    .append(": ")
            val doseQuery = interpreter.query("dose($age, $treatment, D).")
            findAll("D", doseQuery.result).forEach { collect.append("\t" + it).append("\n") }
        }
        return collect.toString()
    }

    private fun exists(variableName: String, expected: String, result: QueryResult): Boolean {
        while (result.next()) {
            if (result.getTerm(variableName).name == expected)
                return true
        }
        return false
    }

    private fun findAll(variableName: String, result: QueryResult): List<String> {
        val results = mutableListOf<String>()
        while (result.next()) {
            results += result.getTerm(variableName).name
        }
        return results
    }

    private fun insertFactsIntoKnowledgeFile(patient: Patient): File {
        val facts = patient.symptoms().joinToString("\n")
        val knowledge = rules + facts
        val tmpFile = createTempFile()
        writeToFile(knowledge, tmpFile)
        return tmpFile
    }

    private fun writeToFile(content: String, file: File) {
        BufferedWriter(FileWriter(file)).use {
            it.write(content)
        }
    }

    private fun normalize(s: String): String {
        return s.replace('_', ' ').capitalize()
    }

    companion object {
        val rules: String = BufferedReader(InputStreamReader(PatientController::class.java.getResourceAsStream("/knowledge.pl")))
                .lines()
                .collect(Collectors.joining("\n")) + "\n"
    }
}