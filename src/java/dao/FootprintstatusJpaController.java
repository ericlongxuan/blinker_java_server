/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Footprintstatus;
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
public class FootprintstatusJpaController {

    public FootprintstatusJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Footprintstatus footprintstatus) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(footprintstatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFootprintstatus(footprintstatus.getFootprintStatusID()) != null) {
                throw new PreexistingEntityException("Footprintstatus " + footprintstatus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Footprintstatus footprintstatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            footprintstatus = em.merge(footprintstatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = footprintstatus.getFootprintStatusID();
                if (findFootprintstatus(id) == null) {
                    throw new NonexistentEntityException("The footprintstatus with id " + id + " no longer exists.");
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
            Footprintstatus footprintstatus;
            try {
                footprintstatus = em.getReference(Footprintstatus.class, id);
                footprintstatus.getFootprintStatusID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The footprintstatus with id " + id + " no longer exists.", enfe);
            }
            em.remove(footprintstatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Footprintstatus> findFootprintstatusEntities() {
        return findFootprintstatusEntities(true, -1, -1);
    }

    public List<Footprintstatus> findFootprintstatusEntities(int maxResults, int firstResult) {
        return findFootprintstatusEntities(false, maxResults, firstResult);
    }

    private List<Footprintstatus> findFootprintstatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Footprintstatus.class));
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

    public Footprintstatus findFootprintstatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Footprintstatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getFootprintstatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Footprintstatus> rt = cq.from(Footprintstatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
