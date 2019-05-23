package be.kdg.integratieproject.model.project

data class Question(val id: Int,
                    val questionType: Int,
                    val question: String,
                    val options: ArrayList<String>)