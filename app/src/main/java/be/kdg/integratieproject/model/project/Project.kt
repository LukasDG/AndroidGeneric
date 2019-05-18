package be.kdg.integratieproject.model.project

import android.graphics.Bitmap
import be.kdg.integratieproject.model.common.Place
import java.util.*
import kotlin.collections.ArrayList

data class Project(val projectId: Int,
                   val name: String,
                   val projectImage: Bitmap,
                   val description: String,
                   val startDate: Date,
                   val endDate: Date,
                   val numberOfLikes: Int,
                   val numberOfIdeations: Int,
                   val percentageCompleted: Int/*,
                   val place: Place,
                   val ideations: ArrayList<Ideation>,
                   val questionnaires: ArrayList<Questionnaire>*/)