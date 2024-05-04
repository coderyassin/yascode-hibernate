package com.hibernate4all.tutorial.repository.jpa;

import com.hibernate4all.tutorial.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirectorRepositoryJpa extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
}
