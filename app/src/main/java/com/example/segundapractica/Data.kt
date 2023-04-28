package com.example.segundapractica

data class infoCliente(
    var name: String,
    var lastname: String,
    val sex: String,
    var phone: String,
    var id: String,
    var occupation: String,
    var address: String
) : java.io.Serializable

data class infoCredito(
    var name: String,
    var lastname: String,
    var amount: String,
    var period: String
) : java.io.Serializable