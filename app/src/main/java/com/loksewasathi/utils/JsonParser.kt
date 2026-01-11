package com.loksewasathi.utils

import android.content.Context
import com.google.gson.Gson
import com.loksewasathi.models.QuestionSet
import java.io.IOException

object JsonParser {
    private val gson = Gson()
    
    fun loadQuestionsFromAssets(context: Context, fileName: String): QuestionSet? {
        return try {
            val jsonString = context.assets.open("questions/$fileName").bufferedReader().use { it.readText() }
            gson.fromJson(jsonString, QuestionSet::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
