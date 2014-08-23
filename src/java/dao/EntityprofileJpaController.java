/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Entityprofile;
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
public class EntityprofileJpaController {

    public EntityprofileJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entityprofile entityprofile) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entityprofile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntityprofile(entityprofile.getEntityID()) != null) {
                throw new PreexistingEntityException("Entityprofile " + entityprofile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entityprofile entityprofile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entityprofile = em.merge(entityprofile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entityprofile.getEntityID();
                if (findEntityprofile(id) == null) {
                    throw new NonexistentEntityException("The entityprofile with id " + id + " no longer exists.");
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
            Entityprofile entityprofile;
            try {
                entityprofile = em.getReference(Entityprofile.class, id);
                entityprofile.getEntityID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entityprofile with id " + id + " no longer exists.", enfe);
            }
            em.remove(entityprofile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entityprofile> findEntityprofileEntities() {
        return findEntityprofileEntities(true, -1, -1);
    }

    public List<Entityprofile> findEntityprofileEntities(int maxResults, int firstResult) {
        return findEntityprofileEntities(false, maxResults, firstResult);
    }

    private List<Entityprofile> findEntityprofileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entityprofile.class));
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

    public Entityprofile findEntityprofile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entityprofile.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntityprofileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entityprofile> rt = cq.from(Entityprofile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
