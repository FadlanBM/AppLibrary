package com.example.loremperpus.core.di


import com.example.loremperpus.ui.auth.AuthViewModel
import com.example.loremperpus.ui.ui.list_book.ListBookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { AuthViewModel(get()) }
    viewModel { ListBookViewModel(get()) }
}