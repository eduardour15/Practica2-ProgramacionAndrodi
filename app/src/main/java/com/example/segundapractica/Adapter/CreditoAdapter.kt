package com.example.segundapractica.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.segundapractica.R
import com.example.segundapractica.infoCredito

class CreditoAdapter(private val informacionCredito: List<infoCredito>) : RecyclerView.Adapter<CreditoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CreditoViewHolder(
            layoutInflater.inflate(R.layout.item_prestamos,parent,false),
            this
        )
    }
    override fun onBindViewHolder(holder: CreditoViewHolder, position: Int) {
        val item = informacionCredito[position]
        holder.render(item)
    }
    override fun getItemCount(): Int= informacionCredito.size
}