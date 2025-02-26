package com.example.onefin.domain.model


data class Response(
    val success: Boolean,
    val terms : String,
    val privacy : String,
    val timestamp : Long,
    val source: String,
    val quotes: Map<String, Double>
)
