/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.School;
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
public class SchoolJpaController {

    public SchoolJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(School school) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(school);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSchool(school.getSchoolID()) != null) {
                throw new PreexistingEntityException("School " + school + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(School school) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            school = em.merge(school);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = school.getSchoolID();
                if (findSchool(id) == null) {
                    throw new NonexistentEntityException("The school with id " + id + " no longer exists.");
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
            School school;
            try {
                school = em.getReference(School.class, id);
                school.getSchoolID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The school with id " + id + " no longer exists.", enfe);
            }
            em.remove(school);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<School> findSchoolEntities() {
        return findSchoolEntities(true, -1, -1);
    }

    public List<School> findSchoolEntities(int maxResults, int firstResult) {
        return findSchoolEntities(false, maxResults, firstResult);
    }

    private List<School> findSchoolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(School.class));
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

    public School findSchool(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(School.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchoolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<School> rt = cq.from(School.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
