package com.errabi.productmgt.repositories;

import com.errabi.productmgt.entities.Product;
import com.errabi.productmgt.exceptions.EntityNotFoundException;
import com.errabi.productmgt.exceptions.TechnicalException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleProductRepository {
    private final EntityManagerFactory entityManagerFactory;

    public void save(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new TechnicalException("Error while saving entity: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void update(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new TechnicalException("Error while updating entity: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public Optional<Product> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(Product.class, id));
        }
    }

    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if(entityManager.find(Product.class, id) == null) {
                throw new EntityNotFoundException("Entity not found");
            }else {
                entityManager.remove(entityManager.find(Product.class, id));
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new TechnicalException("Error while deleting entity: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public Page<Product> findAll(Pageable pageable) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM " + Product.class.getSimpleName() + " e", Product.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Product> results = query.getResultList();

        Query countQuery = entityManager.createQuery("SELECT COUNT(e) FROM " + Product.class.getSimpleName() + " e");
        long totalElements = (long) countQuery.getSingleResult();

        return new PageImpl<>(results, pageable, totalElements);
    }
}
