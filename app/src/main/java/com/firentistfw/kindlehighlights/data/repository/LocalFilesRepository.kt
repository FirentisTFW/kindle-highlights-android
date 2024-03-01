package com.firentistfw.kindlehighlights.data.repository

import android.content.ContentResolver
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class LocalFilesRepository(private val contentResolver: ContentResolver) {
    suspend fun readFileContent(uri: Uri): String = withContext(Dispatchers.IO) {
        val inputStream = contentResolver.openInputStream(uri)
        return@withContext inputStream?.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream))
            val content = StringBuilder()
            var line = reader.readLine()
            while (line != null) {
                content.append(line).append("\n")
                line = reader.readLine()
            }
            return@use content.toString()
        } ?: throw IllegalArgumentException("Unable to open input stream for URI: $uri")
    }
}