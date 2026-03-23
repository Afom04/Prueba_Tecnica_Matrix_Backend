package com.matrix.technicaltest.repository;

import com.matrix.technicaltest.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Optional<Resource> findByName(String name);

    @Query("""
    SELECT r FROM Resource r
    WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', COALESCE(:name, ''), '%'))
    AND r.deletedAt IS NULL
""")
    Page<Resource> search(@Param("name") String name, Pageable pageable);
}