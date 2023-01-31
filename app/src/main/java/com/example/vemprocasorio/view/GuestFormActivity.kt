package com.example.vemprocasorio.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.vemprocasorio.R
import com.example.vemprocasorio.databinding.ActivityGuestFormBinding
import com.example.vemprocasorio.model.GuestModel
import com.example.vemprocasorio.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(0, name, presence)
            viewModel.insert(model)
        }
    }
}