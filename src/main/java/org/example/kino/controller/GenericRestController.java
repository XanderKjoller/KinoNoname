package org.example.kino.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public abstract class GenericRestController<T, ID> {

    @Autowired
    protected JpaRepository<T, ID> repository;

    // CREATE
    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T saved = repository.save(entity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL (without pagination)
    @GetMapping
    public ResponseEntity<Iterable<T>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // READ ALL with pagination
    @GetMapping("/page")
    public ResponseEntity<Page<T>> getAllPaged(Pageable pageable) {
        Page<T> page = repository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        Optional<T> entity = repository.findById(id);
        return entity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        T updated = repository.save(entity);
        return ResponseEntity.ok(updated);
    }
}
