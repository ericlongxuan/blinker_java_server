/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.login;

import dao.AccountJpaController;
import dao.exceptions.PreexistingEntityException;
import entity.Account;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Eric
 */
public class Register {

    static AccountJpaController ajc = new AccountJpaController();

    public String register(String username, String password, String type) {
        if (type.equals("TelNumber")) {
            try {
                Account a = new Account();

                a.setTelNumber(username);
                a.setPassword(password);
                ajc.create(a);
                return "succeed";
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                return "fail";
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                return "fail";
            }
        } else {
            try {
                Account a = new Account();
                a.setEmailAddress(username);
                a.setPassword(password);
                ajc.create(a);
                return "succeed";
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                return "fail";
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                return "fail";
            }
        }
    }
}
