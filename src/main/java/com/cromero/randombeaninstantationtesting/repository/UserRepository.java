package com.cromero.randombeaninstantationtesting.repository;

import com.cromero.randombeaninstantationtesting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}
