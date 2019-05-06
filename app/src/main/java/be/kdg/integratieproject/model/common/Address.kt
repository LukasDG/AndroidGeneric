package be.kdg.integratieproject.model.common

data class Address(val id: Int,
                   val street: String,
                   val number: Int,
                   val place: Place)