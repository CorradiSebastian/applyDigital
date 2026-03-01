package com.sebastiancorradi.myapplication.utils

import java.util.concurrent.TimeUnit
import kotlin.ranges.contains

fun getAgeByTimeStamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - (timestamp * 1000)

    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)
    return when {
        // Menos de 60 minutos
        minutes < 60 -> "${minutes}m"

        // Entre 1 hora y 24 horas
        hours < 24 -> "${hours}h"

        // Entre 24 y 48 horas
        hours in 24..47 -> "yesterday"

        // Entre 48 horas (2 días) y 6 días
        days in 2..6 -> "${days}d"

        // Entre 7 días y 4 semanas (aprox 28 días)
        days in 7..27 -> "${days / 7}w"

        // Entre 4 semanas y 11 meses (aprox 330 días)
        days in 28..330 -> "${days / 30}mon"

        // Más de 11 meses
        else -> "${days / 365}y"
    }
}

fun getSupporttingContent(author: String, cratedTS: Long): String{
    return author + " - " + getAgeByTimeStamp(cratedTS)
}

fun isValidUrl(url: String): Boolean {
    return try {
        val uri = java.net.URI(url)
        uri.scheme != null && (uri.scheme == "http" || uri.scheme == "https")
    } catch (e: Exception) {
        false
    }
}

