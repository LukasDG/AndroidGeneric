package be.kdg.integratieproject.model.project

import java.util.ArrayList

data class Questionnaire(val questionnaireId: Int,
                         val name: String,
                         val questionAmount: Int,
                         val project: Project,
                         val questions: ArrayList<Question>)