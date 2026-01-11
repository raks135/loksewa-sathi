package com.loksewasathi.models

import androidx.compose.ui.graphics.Color

data class Topic(
    val id: String,
    val nameEnglish: String,
    val nameNepali: String,
    val icon: String,
    val gradientColors: List<Color>,
    val questionCount: Int,
    val description: String,
    val jsonFileName: String
)
