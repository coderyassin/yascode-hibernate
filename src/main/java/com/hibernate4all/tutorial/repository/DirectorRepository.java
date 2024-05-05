package com.hibernate4all.tutorial.repository;

import com.hibernate4all.tutorial.domain.Director;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorRepository {

    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;

    @PostConstruct
    public void init() {
        criteriaBuilder = em.getCriteriaBuilder();
    }

    public List<Director> findByFirstName(String firstName) {
        CriteriaQuery<Director> query = getCriteriaQuery();
        Root<Director> root = query.from(Director.class);
        query.select(root).where(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
        return em.createQuery(query).getResultList();
    }

    public List<Director> findByLastName(String LastName) {
        CriteriaQuery<Director> query = getCriteriaQuery();
        Root<Director> root = query.from(Director.class);
        query.select(root).where(criteriaBuilder.like(root.get("lastName"), "%" + LastName + "%"));
        return em.createQuery(query).getResultList();
    }

    private CriteriaQuery<Director> getCriteriaQuery() {
        return criteriaBuilder.createQuery(Director.class);
    }


    public List<Director> findDirectorsByFirstNameAndLastName(String firstName, String lastName) {
        CriteriaQuery<Director> query = getCriteriaQuery();
        Root<Director> root = query.from(Director.class);
        query.select(root).where(criteriaBuilder.or(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"),
                criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%")));
        return em.createQuery(query).getResultList();
    }
}
