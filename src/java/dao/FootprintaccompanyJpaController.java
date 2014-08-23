/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Footprintaccompany;
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
public class FootprintaccompanyJpaController {

    public FootprintaccompanyJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Footprintaccompany footprintaccompany) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(footprintaccompany);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFootprintaccompany(footprintaccompany.getFootprintID()) != null) {
                throw new PreexistingEntityException("Footprintaccompany " + footprintaccompany + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Footprintaccompany footprintaccompany) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            footprintaccompany = em.merge(footprintaccompany);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = footprintaccompany.getFootprintID();
                if (findFootprintaccompany(id) == null) {
                    throw new NonexistentEntityException("The footprintaccompany with id " + id + " no longer exists.");
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
            Footprintaccompany footprintaccompany;
            try {
                footprintaccompany = em.getReference(Footprintaccompany.class, id);
                footprintaccompany.getFootprintID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The footprintaccompany with id " + id + " no longer exists.", enfe);
            }
            em.remove(footprintaccompany);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Footprintaccompany> findFootprintaccompanyEntities() {
        return findFootprintaccompanyEntities(true, -1, -1);
    }

    public List<Footprintaccompany> findFootprintaccompanyEntities(int maxResults, int firstResult) {
        return findFootprintaccompanyEntities(false, maxResults, firstResult);
    }

    private List<Footprintaccompany> findFootprintaccompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Footprintaccompany.class));
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

    public Footprintaccompany findFootprintaccompany(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Footprintaccompany.class, id);
        } finally {
            em.close();
        }
    }

    public int getFootprintaccompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Footprintaccompany> rt = cq.from(Footprintaccompany.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
