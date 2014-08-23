/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Lookingfor;
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
public class LookingforJpaController {

    public LookingforJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lookingfor lookingfor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lookingfor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLookingfor(lookingfor.getLookingForID()) != null) {
                throw new PreexistingEntityException("Lookingfor " + lookingfor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lookingfor lookingfor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lookingfor = em.merge(lookingfor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lookingfor.getLookingForID();
                if (findLookingfor(id) == null) {
                    throw new NonexistentEntityException("The lookingfor with id " + id + " no longer exists.");
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
            Lookingfor lookingfor;
            try {
                lookingfor = em.getReference(Lookingfor.class, id);
                lookingfor.getLookingForID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lookingfor with id " + id + " no longer exists.", enfe);
            }
            em.remove(lookingfor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lookingfor> findLookingforEntities() {
        return findLookingforEntities(true, -1, -1);
    }

    public List<Lookingfor> findLookingforEntities(int maxResults, int firstResult) {
        return findLookingforEntities(false, maxResults, firstResult);
    }

    private List<Lookingfor> findLookingforEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lookingfor.class));
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

    public Lookingfor findLookingfor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lookingfor.class, id);
        } finally {
            em.close();
        }
    }

    public int getLookingforCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lookingfor> rt = cq.from(Lookingfor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
