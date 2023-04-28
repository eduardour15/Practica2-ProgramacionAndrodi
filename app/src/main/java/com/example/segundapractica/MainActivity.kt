package com.example.segundapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.segundapractica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.nuevo_cliente)
        {
            Toast.makeText(this,"Opcion Nuevo cliente", Toast.LENGTH_SHORT).show()
            val intent:Intent= Intent(this,NuevaSolicitud::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.ver_prestamo)
        {
            Toast.makeText(this,"Opcion prestamo",Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this,PrestamosActivos::class.java)
            startActivity(intent)
        }
        if (item.itemId==R.id.ver_cliente){
            Toast.makeText(this,"Opcion ver cliente",Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this,VisualizaCliente::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}