/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Userrelationship;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Eric
 */
public class UserrelationshipJpaController {

    public UserrelationshipJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Userrelationship userrelationship) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userrelationship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUserrelationship(userrelationship.getUserID()) != null) {
                throw new PreexistingEntityException("Userrelationship " + userrelationship + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Userrelationship userrelationship) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userrelationship = em.merge(userrelationship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userrelationship.getUserID();
                if (findUserrelationship(id) == null) {
                    throw new NonexistentEntityException("The userrelationship with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userrelationship userrelationship;
            try {
                userrelationship = em.getReference(Userrelationship.class, id);
                userrelationship.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userrelationship with id " + id + " no longer exists.", enfe);
            }
            em.remove(userrelationship);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Userrelationship> findUserrelationshipEntities() {
        return findUserrelationshipEntities(true, -1, -1);
    }

    public List<Userrelationship> findUserrelationshipEntities(int maxResults, int firstResult) {
        return findUserrelationshipEntities(false, maxResults, firstResult);
    }

    private List<Userrelationship> findUserrelationshipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Userrelationship.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Userrelationship findUserrelationship(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Userrelationship.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserrelationshipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Userrelationship> rt = cq.from(Userrelationship.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
