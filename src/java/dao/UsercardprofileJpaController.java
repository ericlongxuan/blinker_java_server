/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import entity.Usercardprofile;
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
public class UsercardprofileJpaController {

    public UsercardprofileJpaController() {
        emf = Persistence.createEntityManagerFactory("blinkerPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usercardprofile usercardprofile) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usercardprofile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsercardprofile(usercardprofile.getUserID()) != null) {
                throw new PreexistingEntityException("Usercardprofile " + usercardprofile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usercardprofile usercardprofile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usercardprofile = em.merge(usercardprofile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usercardprofile.getUserID();
                if (findUsercardprofile(id) == null) {
                    throw new NonexistentEntityException("The usercardprofile with id " + id + " no longer exists.");
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
            Usercardprofile usercardprofile;
            try {
                usercardprofile = em.getReference(Usercardprofile.class, id);
                usercardprofile.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usercardprofile with id " + id + " no longer exists.", enfe);
            }
            em.remove(usercardprofile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usercardprofile> findUsercardprofileEntities() {
        return findUsercardprofileEntities(true, -1, -1);
    }

    public List<Usercardprofile> findUsercardprofileEntities(int maxResults, int firstResult) {
        return findUsercardprofileEntities(false, maxResults, firstResult);
    }

    private List<Usercardprofile> findUsercardprofileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usercardprofile.class));
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

    public Usercardprofile findUsercardprofile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usercardprofile.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsercardprofileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usercardprofile> rt = cq.from(Usercardprofile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
