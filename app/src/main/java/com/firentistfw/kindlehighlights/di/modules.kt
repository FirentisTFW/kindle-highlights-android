package com.firentistfw.kindlehighlights.di

import androidx.room.Room
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.data.repository.SelectionConditionsRepository
import com.firentistfw.kindlehighlights.storage.AppDatabase
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsViewModel
import com.firentistfw.kindlehighlights.ui.main.HighlightListViewModel
import com.firentistfw.kindlehighlights.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val localStorageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "kindle-highlights-database"
        ).build()
    }

    single { get<AppDatabase>().booksDao() }
    single { get<AppDatabase>().categoriesDao() }
    single { get<AppDatabase>().highlightsDao() }
    single { get<AppDatabase>().highlightCategoriesCrossRefDao() }
    single { get<AppDatabase>().selectionConditionsDao() }
}

val repositoryModule = module {
    single { HighlightsRepository(get(), get()) }
    single { SelectionConditionsRepository(get()) }
}

val viewModelModule = module {
    viewModel { HighlightDetailsViewModel() }
    viewModel { HighlightListViewModel(get()) }
    viewModel { MainViewModel() }
}

