package kz.kpvk.backend.data.dto.response

import kz.kpvk.backend.data.entity.DayOfWeek
import java.time.LocalTime

data class ScheduleWrapper(
    val dayOfWeek: DayOfWeek,
    val lessons: List<ScheduleResponse>
)