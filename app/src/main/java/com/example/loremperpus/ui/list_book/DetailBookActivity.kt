package com.example.loremperpus.ui.list_book

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.AdapterRV.BookRV
import com.example.loremperpus.AdapterRV.CategoryRV
import com.example.loremperpus.R
import com.example.loremperpus.core.data.source.models.Category
import com.example.loremperpus.core.data.source.remote.network.State
import com.example.loremperpus.databinding.ActivityDetailBookBinding
import com.example.loremperpus.item.ListBook
import com.example.loremperpus.item.ListCategory
import com.example.loremperpus.util.CartSharePreft
import com.example.loremperpus.util.Constants
import com.example.loremperpus.util.Prefs
import com.inyongtisto.myhelper.extension.toastWarning
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailBookActivity : AppCompatActivity() {
    private val viewModel: ListBookViewModel by viewModel()
    private lateinit var binding:ActivityDetailBookBinding
    private lateinit var rvListCategory:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvListCategory=binding.CategoryListRv

        val id=intent.getIntExtra("id_Book",0)
        getDetailBook(id)
        getListCategory(id)

        binding.addAddCart.setOnClickListener {
            var IsStatus=false
            val idExis=CartSharePreft(this).getId()
            for (i in idExis.indices){
                val ids=idExis[i]
                if (ids==id.toString()){
                    showDialogCart(this,"Warning","Book sudah terdapat di cart")
                    IsStatus=true
                }
            }
            if (!IsStatus){
                CartSharePreft(this).saveId(id.toString())
                showDialogCart(this,"Success","Success Add Cart")
            }
        }
    }

    private fun getListCategory(id:Int){
        binding.RLDetailView.isVisible=false
        viewModel.getlistcategory(Prefs.token,id).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    val bookResponse = it.data
                    val booklist= mutableListOf<ListCategory>()
                    if (bookResponse != null) {
                        val books = bookResponse.Categories
                        if (books != null) {
                            for (book in books) {
                                val id=book.ID
                                val name = book.name
                                Log.e("Error",name)
                                booklist.add(ListCategory(id,name))
                            }
                            val adapter=CategoryRV(booklist,this)
                            rvListCategory.adapter=adapter
                        }
                    }
                }

                State.ERROR -> {
                    toastWarning(it?.message.toString())
                    binding.RLDetailView.isVisible=true
                }
                State.LOADING -> {

                }
            }
        }
    }
    private fun getDetailBook(id:Int){
        viewModel.getDetailData(Prefs.token,id).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    val bookResponse = it.data?.data
                    val title=bookResponse?.title
                    val author=bookResponse?.author
                    val desc=bookResponse?.description
                    val img=bookResponse?.img
                    binding.bookTitle.text=title
                    binding.bookAuthorTv.text=author
                    binding.bookDesc.text=desc
                    Picasso.get().load(Constants.BASE_Image+img).into(binding.bookImage)
                    binding.RLDetailView.isVisible=true
                }

                State.ERROR -> {
                    toastWarning(it?.message.toString())
                    binding.RLDetailView.isVisible=true
                }
                State.LOADING -> {

                }
            }
        }
    }

    fun showDialogCart(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)

        // Set the dialog title and message
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
            }

        // Create the AlertDialog object and show it
        val dialog = builder.create()
        dialog.show()
    }
}