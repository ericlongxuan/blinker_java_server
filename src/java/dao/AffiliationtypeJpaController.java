/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Affiliationtype;
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
public class AffiliationtypeJpaController {

    public AffiliationtypeJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Affiliationtype affiliationtype) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(affiliationtype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAffiliationtype(affiliationtype.getAffiliationTypeID()) != null) {
                throw new PreexistingEntityException("Affiliationtype " + affiliationtype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Affiliationtype affiliationtype) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            affiliationtype = em.merge(affiliationtype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = affiliationtype.getAffiliationTypeID();
                if (findAffiliationtype(id) == null) {
                    throw new NonexistentEntityException("The affiliationtype with id " + id + " no longer exists.");
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
            Affiliationtype affiliationtype;
            try {
                affiliationtype = em.getReference(Affiliationtype.class, id);
                affiliationtype.getAffiliationTypeID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The affiliationtype with id " + id + " no longer exists.", enfe);
            }
            em.remove(affiliationtype);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Affiliationtype> findAffiliationtypeEntities() {
        return findAffiliationtypeEntities(true, -1, -1);
    }

    public List<Affiliationtype> findAffiliationtypeEntities(int maxResults, int firstResult) {
        return findAffiliationtypeEntities(false, maxResults, firstResult);
    }

    private List<Affiliationtype> findAffiliationtypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Affiliationtype.class));
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

    public Affiliationtype findAffiliationtype(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Affiliationtype.class, id);
        } finally {
            em.close();
        }
    }

    public int getAffiliationtypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Affiliationtype> rt = cq.from(Affiliationtype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
