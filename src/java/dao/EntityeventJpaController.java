/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Entityevent;
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
public class EntityeventJpaController {

    public EntityeventJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entityevent entityevent) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entityevent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntityevent(entityevent.getEventID()) != null) {
                throw new PreexistingEntityException("Entityevent " + entityevent + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entityevent entityevent) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entityevent = em.merge(entityevent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entityevent.getEventID();
                if (findEntityevent(id) == null) {
                    throw new NonexistentEntityException("The entityevent with id " + id + " no longer exists.");
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
            Entityevent entityevent;
            try {
                entityevent = em.getReference(Entityevent.class, id);
                entityevent.getEventID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entityevent with id " + id + " no longer exists.", enfe);
            }
            em.remove(entityevent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entityevent> findEntityeventEntities() {
        return findEntityeventEntities(true, -1, -1);
    }

    public List<Entityevent> findEntityeventEntities(int maxResults, int firstResult) {
        return findEntityeventEntities(false, maxResults, firstResult);
    }

    private List<Entityevent> findEntityeventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entityevent.class));
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

    public Entityevent findEntityevent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entityevent.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntityeventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entityevent> rt = cq.from(Entityevent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
