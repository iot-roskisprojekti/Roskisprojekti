package fi.roskisprojekti.adapter.out.persistence.jpa.repository;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.SiteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteJpaRepository extends JpaRepository<SiteJpaEntity, Long> {
}
