package com.firentistfw.kindlehighlights.di

import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsViewModel
import com.firentistfw.kindlehighlights.ui.main.HighlightListViewModel
import com.firentistfw.kindlehighlights.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {HighlightDetailsViewModel()}
    viewModel {HighlightListViewModel()}
    viewModel {MainViewModel()}
}