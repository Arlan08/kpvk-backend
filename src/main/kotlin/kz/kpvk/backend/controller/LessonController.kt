package kz.kpvk.backend.controller

import kz.kpvk.backend.data.dto.request.LessonDTO
import kz.kpvk.backend.service.LessonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lessons")
class LessonController(@Autowired private val lessonService: LessonService) {

    @GetMapping
    fun getAllLessons(): List<LessonDTO> = lessonService.getAllLessons()

    @GetMapping("/search")
    fun getLessonsByName(@RequestParam name: String): List<LessonDTO> = lessonService.getLessonsByName(name)

    @PostMapping
    fun createLesson(@RequestBody lessonDTO: LessonDTO): LessonDTO = lessonService.createLesson(lessonDTO)

    @PutMapping("/{id}")
    fun updateLesson(@PathVariable id: Int, @RequestBody lessonDTO: LessonDTO): ResponseEntity<LessonDTO> {
        val updatedLesson = lessonService.updateLesson(id, lessonDTO)
        return if (updatedLesson != null) {
            ResponseEntity.ok(updatedLesson)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteLesson(@PathVariable id: Int): ResponseEntity<Void> {
        return if (lessonService.deleteLesson(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}