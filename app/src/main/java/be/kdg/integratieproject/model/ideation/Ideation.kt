package be.kdg.integratieproject.model.ideation

data class Ideation(val ideationId: Int,
                    val question: String,
                    val numberOfLikes: Int,
                    val numberOfIdeas: Int)