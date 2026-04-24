package fi.roskisprojekti.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "site", schema = "roskis_db")
public class SiteJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 200)
    @NotNull
    @Column(name = "location", nullable = false, length = 200)
    private String location;

    @OneToMany(mappedBy = "siteJpaEntity")
    private Set<BinJpaEntity> binEntities = new LinkedHashSet<>();


}