package com.example.dscar.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dscar.R
import com.example.dscar.databinding.ActivityViewAgendamentosBinding
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase

// Modelo de dados (Appointment)
data class Appointment(
    val id: String = "",
    val userName: String = "",
    val carModel: String = "",
    val serviceType: String = "",
    val date: String = "",
    val time: String = ""
)

class ViewAgendamentosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewAgendamentosBinding
    private lateinit var database: DatabaseReference
    private lateinit var adapter: AppointmentAdapter
    private val appointmentsList = mutableListOf<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityViewAgendamentosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o Firebase Database
        database = FirebaseDatabase.getInstance().getReference("bookings")

        // Configura o RecyclerView
        binding.rvAppointments.layoutManager = LinearLayoutManager(this)
        adapter = AppointmentAdapter(appointmentsList)
        binding.rvAppointments.adapter = adapter

        // Recupera os agendamentos do Firebase
        fetchAppointments()
    }

    private fun fetchAppointments() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                appointmentsList.clear()
                if (snapshot.exists()) {
                    for (appointmentSnapshot in snapshot.children) {
                        val appointment = appointmentSnapshot.getValue(Appointment::class.java)
                        appointment?.let { appointmentsList.add(it) }
                    }
                    adapter.notifyDataSetChanged()
                    binding.tvNoAppointments.visibility = View.GONE
                } else {
                    binding.tvNoAppointments.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Lida com erros de banco de dados
            }
        })
    }
}
