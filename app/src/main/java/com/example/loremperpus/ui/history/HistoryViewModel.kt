package com.example.loremperpus.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.loremperpus.core.data.repository.AppRepository
import com.example.loremperpus.core.data.source.remote.request.ListLendingRequest

class HistoryViewModel(val repo: AppRepository) : ViewModel() {
    fun getDetailHistoryLending(token:String,id:Int)=repo.getDetailHistoryLending(token,id).asLiveData()
    fun getLending(token:String)=repo.getLending(token).asLiveData()
}