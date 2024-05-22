package kz.kpvk.backend.service

import jakarta.annotation.PostConstruct
import kz.kpvk.backend.data.entity.ScheduleEntity
import kz.kpvk.backend.data.repository.ScheduleRepository
import kz.kpvk.backend.data.dto.response.ScheduleResponse
import kz.kpvk.backend.data.dto.response.ScheduleWrapper
import kz.kpvk.backend.data.entity.DayOfWeek
import kz.kpvk.backend.data.entity.LessonEntity
import kz.kpvk.backend.data.repository.LessonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class ScheduleService(
    @Autowired private val scheduleRepository: ScheduleRepository,
    @Autowired private val lessonRepository: LessonRepository
) {

    @PostConstruct
    fun init() {
        lessonRepository.saveAllAndFlush(
            listOf(
                LessonEntity(id = 1, name = "Алгебра"),
                LessonEntity(id = 2, name = "Физика"),
                LessonEntity(id = 3, name = "Химия"),
                LessonEntity(id = 4, name = "Биология"),
                LessonEntity(id = 5, name = "География"),
                LessonEntity(id = 6, name = "Геометрия"),
            )
        )

        scheduleRepository.saveAllAndFlush(
            listOf(
                ScheduleEntity(
                    lesson = lessonRepository.findById(1).get(),
                    startTime = LocalTime.of(8, 30),
                    endTime = LocalTime.of(9, 30),
                    dayOfWeek = DayOfWeek.MONDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(6).get(),
                    startTime = LocalTime.of(9, 30),
                    endTime = LocalTime.of(10, 30),
                    dayOfWeek = DayOfWeek.MONDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(2).get(),
                    startTime = LocalTime.of(10, 30),
                    endTime = LocalTime.of(11, 30),
                    dayOfWeek = DayOfWeek.TUESDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(3).get(),
                    startTime = LocalTime.of(11, 30),
                    endTime = LocalTime.of(12, 30),
                    dayOfWeek = DayOfWeek.TUESDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(3).get(),
                    startTime = LocalTime.of(12, 30),
                    endTime = LocalTime.of(13,30),
                    dayOfWeek = DayOfWeek.WEDNESDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(4).get(),
                    startTime = LocalTime.of(13, 30),
                    endTime = LocalTime.of(14,30),
                    dayOfWeek = DayOfWeek.WEDNESDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(4).get(),
                    startTime = LocalTime.of(15, 30),
                    endTime = LocalTime.of(16, 30),
                    dayOfWeek = DayOfWeek.THURSDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(5).get(),
                    startTime = LocalTime.of(16, 30),
                    endTime = LocalTime.of(17, 30),
                    dayOfWeek = DayOfWeek.THURSDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(5).get(),
                    startTime = LocalTime.of(16, 30),
                    endTime = LocalTime.of(17, 30),
                    dayOfWeek = DayOfWeek.FRIDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(1).get(),
                    startTime = LocalTime.of(17, 30),
                    endTime = LocalTime.of(18, 30),
                    dayOfWeek = DayOfWeek.FRIDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(6).get(),
                    startTime = LocalTime.of(11, 30),
                    endTime = LocalTime.of(12, 30),
                    dayOfWeek = DayOfWeek.SATURDAY
                ),
                ScheduleEntity(
                    lesson = lessonRepository.findById(4).get(),
                    startTime = LocalTime.of(12, 30),
                    endTime = LocalTime.of(13, 30),
                    dayOfWeek = DayOfWeek.SATURDAY
                ),
            )
        )
    }

    fun getAllSchedules(): List<ScheduleWrapper> {
        return scheduleRepository.findAll()
            .map { it.toDTO() }
            .sortedBy { it.dayOfWeek }
            .groupBy { it.dayOfWeek }
            .map { ScheduleWrapper(it.key, it.value.sortedBy { l -> l.startTime }) }
    }

    fun createSchedule(scheduleDTO: ScheduleResponse): ScheduleResponse {
        val lesson = lessonRepository.findById(scheduleDTO.lessonId)
            .orElseThrow { IllegalArgumentException("Lesson not found") }
        val schedule = ScheduleEntity(
            dayOfWeek = scheduleDTO.dayOfWeek,
            startTime = scheduleDTO.startTime,
            endTime = scheduleDTO.endTime,
            lesson = lesson
        )
        return scheduleRepository.save(schedule).toDTO()
    }

    fun updateSchedule(id: Int, scheduleDTO: ScheduleResponse): ScheduleResponse? {
        val existingSchedule = scheduleRepository.findById(id).orElse(null) ?: return null
        val lesson = lessonRepository.findById(scheduleDTO.lessonId)
            .orElseThrow { IllegalArgumentException("Lesson not found") }
        existingSchedule.dayOfWeek = scheduleDTO.dayOfWeek
        existingSchedule.startTime = scheduleDTO.startTime
        existingSchedule.endTime = scheduleDTO.endTime
        existingSchedule.lesson = lesson
        return scheduleRepository.save(existingSchedule).toDTO()
    }

    private fun ScheduleEntity.toDTO(): ScheduleResponse {
        return ScheduleResponse(
            id = this.id,
            dayOfWeek = this.dayOfWeek,
            startTime = this.startTime,
            endTime = this.endTime,
            lessonId = this.lesson.id,
            lessonName = this.lesson.name
        )
    }
}