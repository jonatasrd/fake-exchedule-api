package br.com.dasa.fakeexcheduleapi.controllers

import br.com.dasa.fakeexcheduleapi.clients.RulesClient
import br.com.dasa.fakeexcheduleapi.models.Schedule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api")
class ScheduleController {

    @Autowired
    lateinit var client: RulesClient

    private fun getSchedules() = listOf(
        Schedule(id = 1, date = LocalDate.now(), startTime = "0600", endTime = "0700", ruleId = 3),
        Schedule(id = 2, date = LocalDate.now(), startTime = "0700", endTime = "0800", ruleId = 1),
        Schedule(id = 3, date = LocalDate.now(), startTime = "0800", endTime = "0900", ruleId = 1),
        Schedule(id = 4, date = LocalDate.now(), startTime = "0900", endTime = "1000", ruleId = 2),
        Schedule(id = 5, date = LocalDate.now(), startTime = "1000", endTime = "1100", ruleId = 3)
    )

    @GetMapping("/schedules")
    fun getAllSchedules() = getSchedules()

    @GetMapping("/schedules/{id}")
    fun getById(@PathVariable("id") id: Int) =
        try {
            val schedule = getSchedules().first {
                it.id == id
            }
            val rule = client.getRules(schedule.ruleId)
            schedule.rule = rule

            ResponseEntity(schedule, HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity<Any>(HttpStatus.NOT_FOUND)
        }


}