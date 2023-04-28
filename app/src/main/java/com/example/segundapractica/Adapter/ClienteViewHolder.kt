package com.example.segundapractica.Adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.segundapractica.*
import com.example.segundapractica.databinding.ItemClientesBinding


class ClienteViewHolder(
    view: View, private val adapter: ClienteAdapter
) : ViewHolder(view) {
    private val binding = ItemClientesBinding.bind(view)

    fun render(infoClienteModel: infoCliente) {
        binding.tvname.text = infoClienteModel.name
        binding.lastNameTv.text = infoClienteModel.lastname

        binding.ivDelete.setOnClickListener {
            val builder = AlertDialog.Builder(binding.root.context)
            builder.setTitle("Eliminar cliente")
            builder.setMessage("¿Estás seguro de que deseas eliminar este cliente?")
            builder.setPositiveButton("Sí") { _, _ ->
                Toast.makeText(
                    binding.root.context, "Eliminando", Toast.LENGTH_SHORT
                ).show()
                InformacionProvider.listadoClientes.removeAt(adapterPosition)
                adapter.notifyDataSetChanged()
            }
            builder.setNegativeButton("No", null)
            val dialog = builder.create()
            dialog.show()
        }
        binding.ivEdit.setOnClickListener {
            val intent = Intent(binding.root.context, EditaInformacion::class.java)
            intent.putExtra("CLIENTE_SELECCIONADO", infoClienteModel)
            binding.root.context.startActivity(intent)
        }
        itemView.setOnClickListener{
            val intent = Intent(binding.root.context,InformacionCompleta::class.java)
            intent.putExtra("CLIENTE_SELECCIONADO", infoClienteModel)
            binding.root.context.startActivity(intent)
        }

    }
}