package com.example.loremperpus.ui.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.loremperpus.core.data.repository.AppRepository

class CartViewModel(val repo: AppRepository) : ViewModel() {
    fun getDetailData(token:String,id:Int)=repo.getDetailData(token,id).asLiveData()
}