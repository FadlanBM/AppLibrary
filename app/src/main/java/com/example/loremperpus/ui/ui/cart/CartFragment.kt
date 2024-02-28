package com.example.loremperpus.ui.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loremperpus.core.data.source.remote.network.State
import com.example.loremperpus.databinding.FragmentCartBinding
import com.example.loremperpus.ui.ui.list_book.ListBookViewModel
import com.example.loremperpus.util.CartSharePreft
import com.example.loremperpus.util.Constants
import com.example.loremperpus.util.Prefs
import com.inyongtisto.myhelper.extension.toastWarning
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val viewModel: ListBookViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val id= CartSharePreft(requireContext()).getId()
        for (ids in id) {
            getDetailBook(ids.toInt())
        }
        return root
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