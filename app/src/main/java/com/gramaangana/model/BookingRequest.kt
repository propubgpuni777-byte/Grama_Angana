package com.gramaangana.model

data class BookingRequest(
    val id: String = "",
    val requesterName: String = "",
    val purpose: String = "",
    val date: String = "",        // format: yyyy-MM-dd
    val timeSlot: String = "",    // e.g. "10:00-12:00"
    val status: String = "pending" // pending | approved | rejected
)
