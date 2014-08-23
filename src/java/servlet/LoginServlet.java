/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Account;
import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import service.login.Login;
import service.login.Register;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eric
 */
public class LoginServlet extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private HttpServletResponse response;
    XStream xstream = new XStream(new JsonHierarchicalStreamDriver());

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void login() {
        //String usernamer = this.request.getParameter("UserName");
        //String password = this.request.getParameter("Password");
        //String idstring = this.request.getParameter("id");
        //int id = Integer.parseInt(idstring);
        String loginType = this.request.getParameter("type");
        String userName = this.request.getParameter("userName");
        String password = this.request.getParameter("password");

        Login lg = new Login();
        String loginStatus = lg.login(userName, password, loginType);
        //xstream.alias("user", .class);
        //String userXML = xstream.toXML(user);
        try {
            //this.response.setContentType("application/xml;charset=UTF-8");
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().print(loginStatus);
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void register()
    {
        String loginType = this.request.getParameter("type");
        String userName = this.request.getParameter("userName");
        String password = this.request.getParameter("password");

        Register r = new Register();
        String registerStatus = r.register(userName, password, loginType);
        //xstream.alias("user", .class);
        //String userXML = xstream.toXML(user);
        try {
            //this.response.setContentType("application/xml;charset=UTF-8");
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().print(registerStatus);
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void getAccount() {
        String idstring = this.request.getParameter("id");
        int id = Integer.parseInt(idstring);
        Login lg = new Login();
        Account acc = lg.getAccount(id);
        xstream.alias("account", Account.class);
        String accountJson = xstream.toXML(acc);

        try {
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().print(accountJson);
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
