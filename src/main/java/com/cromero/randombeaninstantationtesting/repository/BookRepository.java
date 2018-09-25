package com.cromero.randombeaninstantationtesting.repository;

import com.cromero.randombeaninstantationtesting.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
