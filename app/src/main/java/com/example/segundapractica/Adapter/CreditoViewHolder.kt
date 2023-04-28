package com.example.segundapractica.Adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.segundapractica.*
import com.example.segundapractica.databinding.ItemClientesBinding
import com.example.segundapractica.databinding.ItemPrestamosBinding

class CreditoViewHolder(
    view: View, private val adapter: CreditoAdapter
) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPrestamosBinding.bind(view)

    fun render(infoCreditoModel: infoCredito) {
        binding.tvname.text = infoCreditoModel.name +" "+ infoCreditoModel.lastname
        binding.tvMonto.text = infoCreditoModel.amount.toString()
        binding.tvPlazo.text = infoCreditoModel.period.toString()
    }
}