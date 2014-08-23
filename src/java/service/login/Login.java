/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.login;

import dao.AccountJpaController;
import entity.Account;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Eric
 */
public class Login {

    static AccountJpaController ajc = new AccountJpaController();

    public boolean login(int id) {
        if (ajc.findAccount(new Integer(id)) == null) {
            return false;
        } else {
            return true;
        }
    }

    public String login(String username, String password, String type) {
        EntityManager em = ajc.getEntityManager();
        try {
            if (type.equals("TelNumber")) {
                Query query = em.createNamedQuery("Account.findByTelNumber");
                query.setParameter("telNumber", username);
                Account a = (Account) query.getSingleResult();
                if (a != null) {
                    if (a.getPassword().equals(password)) {
                        return "succeed";
                    } else {
                        return "wrong password";
                    }
                } else {
                    return "no user";
                }
            } else {
                Query query = em.createNamedQuery("Account.findByEmailAddress");
                query.setParameter("emailAddress", username);
                Account a = (Account) query.getSingleResult();
                if (a != null) {
                    if (a.getPassword().equals(password)) {
                        return "succeed";
                    } else {
                        return "wrong password";
                    }
                } else {
                    return "no user";
                }
            }
        } finally {
            em.close();
        }
    }

    public Account getAccount(int id) {
        return ajc.findAccount(id);
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
