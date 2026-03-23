package com.matrix.technicaltest.repository;

import com.matrix.technicaltest.entity.Request;
import com.matrix.technicaltest.entity.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByStatus(RequestStatus status);

    List<Request> findByApplicantNameContainingIgnoreCase(String name);

    List<Request> findByApplicantEmailContainingIgnoreCase(String email);

    List<Request> findByStatusAndApplicantNameContainingIgnoreCase(
            RequestStatus status,
            String name
    );

    @Query("""
    SELECT r FROM Request r
    WHERE (r.status = COALESCE(:status, r.status))
    AND LOWER(r.applicantName) LIKE LOWER(CONCAT('%', COALESCE(:name, ''), '%'))
    AND LOWER(r.applicantEmail) LIKE LOWER(CONCAT('%', COALESCE(:email, ''), '%'))
    AND r.deletedAt IS NULL
""")
    Page<Request> search(
            @org.springframework.data.repository.query.Param("status") RequestStatus status,
            @org.springframework.data.repository.query.Param("name") String name,
            @org.springframework.data.repository.query.Param("email") String email,
            Pageable pageable
    );
}
