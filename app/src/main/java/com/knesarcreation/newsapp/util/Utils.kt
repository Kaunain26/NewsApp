package com.knesarcreation.newsapp.util

import android.annotation.SuppressLint
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**This concepts is taken from GitHub**/

class Utils {
    fun DateToTimeFormat(oldStringDate: String?): String? {
        val p = PrettyTime(Locale(getCountry()))
        var isTime: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH
            )
            val date: Date = simpleDateFormat.parse(oldStringDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    @SuppressLint("SimpleDateFormat")
    fun DateFormat(oldStringDate: String?): String? {
        val newDate: String?
        val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
        newDate = try {
            val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldStringDate
        }
        return newDate
    }

    fun getCountry(): String? {
        val locale: Locale = Locale.getDefault()
        val country: String = (locale.country)
        return country.toLowerCase()
    }
}