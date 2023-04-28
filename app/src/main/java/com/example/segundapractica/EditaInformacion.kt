package com.example.segundapractica

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class EditaInformacion : AppCompatActivity() {
    private var sexoSeleccionado: String = ""
    private lateinit var clienteSeleccionado: infoCliente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_informacion)

        val sexos = arrayListOf("Masculino", "Femenino")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sexos)
        val spinner: Spinner = findViewById(R.id.spinner_sexo)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                sexoSeleccionado = sexos[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Aún no seleccionado", Toast.LENGTH_SHORT).show()
            }

        }

        // Obtener la información del cliente seleccionado del Intent
        clienteSeleccionado = intent.getSerializableExtra("CLIENTE_SELECCIONADO") as infoCliente

        inicializarValores(clienteSeleccionado)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_secundario, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_confirmar) {
            Toast.makeText(this, "Opcion Confirmar", Toast.LENGTH_SHORT).show()
            actualizarValore(clienteSeleccionado)
        }
        if (item.itemId == R.id.ic_cancelar) {
            Toast.makeText(this, "Opcion cancelar", Toast.LENGTH_SHORT).show()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun inicializarValores(clienteSeleccionado: infoCliente) {
        // Obtener las referencias a los TextViews de la vista de la actividad
        val nombre = findViewById<TextView>(R.id.idNombre)
        val apellido = findViewById<TextView>(R.id.idApellido)
        val direccion = findViewById<TextView>(R.id.idDireccion)
        val telefono = findViewById<TextView>(R.id.idTelefono)
        val cedula = findViewById<TextView>(R.id.idCedula)
        val ocupacion = findViewById<TextView>(R.id.idOcupacion)

        // Mostrar los datos del cliente seleccionado en los TextViews correspondientes
        nombre.text = clienteSeleccionado.name
        apellido.text = clienteSeleccionado.lastname
        direccion.text = clienteSeleccionado.address
        cedula.text = clienteSeleccionado.id
        ocupacion.text = clienteSeleccionado.occupation
        telefono.text = clienteSeleccionado.phone
    }

    private fun actualizarValore(clienteSeleccionado: infoCliente) {
        // Recuperar los datos actualizados del formulario
        val nombre = findViewById<TextView>(R.id.idNombre).text.toString()
        val apellido = findViewById<TextView>(R.id.idApellido).text.toString()
        val direccion = findViewById<TextView>(R.id.idDireccion).text.toString()
        val cedula = findViewById<TextView>(R.id.idCedula).text.toString()
        val ocupacion = findViewById<TextView>(R.id.idOcupacion).text.toString()
        val telefono = findViewById<TextView>(R.id.idTelefono).text.toString()
        // Actualizar los datos del objeto infoCliente correspondiente en la lista listadoClientes
        val indiceClienteSeleccionado =
            InformacionProvider.listadoClientes.indexOf(clienteSeleccionado)
        val clienteActualizado =
            infoCliente(nombre, apellido, "", telefono, cedula, ocupacion, direccion)
        InformacionProvider.listadoClientes[indiceClienteSeleccionado] = clienteActualizado

        // Iniciar la actividad DetalleCliente y pasar el objeto infoCliente actualizado a través del Intent
        val intent = Intent(this, VisualizaCliente::class.java)
        intent.putExtra("CLIENTE_ACTUALIZADO", clienteActualizado)
        startActivity(intent)

        // Cerrar la actividad actual
        finish()
    }

}