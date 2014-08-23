/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Schooltype;
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
public class SchooltypeJpaController {

    public SchooltypeJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Schooltype schooltype) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(schooltype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSchooltype(schooltype.getSchoolTypeID()) != null) {
                throw new PreexistingEntityException("Schooltype " + schooltype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Schooltype schooltype) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            schooltype = em.merge(schooltype);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = schooltype.getSchoolTypeID();
                if (findSchooltype(id) == null) {
                    throw new NonexistentEntityException("The schooltype with id " + id + " no longer exists.");
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
            Schooltype schooltype;
            try {
                schooltype = em.getReference(Schooltype.class, id);
                schooltype.getSchoolTypeID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schooltype with id " + id + " no longer exists.", enfe);
            }
            em.remove(schooltype);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Schooltype> findSchooltypeEntities() {
        return findSchooltypeEntities(true, -1, -1);
    }

    public List<Schooltype> findSchooltypeEntities(int maxResults, int firstResult) {
        return findSchooltypeEntities(false, maxResults, firstResult);
    }

    private List<Schooltype> findSchooltypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Schooltype.class));
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

    public Schooltype findSchooltype(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Schooltype.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchooltypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Schooltype> rt = cq.from(Schooltype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
