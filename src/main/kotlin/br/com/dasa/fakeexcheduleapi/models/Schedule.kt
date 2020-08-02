package br.com.dasa.fakeexcheduleapi.models

import java.time.LocalDate

data class Schedule (
    val id: Int,
    val date: LocalDate,
    val startTime: String,
    val endTime: String,
    var ruleId: Int,
    var rule: Rule? = null
)
