/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Entityrelationship;
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
public class EntityrelationshipJpaController {

    public EntityrelationshipJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entityrelationship entityrelationship) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entityrelationship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntityrelationship(entityrelationship.getEntityID()) != null) {
                throw new PreexistingEntityException("Entityrelationship " + entityrelationship + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entityrelationship entityrelationship) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entityrelationship = em.merge(entityrelationship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entityrelationship.getEntityID();
                if (findEntityrelationship(id) == null) {
                    throw new NonexistentEntityException("The entityrelationship with id " + id + " no longer exists.");
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
            Entityrelationship entityrelationship;
            try {
                entityrelationship = em.getReference(Entityrelationship.class, id);
                entityrelationship.getEntityID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entityrelationship with id " + id + " no longer exists.", enfe);
            }
            em.remove(entityrelationship);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entityrelationship> findEntityrelationshipEntities() {
        return findEntityrelationshipEntities(true, -1, -1);
    }

    public List<Entityrelationship> findEntityrelationshipEntities(int maxResults, int firstResult) {
        return findEntityrelationshipEntities(false, maxResults, firstResult);
    }

    private List<Entityrelationship> findEntityrelationshipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entityrelationship.class));
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

    public Entityrelationship findEntityrelationship(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entityrelationship.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntityrelationshipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entityrelationship> rt = cq.from(Entityrelationship.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
