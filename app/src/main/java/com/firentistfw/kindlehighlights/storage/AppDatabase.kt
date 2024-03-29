package com.firentistfw.kindlehighlights.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firentistfw.kindlehighlights.storage.dao.BooksDao
import com.firentistfw.kindlehighlights.storage.dao.CategoriesDao
import com.firentistfw.kindlehighlights.storage.dao.HighlightCategoryCrossRefDao
import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.storage.dao.SelectionsDao
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.storage.tables.HighlightCategoryCrossRef
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition

@Database(
    entities = [
        DBBook::class,
        DBCategory::class,
        DBHighlight::class,
        HighlightCategoryCrossRef::class,
        SelectionCondition::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao

    abstract fun categoriesDao(): CategoriesDao

    abstract fun highlightsDao(): HighlightsDao

    abstract fun highlightCategoriesCrossRefDao(): HighlightCategoryCrossRefDao

    abstract fun selectionConditionsDao(): SelectionsDao
}
