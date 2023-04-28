package com.example.segundapractica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.segundapractica.Adapter.ClienteAdapter
import com.example.segundapractica.databinding.ActivityVisualizaClienteBinding

class VisualizaCliente : AppCompatActivity() {
   lateinit var binding: ActivityVisualizaClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizaClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }
    private fun initRecyclerView(){
        binding.recyclerClientes.layoutManager = LinearLayoutManager(this)
        binding.recyclerClientes.adapter = ClienteAdapter(InformacionProvider.listadoClientes)
    }
}