package be.kdg.integratieproject.model.project

import be.kdg.integratieproject.model.common.Place
import java.util.*
import kotlin.collections.ArrayList

data class Project(val projectId: Int,
                   val name: String/*,
                   val description: String,
                   val startDate: Date,
                   val endDate: Date,
                   val place: Place,
                   val ideations: ArrayList<Ideation>,
                   val questionnaires: ArrayList<Questionnaire>*/)