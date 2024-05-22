package kz.kpvk.backend.service

import kz.kpvk.backend.data.dto.request.LessonDTO
import kz.kpvk.backend.data.entity.LessonEntity
import kz.kpvk.backend.data.repository.LessonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LessonService(@Autowired private val lessonRepository: LessonRepository) {

    fun getAllLessons(): List<LessonDTO> {
        return lessonRepository.findAll().map { it.toDTO() }
    }

    fun getLessonsByName(name: String): List<LessonDTO> {
        return lessonRepository.findByNameContaining(name).map { it.toDTO() }
    }

    fun createLesson(lessonDTO: LessonDTO): LessonDTO {
        val lesson = LessonEntity(name = lessonDTO.name)
        return lessonRepository.save(lesson).toDTO()
    }

    fun updateLesson(id: Int, lessonDTO: LessonDTO): LessonDTO? {
        val lesson = lessonRepository.findById(id).orElse(null) ?: return null
        lesson.name = lessonDTO.name
        return lessonRepository.save(lesson).toDTO()
    }

    fun deleteLesson(id: Int): Boolean {
        return if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    private fun LessonEntity.toDTO(): LessonDTO {
        return LessonDTO(id = this.id, name = this.name)
    }
}