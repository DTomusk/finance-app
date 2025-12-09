package com.example.financeapp.ui.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toReadableDate(): String {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
}
