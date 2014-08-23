/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Idol;
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
public class IdolJpaController {

    public IdolJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Idol idol) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(idol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIdol(idol.getIdolName()) != null) {
                throw new PreexistingEntityException("Idol " + idol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Idol idol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            idol = em.merge(idol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = idol.getIdolName();
                if (findIdol(id) == null) {
                    throw new NonexistentEntityException("The idol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Idol idol;
            try {
                idol = em.getReference(Idol.class, id);
                idol.getIdolName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idol with id " + id + " no longer exists.", enfe);
            }
            em.remove(idol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Idol> findIdolEntities() {
        return findIdolEntities(true, -1, -1);
    }

    public List<Idol> findIdolEntities(int maxResults, int firstResult) {
        return findIdolEntities(false, maxResults, firstResult);
    }

    private List<Idol> findIdolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Idol.class));
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

    public Idol findIdol(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Idol.class, id);
        } finally {
            em.close();
        }
    }

    public int getIdolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Idol> rt = cq.from(Idol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
