package com.example.loremperpus.ui.Lending

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.AdapterRV.ListLendingRV
import com.example.loremperpus.R
import com.example.loremperpus.core.data.source.remote.network.State
import com.example.loremperpus.databinding.ActivityLendingFormBinding
import com.example.loremperpus.item.ListCart
import com.example.loremperpus.ui.MenuActivity
import com.example.loremperpus.ui.list_book.ListBookViewModel
import com.example.loremperpus.util.CartSharePreft
import com.example.loremperpus.util.Prefs
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toastWarning
import org.koin.androidx.viewmodel.ext.android.viewModel

class LendingFormActivity : AppCompatActivity() {
    private val viewModel: ListBookViewModel by viewModel()
    private lateinit var binding:ActivityLendingFormBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLendingFormBinding.inflate(layoutInflater)
        setToolbar(binding.toolBarUpdatePeminjam,"Kembali")
        setContentView(binding.root)
        recyclerView=binding.rvcartBarang

        getDetailBook(binding)
    }

    private fun getDetailBook(binding:ActivityLendingFormBinding){
        val id= CartSharePreft(this).getId()
        val booklist= mutableListOf<ListCart>()
        for (ids in id) {
            viewModel.getDetailData(Prefs.token,ids.toInt()).observe(this) {
                when (it.state) {
                    State.SUCCESS -> {
                        val bookResponse = it.data?.data
                        val id=bookResponse?.ID.toString()
                        val title=bookResponse?.title.toString()
                        val author=bookResponse?.author.toString()
                        val releseTH=bookResponse?.year_published.toString()
                        val img=bookResponse?.img.toString()
                        booklist.add(ListCart(id,author,img,releseTH,title))
                        val adapter=ListLendingRV(booklist,this,binding)
                        recyclerView.adapter=adapter
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
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}