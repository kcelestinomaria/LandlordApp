package com.example.navigationuidemo2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigationuidemo2.databinding.AddtenantActivityBinding

class AddTenant : AppCompatActivity() {

    private lateinit var binding: AddtenantActivityBinding
    private val viewModel: TenantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.addtenant_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val fullName = binding.fullName
        val unitNumber = binding.unitNumber
        val fullRent = binding.fullRent

        binding.button.setOnClickListener {
            val name = fullName.text.toString()
            val unit = unitNumber.text.toString()
            val rent = fullRent.text.toString()

            viewModel.addTenant(name, unit, rent)

            fullName.text.clear()
            unitNumber.text.clear()
            fullRent.text.clear()
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
