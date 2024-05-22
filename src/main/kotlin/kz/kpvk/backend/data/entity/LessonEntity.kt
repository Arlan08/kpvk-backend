package kz.kpvk.backend.data.entity

import jakarta.persistence.*


@Entity
@Table(name = "lessons")
class LessonEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String = "",
)