package com.example.segundapractica

import android.os.Bundle
import android.app.DatePickerDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.DatePicker
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.segundapractica.databinding.ActivityInformacionCrediticiaBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import java.util.concurrent.TimeUnit

class InformacionCrediticia : AppCompatActivity() {
    private lateinit var binding: ActivityInformacionCrediticiaBinding // Variable para almacenar la referencia al binding de la vista
    private var interesSeleccionado: Double =
        0.0 // Variable para almacenar el interés seleccionado por el usuario
    private var fechaInicio: Calendar =
        Calendar.getInstance() // Variable para almacenar la fecha de inicio del préstamo

    private var nombreCliente: String? = null
    private var apellidoCliente: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityInformacionCrediticiaBinding.inflate(layoutInflater) // Inflar el layout y asignarlo al binding
        setContentView(binding.root) // Establecer la vista del binding como la vista principal de la actividad

        // Configurar DatePickerDialog para la fecha de inicio
        val year = fechaInicio.get(Calendar.YEAR)
        val month = fechaInicio.get(Calendar.MONTH)
        val dayOfMonth = fechaInicio.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth ->
                fechaInicio.set(
                    year, monthOfYear, dayOfMonth
                ) // Actualizar la fecha de inicio con la fecha seleccionada por el usuario
                val date =
                    "${dayOfMonth}/${monthOfYear + 1}/${year}" // Formatear la fecha seleccionada como una cadena
                binding.idFechaInicio.setText(date) // Establecer la fecha seleccionada en el EditText correspondiente
                updateCalculations() // Actualizar los cálculos del préstamo
            }, year, month, dayOfMonth
        )
        binding.idFechaInicio.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                datePickerDialog.show() // Mostrar el DatePickerDialog cuando el usuario toque el EditText correspondiente
            }
            true
        }

        // Configurar DatePickerDialog para la fecha de final
        val fechaFinal = Calendar.getInstance()
        val datePickerDialogFinal = DatePickerDialog(
            this, { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth ->
                fechaFinal.set(
                    year, monthOfYear, dayOfMonth
                ) // Actualizar la fecha de final con la fecha seleccionada por el usuario
                val date =
                    "${dayOfMonth}/${monthOfYear + 1}/${year}" // Formatear la fecha seleccionada como una cadena
                binding.idFechaFinal.setText(date) // Establecer la fecha seleccionada en el EditText correspondiente
                updateCalculations() // Actualizar los cálculos del préstamo
            }, year, month, dayOfMonth
        )

        setupSpinner() // Configurar el spinner de intereses
        updateCalculations() // Actualizar los cálculos del préstamo
        binding.idMonto.addTextChangedListener {
            updateCalculations() // Actualizar los cálculos del préstamo cuando el usuario cambie el monto
        }
        binding.idPlazo.addTextChangedListener {
            updateCalculations() // Actualizar los cálculos del préstamo cuando el usuario cambie el plazo
        }

        // Obtener datos del Intent
        nombreCliente = intent.getStringExtra("NOMBRE_CLIENTE")
        apellidoCliente = intent.getStringExtra("APELLIDO_CLIENTE")

    }

    private fun setupSpinner() {
        val intereses = arrayListOf(15.0, 20.0, 25.0) // Lista de intereses disponibles
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, intereses
        ) // Adaptador para el spinner de intereses
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Establecer el layout de los elementos del spinner
        binding.spinnerInteres.adapter = adapter // Asignar el adaptador al spinner de intereses

        binding.spinnerInteres.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    interesSeleccionado =
                        intereses[position] // Actualizar el interés seleccionado por el usuario
                    updateCalculations() // Actualizar los cálculos del préstamo
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Snackbar.make(binding.root, R.string.nothing_selected, Snackbar.LENGTH_SHORT)
                        .show() // Mostrar un mensaje de error cuando el usuario no ha seleccionado ningún interés
                }
            }
    }

    // Esta función se encarga de actualizar los cálculos y mostrar los resultados en la pantalla
    private fun updateCalculations() {
        // Se obtiene el monto del crédito y el plazo en meses desde los campos de texto
        val montoCredito = binding.idMonto.text.toString().toDoubleOrNull() ?: 0.0
        val plazoCreditoMeses = binding.idPlazo.text.toString().toIntOrNull() ?: 0

        // Se convierte el plazo a días, asumiendo que cada mes tiene 30 días
        // val plazoCreditoDias = plazoCreditoMeses * 30

        // Se calcula el monto total a pagar y se muestra en la pantalla
        val montoPagar = calculaMonto(montoCredito, interesSeleccionado)
        binding.idMontoPagar.text = String.format("%.2f", montoPagar)

        // Se calcula la diferencia en días entre las fechas de inicio y final
        val fechaFinal = binding.idFechaFinal.text.toString()
        val dias = if (fechaFinal.isNotEmpty()) {
            val fecha = fechaFinal.split("/").map { it.toInt() }
            val fechaCal = Calendar.getInstance().apply {
                set(fecha[2], fecha[1] - 1, fecha[0])
            }
            val diferencia = fechaCal.timeInMillis - fechaInicio.timeInMillis
            TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS)
        } else {
            0
        }
        //Se calcula la cuota a pagar
        val cuota = calculaCuota(montoCredito, interesSeleccionado, plazoCreditoMeses)

        // Se muestra la cuota mensual en la pantalla
        binding.idMontoCuota.text = String.format("%.2f", cuota)

        // Se calcula y muestra la fecha final del crédito en la pantalla
        val fechaFinalCalculada = fechaInicio.clone() as Calendar
        fechaFinalCalculada.add(Calendar.MONTH, plazoCreditoMeses)
        val date =
            "${fechaFinalCalculada.get(Calendar.DAY_OF_MONTH)}/${fechaFinalCalculada.get(Calendar.MONTH) + 1}/${
                fechaFinalCalculada.get(Calendar.YEAR)
            }"
        binding.idFechaFinal.setText(date)
    }

    // Esta función calcula la cuota mensual constante a pagar para un crédito con el monto y la tasa de interés dados
    private fun calculaCuota(montoCredito: Double, interes: Double, plazo: Int): Double {
        val cuota = (montoCredito + (montoCredito *(interes/100) ))/plazo
        return cuota
    }

    // Esta función calcula el monto total a pagar por un crédito con el monto y la tasa de interés dados, asumiendo un plazo de 12 meses
    private fun calculaMonto(montoCredito: Double, interes: Double): Double {
        val monto = montoCredito + (montoCredito*(interes/100))
        return monto
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
            val intent: Intent = Intent(this,PrestamosActivos::class.java)
            intent.putExtra("NOMBRE_CLIENTE", nombreCliente)
            intent.putExtra("APELLIDO_CLIENTE",apellidoCliente)
            intent.putExtra("MONTO",binding.idMonto.text.toString())
            intent.putExtra("PLAZO",binding.idPlazo.text.toString())
            startActivity(intent)
        }
        if(item.itemId==R.id.ic_cancelar)
        {
           finish()
        }
        return super.onOptionsItemSelected(item)
    }
}