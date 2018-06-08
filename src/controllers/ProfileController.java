package controllers;

import beans.IPeopleBean;
import beans.IUserBean;
import models.People;
import models.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ProfileController extends HttpServlet implements JsonToStringConverter{

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IUserBean userBean;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        String[] pathArr = path.split("/");
        String username;
        JSONObject jsonObject;
        String result = String.valueOf(0);
        switch (pathArr[2]) {
            case "get_person":

                try {
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    User user = userBean.get(username);
                    People person = peopleBean.get(user.getPerson_id());
                    jsonObject = new JSONObject(person);
                    result = jsonObject.toString();
                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = response.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }



                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
