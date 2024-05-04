package com.hibernate4all.tutorial.repository;

import com.hibernate4all.tutorial.domain.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void persist(Movie movie) {
        //LOGGER.trace("em.contains() : {}", em.contains(movie));
        em.persist(movie);
        //LOGGER.trace("em.contains() : {}", em.contains(movie));
    }

    public List<Movie> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = criteriaQuery.from(Movie.class);
        criteriaQuery.select(movieRoot);
        return em.createQuery(criteriaQuery).getResultList();
        //throw new UnsupportedOperationException("findAll() method not supported yet.");
    }

    public List<Movie> findByTitle(String title) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = criteriaQuery.from(Movie.class);
        criteriaQuery.select(movieRoot);
        criteriaQuery.where(criteriaBuilder.equal(movieRoot.get("title"), title));
        return em.createQuery(criteriaQuery).getResultList();
    }

}
