package com.example.test;

import com.example.entity.Category;
import com.example.util.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TestJPA {
    public static void main(String[] args) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Category c = new Category("Test", "test.jpg", 1);
            em.persist(c);
            trans.commit();
            System.out.println("Insert thành công");
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}