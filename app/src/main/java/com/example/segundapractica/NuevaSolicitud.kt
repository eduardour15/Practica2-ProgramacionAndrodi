package com.example.segundapractica

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.segundapractica.databinding.ActivityNuevaSolicitudBinding

class NuevaSolicitud : AppCompatActivity() {
    lateinit var binding: ActivityNuevaSolicitudBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevaSolicitudBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_secundario, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.ic_confirmar)
        {
            Toast.makeText(this,"Opcion Nuevo cliente", Toast.LENGTH_SHORT).show()
            AgregarValor()
        }
        if(item.itemId==R.id.ic_cancelar)
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun AgregarValor() {
        val clienteActualizado = infoCliente(
            binding.idNombre.text.toString(),
            binding.idApellido.text.toString(),
            "",
            binding.idTelefono.text.toString(),
            binding.idCedula.text.toString(),
            binding.idOcupacion.text.toString(),
            binding.idDireccion.text.toString()
        )
        InformacionProvider.listadoClientes.add(clienteActualizado)

        // Iniciar la actividad DetalleCliente y pasar el objeto infoCliente actualizado a través del Intent
        val intent = Intent(this, InformacionCrediticia::class.java)
        intent.putExtra("NOMBRE_CLIENTE",clienteActualizado.name)
        intent.putExtra("APELLIDO_CLIENTE",clienteActualizado.lastname)
        startActivity(intent)

        // Cerrar la actividad actual
        finish()
    }
}