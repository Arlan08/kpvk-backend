package kz.kpvk.backend.data.repository

import kz.kpvk.backend.data.entity.ScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository : JpaRepository<ScheduleEntity, Int>
