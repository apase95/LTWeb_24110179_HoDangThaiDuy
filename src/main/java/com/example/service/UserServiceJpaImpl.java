package com.example.service;

import com.example.dao.jpa.UserDaoJpa;
import com.example.entity.UserEntity;
import com.example.util.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.Date;

public class UserServiceJpaImpl implements IUserServiceJpa {
    private IUserDao userDao = new UserDaoJpa();

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void update(UserEntity user) {
        userDao.update(user);
    }

    @Override
    public boolean verifyOTP(String username, String otp) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            UserEntity user = em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
                                .setParameter("username", username)
                                .getSingleResult();
            if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
                Date now = new Date();
                if (user.getOtpExpiry() != null && now.before(user.getOtpExpiry())) {
                    user.setOtp(null);
                    user.setOtpExpiry(null);
                    em.getTransaction().begin();
                    em.merge(user);
                    em.getTransaction().commit();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void saveOTP(String username, String otp, Date expiry) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            UserEntity user = em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
                                .setParameter("username", username)
                                .getSingleResult();
            if (user != null) {
                user.setOtp(otp);
                user.setOtpExpiry(expiry);
                em.merge(user);
                trans.commit();
            }
        } catch (NoResultException e) {
            if (trans.isActive()) trans.rollback();
            System.err.println("User not found: " + username);
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean activateUser(String username, String otp) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            UserEntity user = em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
                                .setParameter("username", username)
                                .getSingleResult();
            if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
                Date now = new Date();
                if (user.getOtpExpiry() != null && now.before(user.getOtpExpiry())) {
                    user.setActive(true);
                    user.setOtp(null);
                    user.setOtpExpiry(null);
                    em.merge(user);
                    trans.commit();
                    return true;
                }
            }
            trans.commit();
            return false;
        } catch (NoResultException e) {
            if (trans.isActive()) trans.rollback();
            return false;
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            UserEntity user = em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
                                .setParameter("username", username)
                                .getSingleResult();
            if (user != null) {
                user.setPassword(newPassword);
                em.merge(user);
                trans.commit();
                return true;
            }
            trans.commit();
            return false;
        } catch (NoResultException e) {
            if (trans.isActive()) trans.rollback();
            return false;
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}