/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Entitytype;
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
public class EntitytypeJpaController {

    public EntitytypeJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entitytype entitytype) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entitytype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntitytype(entitytype.getEntityTypeID()) != null) {
                throw new PreexistingEntityException("Entitytype " + entitytype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entitytype entitytype) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entitytype = em.merge(entitytype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entitytype.getEntityTypeID();
                if (findEntitytype(id) == null) {
                    throw new NonexistentEntityException("The entitytype with id " + id + " no longer exists.");
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
            Entitytype entitytype;
            try {
                entitytype = em.getReference(Entitytype.class, id);
                entitytype.getEntityTypeID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entitytype with id " + id + " no longer exists.", enfe);
            }
            em.remove(entitytype);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entitytype> findEntitytypeEntities() {
        return findEntitytypeEntities(true, -1, -1);
    }

    public List<Entitytype> findEntitytypeEntities(int maxResults, int firstResult) {
        return findEntitytypeEntities(false, maxResults, firstResult);
    }

    private List<Entitytype> findEntitytypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entitytype.class));
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

    public Entitytype findEntitytype(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entitytype.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntitytypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entitytype> rt = cq.from(Entitytype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
