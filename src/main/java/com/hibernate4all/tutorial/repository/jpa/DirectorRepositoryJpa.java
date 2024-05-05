package com.hibernate4all.tutorial.repository.jpa;

import com.hibernate4all.tutorial.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectorRepositoryJpa extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {

    @Query(value = "select d.* from director d inner join movie m " +
            "on d.id = m.director_id where m.name like %:movieName%", nativeQuery = true)
    List<Director> findByMovieName(@Param("movieName") String movieName);

}
