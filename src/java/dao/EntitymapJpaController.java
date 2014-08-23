/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Entitymap;
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
public class EntitymapJpaController {

    public EntitymapJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entitymap entitymap) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entitymap);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntitymap(entitymap.getEntityID()) != null) {
                throw new PreexistingEntityException("Entitymap " + entitymap + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entitymap entitymap) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entitymap = em.merge(entitymap);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entitymap.getEntityID();
                if (findEntitymap(id) == null) {
                    throw new NonexistentEntityException("The entitymap with id " + id + " no longer exists.");
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
            Entitymap entitymap;
            try {
                entitymap = em.getReference(Entitymap.class, id);
                entitymap.getEntityID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entitymap with id " + id + " no longer exists.", enfe);
            }
            em.remove(entitymap);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entitymap> findEntitymapEntities() {
        return findEntitymapEntities(true, -1, -1);
    }

    public List<Entitymap> findEntitymapEntities(int maxResults, int firstResult) {
        return findEntitymapEntities(false, maxResults, firstResult);
    }

    private List<Entitymap> findEntitymapEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entitymap.class));
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

    public Entitymap findEntitymap(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entitymap.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntitymapCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entitymap> rt = cq.from(Entitymap.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
