package com.example.loremperpus.ui.ui.list_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.loremperpus.core.data.repository.AppRepository

class ListBookViewModel(val repo: AppRepository) : ViewModel() {
    fun getBook(token:String)=repo.getBook(token).asLiveData()
    fun getlistcategory(token:String,id:Int)=repo.getlistcategory(token,id).asLiveData()
    fun getDetailData(token:String,id:Int)=repo.getDetailData(token,id).asLiveData()
}