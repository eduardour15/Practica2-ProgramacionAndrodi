package com.example.segundapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.segundapractica.databinding.ActivityInformacionCompletaBinding

class InformacionCompleta : AppCompatActivity() {
    lateinit var binding: ActivityInformacionCompletaBinding
    private lateinit var clienteSeleccionado: infoCliente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionCompletaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtener la información del cliente seleccionado del Intent
        clienteSeleccionado = intent.getSerializableExtra("CLIENTE_SELECCIONADO") as infoCliente

        inicializarValores(clienteSeleccionado)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.tercer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_infCredito) {
            val intent: Intent = Intent(this, InformacionCrediticia::class.java)
            intent.putExtra("NOMBRE_CLIENTE", clienteSeleccionado.name)
            intent.putExtra("APELLIDO_CLIENTE",clienteSeleccionado.lastname)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun inicializarValores(clienteSeleccionado: infoCliente) {

        // Mostrar los datos del cliente seleccionado en los TextViews correspondientes
        binding.tvNombre.text = clienteSeleccionado.name
        binding.tvApellido.text = clienteSeleccionado.lastname
        binding.tvDireccion.text = clienteSeleccionado.address
        binding.tvCedula.text = clienteSeleccionado.id
        binding.tvOcupacion.text = clienteSeleccionado.occupation
        binding.tvTelefono.text = clienteSeleccionado.phone
    }
}