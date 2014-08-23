/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Gender;
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
public class GenderJpaController {

    public GenderJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gender gender) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gender);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGender(gender.getGenderID()) != null) {
                throw new PreexistingEntityException("Gender " + gender + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gender gender) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gender = em.merge(gender);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gender.getGenderID();
                if (findGender(id) == null) {
                    throw new NonexistentEntityException("The gender with id " + id + " no longer exists.");
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
            Gender gender;
            try {
                gender = em.getReference(Gender.class, id);
                gender.getGenderID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gender with id " + id + " no longer exists.", enfe);
            }
            em.remove(gender);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gender> findGenderEntities() {
        return findGenderEntities(true, -1, -1);
    }

    public List<Gender> findGenderEntities(int maxResults, int firstResult) {
        return findGenderEntities(false, maxResults, firstResult);
    }

    private List<Gender> findGenderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gender.class));
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

    public Gender findGender(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gender.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gender> rt = cq.from(Gender.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
