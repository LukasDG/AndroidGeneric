package be.kdg.integratieproject.model.project

data class User(val id: Int,
                val firstName: String,
                val lastName: String,
                val eMailAddress: String,
                val password: String,
                val verified: Boolean)