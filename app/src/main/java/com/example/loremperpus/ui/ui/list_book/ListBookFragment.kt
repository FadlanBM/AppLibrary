package com.example.loremperpus.ui.ui.list_book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.AdapterRV.BookRV
import com.example.loremperpus.core.data.source.models.Book
import com.example.loremperpus.core.data.source.remote.network.State
import com.example.loremperpus.core.data.source.remote.request.LoginRequest
import com.example.loremperpus.databinding.FragmentListBookBinding
import com.example.loremperpus.item.ListBook
import com.example.loremperpus.ui.MenuActivity
import com.example.loremperpus.ui.auth.AuthViewModel
import com.example.loremperpus.util.Prefs
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toastWarning
import org.json.JSONArray
import org.json.JSONException
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBookFragment : Fragment() {

    private var _binding: FragmentListBookBinding? = null
    private val viewModel: ListBookViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBookBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView=binding.rvListBarang
        getBook()

        return root
    }

    private fun getBook(){
        viewModel.getBook(Prefs.token).observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> {
                    val bookResponse = it.data
                    val booklist= mutableListOf<ListBook>()
                    if (bookResponse != null) {
                        val books = bookResponse.data
                        if (books != null) {
                            for (book in books) {
                                val id=book.ID
                                val author = book.author
                                val title = book.title
                                booklist.add(ListBook(title,author,id))
                            }
                            val adapter= BookRV(booklist,requireContext())
                            recyclerView.adapter=adapter
                        }
                    }
                }

                State.ERROR -> {
                    toastWarning(it?.message.toString())
                }
                State.LOADING -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}