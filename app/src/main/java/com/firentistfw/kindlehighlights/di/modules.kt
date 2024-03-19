package com.firentistfw.kindlehighlights.di

import androidx.room.Room
import com.firentistfw.kindlehighlights.data.repository.BooksRepository
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.data.repository.LocalFilesRepository
import com.firentistfw.kindlehighlights.data.repository.SelectionConditionsRepository
import com.firentistfw.kindlehighlights.storage.AppDatabase
import com.firentistfw.kindlehighlights.ui.addcategory.AddCategoryViewModel
import com.firentistfw.kindlehighlights.ui.categorylist.CategoryListViewModel
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsViewModel
import com.firentistfw.kindlehighlights.ui.main.MainViewModel
import com.firentistfw.kindlehighlights.ui.common.highlightlist.HighlightListViewModel
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.ManageHighlightCategoriesViewModel
import com.firentistfw.kindlehighlights.ui.randomgenerator.RandomGeneratorViewModel
import com.firentistfw.kindlehighlights.utils.KindleClippingsParser
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { androidContext().contentResolver }
}

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
    single { BooksRepository(get()) }
    single { CategoriesRepository(get(), get()) }
    single { HighlightsRepository(get(), get()) }
    single { LocalFilesRepository(get()) }
    single { SelectionConditionsRepository(get()) }
}

val servicesModule = module {
    single { KindleClippingsParser() }
}

val viewModelModule = module {
    viewModel { AddCategoryViewModel(get()) }
    viewModel { CategoryListViewModel(get()) }
    viewModel { HighlightDetailsViewModel(get()) }
    viewModel { HighlightListViewModel(get()) }
    viewModel { MainViewModel(get(), get(), get(), get()) }
    viewModel { ManageHighlightCategoriesViewModel(get()) }
    viewModel { RandomGeneratorViewModel(get(), get(), get()) }
}

