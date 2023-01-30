package com.example.vemprocasorio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllGuestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is All Guests"
    }

    private val _text2 = MutableLiveData<String>().apply {
        value = "This is other home Fragment"
    }
    val text: LiveData<String> = _text
    val text2: LiveData<String> = _text2
}