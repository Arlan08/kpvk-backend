package kz.kpvk.backend.data.entity

import jakarta.persistence.*
import java.time.LocalTime

@Entity
@Table(name = "schedules")
class ScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Enumerated(EnumType.STRING)
    var dayOfWeek: DayOfWeek,
    var startTime: LocalTime,
    var endTime: LocalTime,
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    var lesson: LessonEntity
)