package com.example.segundapractica.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.segundapractica.R
import com.example.segundapractica.infoCliente

class ClienteAdapter(private val informacionCliente: List<infoCliente>) : RecyclerView.Adapter<ClienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ClienteViewHolder(
            layoutInflater.inflate(R.layout.item_clientes,parent,false),
            this
        )
    }
    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val item = informacionCliente[position]
        holder.render(item)
    }
    override fun getItemCount(): Int= informacionCliente.size
}