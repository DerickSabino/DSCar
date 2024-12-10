package com.example.dscar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dscar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurações dos botões
        binding.btnBookAppointment.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        binding.btnViewAppointments.setOnClickListener {
            startActivity(Intent(this, ViewAgendamentosActivity::class.java))
        }
    }
}
