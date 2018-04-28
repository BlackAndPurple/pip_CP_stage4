package controllers;

import beans.IPeopleBean;
import beans.IUserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IUserBean userBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        switch (pathArr[2]) {
            case "username_exists":
                break;
            case "user_exists":
                break;
            case "person_exists":
                break;
            case "add_user":
                break;
        }
    }
}
