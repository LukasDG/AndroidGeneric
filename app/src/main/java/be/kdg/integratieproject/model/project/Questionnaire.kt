package be.kdg.integratieproject.model.project

import java.util.ArrayList

data class Questionnaire(val id: Int,
                         val name: String,
                         val questionAmount: Int,
                         val confirmed: Boolean,
                         val project: Project,
                         val questions: ArrayList<Question>)