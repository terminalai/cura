package app.nush.cura.model.util

import java.time.*
import java.time.format.DateTimeFormatterBuilder
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

object Util {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    val dTF: DateTimeFormatter =
        DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd/MM/yyyy")
            .toFormatter()
}

fun LocalDateTime.onDate(date: LocalDate) =
    Util.dTF.format(toLocalDate()).equals(Util.dTF.format(date))

fun LocalDateTime.atTime(time: LocalTime) = run {
    val itTime = toLocalTime()
    (itTime.hour == time.hour && itTime.minute == time.minute && itTime.second == time.second)
}

fun LocalDateTime.atHour(hour: Int) =
    toLocalTime().hour == hour

fun Long.toLocalDateTime() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
)

fun Long.toLocalDate() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
).toLocalDate()

fun Long.toLocalTime() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
).toLocalTime()

fun LocalDateTime.toEpoch() = atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

val dTF =
    DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern("dd/MM/yyyy")
        .toFormatter()