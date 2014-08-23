/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Footprintprofile;
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
public class FootprintprofileJpaController {

    public FootprintprofileJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Footprintprofile footprintprofile) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(footprintprofile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFootprintprofile(footprintprofile.getFootprintID()) != null) {
                throw new PreexistingEntityException("Footprintprofile " + footprintprofile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Footprintprofile footprintprofile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            footprintprofile = em.merge(footprintprofile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = footprintprofile.getFootprintID();
                if (findFootprintprofile(id) == null) {
                    throw new NonexistentEntityException("The footprintprofile with id " + id + " no longer exists.");
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
            Footprintprofile footprintprofile;
            try {
                footprintprofile = em.getReference(Footprintprofile.class, id);
                footprintprofile.getFootprintID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The footprintprofile with id " + id + " no longer exists.", enfe);
            }
            em.remove(footprintprofile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Footprintprofile> findFootprintprofileEntities() {
        return findFootprintprofileEntities(true, -1, -1);
    }

    public List<Footprintprofile> findFootprintprofileEntities(int maxResults, int firstResult) {
        return findFootprintprofileEntities(false, maxResults, firstResult);
    }

    private List<Footprintprofile> findFootprintprofileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Footprintprofile.class));
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

    public Footprintprofile findFootprintprofile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Footprintprofile.class, id);
        } finally {
            em.close();
        }
    }

    public int getFootprintprofileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Footprintprofile> rt = cq.from(Footprintprofile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
