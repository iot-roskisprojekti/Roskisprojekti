package fi.roskisprojekti.adapter.in.rest.controller;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.EmployeeJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeJpaRepository repo;

    @GetMapping
    public List<EmployeeJpaEntity> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public EmployeeJpaEntity create(@RequestBody EmployeeJpaEntity employee) {
        return repo.save(employee);
    }

    @PutMapping("/{id}")
    public EmployeeJpaEntity update(@PathVariable Long id, @RequestBody EmployeeJpaEntity updated) {
        updated.setId(id);
        return repo.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}