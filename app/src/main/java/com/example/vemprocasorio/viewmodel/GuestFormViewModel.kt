package com.example.vemprocasorio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.vemprocasorio.model.GuestModel
import com.example.vemprocasorio.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel){
        repository.insert(guest)

    }

}