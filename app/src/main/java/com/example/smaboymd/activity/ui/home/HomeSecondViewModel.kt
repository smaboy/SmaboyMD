package com.example.smaboymd.activity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeSecondViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Second Fragment"
    }
    val text: LiveData<String> = _text
}