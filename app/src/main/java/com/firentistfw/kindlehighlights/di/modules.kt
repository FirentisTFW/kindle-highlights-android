package com.firentistfw.kindlehighlights.di

import androidx.room.Room
import com.firentistfw.kindlehighlights.data.datasource.highlights.HighlightsDataSource
import com.firentistfw.kindlehighlights.data.datasource.highlights.LocalHighlightsDataSource
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.storage.AppDatabase
import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsViewModel
import com.firentistfw.kindlehighlights.ui.main.HighlightListViewModel
import com.firentistfw.kindlehighlights.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataSourceModule = module {
    single<HighlightsDataSource> { LocalHighlightsDataSource() }
}

val localStorageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "kindle-highlights-database"
        ).build()
    }

    single<HighlightsDao> { get<AppDatabase>().highlightsDao() }
}

val repositoryModule = module {
    single { HighlightsRepository(get()) }
}

val viewModelModule = module {
    viewModel { HighlightDetailsViewModel() }
    viewModel { HighlightListViewModel(get()) }
    viewModel { MainViewModel() }
}