package com.example.librarycatalog.repository;

import com.example.librarycatalog.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
