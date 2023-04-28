package com.example.segundapractica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.segundapractica.Adapter.CreditoAdapter
import com.example.segundapractica.databinding.ActivityPrestamosActivosBinding

class PrestamosActivos : AppCompatActivity() {
    lateinit var binding: ActivityPrestamosActivosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrestamosActivosBinding.inflate(layoutInflater)
        setContentView(binding.root)
       agregarNuevoCreditoDesdeIntent()
        initRecyclerView()
    }
    private fun agregarNuevoCreditoDesdeIntent() {
        val nombre = intent.getStringExtra("NOMBRE_CLIENTE")
        val apellido = intent.getStringExtra("APELLIDO_CLIENTE")
        val cantidad = intent.getStringExtra("MONTO")
        val plazo = intent.getStringExtra("PLAZO")

        if (nombre != null && apellido != null && cantidad != null && plazo != null) {
            val nuevoCredito = infoCredito(nombre, apellido, cantidad, plazo)
            InformacionProvider.listadoCreditos.add(nuevoCredito)
        }
    }
    private fun initRecyclerView() {
        binding.recyclerCreditos.layoutManager = LinearLayoutManager(this)
        binding.recyclerCreditos.adapter = CreditoAdapter(InformacionProvider.listadoCreditos)
    }
}