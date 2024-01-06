package com.firentistfw.kindlehighlights.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.storage.model.DBHighlight

@Database(entities = [DBHighlight::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun highlightsDao(): HighlightsDao
}
