package com.example.segundapractica

class InformacionProvider {
    companion object{
        val listadoClientes = mutableListOf<infoCliente>(
            infoCliente("Leonel", "Messi","","","","",""),
            infoCliente("Pedri", "Gonzales","","","","",""),
            infoCliente("Fátima", "Parrales","","","","",""),
            infoCliente("Angie", "Castillo","","","","",""),
            infoCliente("Jocsan", "Fonseca","","","","",""),
            infoCliente("Xavi", "Hernández","","","","","")
        )
        val listadoCreditos = mutableListOf<infoCredito>(
            infoCredito("Leonel","Messi","25000.5","48"),
            infoCredito("Xavi","Hernández","33000.8","72"),
            infoCredito("Jocsan","Fonseca","2500.5","12")
        )
    }
}