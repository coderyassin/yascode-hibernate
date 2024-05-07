package com.hibernate4all.tutorial.repository.jpa;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.jpa.projections.DirectorProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectorRepositoryJpa extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {

    @Query(value = "select d.* from director d inner join movie m " +
            "on d.id = m.director_id where m.name like %:movieName%", nativeQuery = true)
    List<Director> findByMovieName(@Param("movieName") String movieName);

    @Query(value = "select new com.hibernate4all.tutorial.repository.jpa.projections.DirectorProjection(d.firstName, d.lastName) from Director d")
    Page<DirectorProjection> findFirstNameAndLastName(Pageable pageable);

}
