package fi.roskisprojekti.adapter.out.persistence.jpa.repository;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinJpaRepository extends JpaRepository<BinJpaEntity, Long>{
}
