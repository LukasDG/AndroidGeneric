package be.kdg.integratieproject.model.project

import android.graphics.Bitmap

data class ProjectBasic (
    val projectId: Int,
    val name: String,
    val projectImage: Bitmap,
    val numberOfLikes: Int,
    val numberOfIdeations: Int
)