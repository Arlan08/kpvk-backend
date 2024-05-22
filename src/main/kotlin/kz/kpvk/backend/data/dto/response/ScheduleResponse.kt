package kz.kpvk.backend.data.dto.response

import kz.kpvk.backend.data.entity.DayOfWeek
import java.time.LocalTime

data class ScheduleResponse(
    val id: Int,
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val lessonId: Int,
    val lessonName: String
)