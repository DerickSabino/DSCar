package com.example.dscar.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dscar.R

// Adaptador para exibir a lista de agendamentos
class AppointmentAdapter(private val appointments: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    // Classe interna para o ViewHolder
    class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUserName: TextView = view.findViewById(R.id.tvUserName)
        val tvCarModel: TextView = view.findViewById(R.id.tvCarModel)
        val tvServiceType: TextView = view.findViewById(R.id.tvServiceType)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
    }

    // Criação do ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    // Associação de dados ao ViewHolder
    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.tvUserName.text = "Cliente: ${appointment.userName}"
        holder.tvCarModel.text = "Carro: ${appointment.carModel}"
        holder.tvServiceType.text = "Serviço: ${appointment.serviceType}"
        holder.tvDate.text = "Data: ${appointment.date}"
        holder.tvTime.text = "Hora: ${appointment.time}"
    }

    // Número de itens na lista
    override fun getItemCount(): Int = appointments.size
}
