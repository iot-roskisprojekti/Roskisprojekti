package fi.roskisprojekti.adapter.out.persistence.jpa.repository;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
}
