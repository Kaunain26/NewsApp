package com.knesarcreation.newsapp.model

data class News(
    val status: String,
    val totalResult: Int,
    val articles: ArrayList<Articles>
)
