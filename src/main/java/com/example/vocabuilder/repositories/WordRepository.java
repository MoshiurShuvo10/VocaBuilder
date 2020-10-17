package com.example.vocabuilder.repositories;

import com.example.vocabuilder.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
}
