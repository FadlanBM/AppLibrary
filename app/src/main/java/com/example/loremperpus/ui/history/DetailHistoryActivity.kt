package com.example.loremperpus.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loremperpus.R
import com.example.loremperpus.core.data.source.remote.network.State
import com.example.loremperpus.util.Prefs
import com.inyongtisto.myhelper.extension.toastWarning
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHistoryActivity : AppCompatActivity() {
    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
    }

    private fun getListCategory(id:Int){
        val token="Bearer ${Prefs.token}"
        viewModel.getDetailHistoryLending(token,id).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                }

                State.ERROR -> {
                    toastWarning(it?.message.toString())

                }
                State.LOADING -> {

                }
            }
        }
    }
}