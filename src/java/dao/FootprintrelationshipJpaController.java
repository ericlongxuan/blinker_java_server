/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Footprintrelationship;
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
public class FootprintrelationshipJpaController {

    public FootprintrelationshipJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Footprintrelationship footprintrelationship) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(footprintrelationship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFootprintrelationship(footprintrelationship.getUserID()) != null) {
                throw new PreexistingEntityException("Footprintrelationship " + footprintrelationship + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Footprintrelationship footprintrelationship) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            footprintrelationship = em.merge(footprintrelationship);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = footprintrelationship.getUserID();
                if (findFootprintrelationship(id) == null) {
                    throw new NonexistentEntityException("The footprintrelationship with id " + id + " no longer exists.");
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
            Footprintrelationship footprintrelationship;
            try {
                footprintrelationship = em.getReference(Footprintrelationship.class, id);
                footprintrelationship.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The footprintrelationship with id " + id + " no longer exists.", enfe);
            }
            em.remove(footprintrelationship);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Footprintrelationship> findFootprintrelationshipEntities() {
        return findFootprintrelationshipEntities(true, -1, -1);
    }

    public List<Footprintrelationship> findFootprintrelationshipEntities(int maxResults, int firstResult) {
        return findFootprintrelationshipEntities(false, maxResults, firstResult);
    }

    private List<Footprintrelationship> findFootprintrelationshipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Footprintrelationship.class));
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

    public Footprintrelationship findFootprintrelationship(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Footprintrelationship.class, id);
        } finally {
            em.close();
        }
    }

    public int getFootprintrelationshipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Footprintrelationship> rt = cq.from(Footprintrelationship.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
