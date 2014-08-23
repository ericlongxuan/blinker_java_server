/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Relationshipstatus;
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
public class RelationshipstatusJpaController {

    public RelationshipstatusJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relationshipstatus relationshipstatus) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relationshipstatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelationshipstatus(relationshipstatus.getRelationshipStatusID()) != null) {
                throw new PreexistingEntityException("Relationshipstatus " + relationshipstatus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relationshipstatus relationshipstatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relationshipstatus = em.merge(relationshipstatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = relationshipstatus.getRelationshipStatusID();
                if (findRelationshipstatus(id) == null) {
                    throw new NonexistentEntityException("The relationshipstatus with id " + id + " no longer exists.");
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
            Relationshipstatus relationshipstatus;
            try {
                relationshipstatus = em.getReference(Relationshipstatus.class, id);
                relationshipstatus.getRelationshipStatusID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relationshipstatus with id " + id + " no longer exists.", enfe);
            }
            em.remove(relationshipstatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relationshipstatus> findRelationshipstatusEntities() {
        return findRelationshipstatusEntities(true, -1, -1);
    }

    public List<Relationshipstatus> findRelationshipstatusEntities(int maxResults, int firstResult) {
        return findRelationshipstatusEntities(false, maxResults, firstResult);
    }

    private List<Relationshipstatus> findRelationshipstatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relationshipstatus.class));
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

    public Relationshipstatus findRelationshipstatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relationshipstatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelationshipstatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relationshipstatus> rt = cq.from(Relationshipstatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
