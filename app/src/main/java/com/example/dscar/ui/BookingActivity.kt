package com.example.dscar.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dscar.R
import com.example.dscar.databinding.ActivityBookingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference

        // Configura o botão para salvar o agendamento
        binding.btnSubmitBooking.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            val carModel = binding.etCarModel.text.toString()
            val serviceType = binding.etServiceType.text.toString()
            val date = binding.etDate.text.toString()
            val time = binding.etTime.text.toString()

            // Verifica se todos os campos foram preenchidos
            if (userName.isNotEmpty() && carModel.isNotEmpty() && serviceType.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
                saveBooking(userName, carModel, serviceType, date, time)
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveBooking(userName: String, carModel: String, serviceType: String, date: String, time: String) {
        // Gera um ID único para o agendamento
        val bookingId = database.child("bookings").push().key ?: return

        // Cria um objeto de agendamento
        val booking = mapOf(
            "id" to bookingId,
            "userName" to userName,
            "carModel" to carModel,
            "serviceType" to serviceType,
            "date" to date,
            "time" to time
        )

        // Salva o agendamento no Firebase
        database.child("bookings").child(bookingId).setValue(booking)
            .addOnSuccessListener {
                Toast.makeText(this, "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao salvar o agendamento.", Toast.LENGTH_SHORT).show()
            }
    }
}
