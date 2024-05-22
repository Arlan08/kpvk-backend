package kz.kpvk.backend.controller

import kz.kpvk.backend.data.dto.response.ScheduleResponse
import kz.kpvk.backend.data.dto.response.ScheduleWrapper
import kz.kpvk.backend.service.ScheduleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/schedules")
class ScheduleController(@Autowired private val scheduleService: ScheduleService) {

    @GetMapping
    fun getAllSchedules(): List<ScheduleWrapper> = scheduleService.getAllSchedules()

    @PostMapping
    fun createSchedule(@RequestBody scheduleDTO: ScheduleResponse): ScheduleResponse = scheduleService.createSchedule(scheduleDTO)

    @PutMapping("/{id}")
    fun updateSchedule(@PathVariable id: Int, @RequestBody scheduleDTO: ScheduleResponse): ResponseEntity<ScheduleResponse> {
        val updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO)
        return if (updatedSchedule != null) {
            ResponseEntity.ok(updatedSchedule)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
