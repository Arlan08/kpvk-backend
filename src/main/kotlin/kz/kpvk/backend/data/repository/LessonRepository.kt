package kz.kpvk.backend.data.repository

import kz.kpvk.backend.data.entity.LessonEntity
import kz.kpvk.backend.data.entity.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : JpaRepository<LessonEntity, Int> {
    fun findByNameContaining(name: String): List<LessonEntity>
}