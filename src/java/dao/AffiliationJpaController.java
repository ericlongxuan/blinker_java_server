/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Affiliation;
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
public class AffiliationJpaController {

    public AffiliationJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Affiliation affiliation) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(affiliation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAffiliation(affiliation.getAffiliationID()) != null) {
                throw new PreexistingEntityException("Affiliation " + affiliation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Affiliation affiliation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            affiliation = em.merge(affiliation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = affiliation.getAffiliationID();
                if (findAffiliation(id) == null) {
                    throw new NonexistentEntityException("The affiliation with id " + id + " no longer exists.");
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
            Affiliation affiliation;
            try {
                affiliation = em.getReference(Affiliation.class, id);
                affiliation.getAffiliationID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The affiliation with id " + id + " no longer exists.", enfe);
            }
            em.remove(affiliation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Affiliation> findAffiliationEntities() {
        return findAffiliationEntities(true, -1, -1);
    }

    public List<Affiliation> findAffiliationEntities(int maxResults, int firstResult) {
        return findAffiliationEntities(false, maxResults, firstResult);
    }

    private List<Affiliation> findAffiliationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Affiliation.class));
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

    public Affiliation findAffiliation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Affiliation.class, id);
        } finally {
            em.close();
        }
    }

    public int getAffiliationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Affiliation> rt = cq.from(Affiliation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
