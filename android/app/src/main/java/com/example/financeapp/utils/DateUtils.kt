package com.example.financeapp.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toReadableDate(): String {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
}

fun getMonthBounds(): Pair<Long, Long> {
    val now = LocalDate.now()
    val zoneId = ZoneId.systemDefault()

    val startOfMonth =
        now.withDayOfMonth(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

    val startOfNextMonth =
        now.plusMonths(1)
            .withDayOfMonth(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

    return startOfMonth to startOfNextMonth
}
