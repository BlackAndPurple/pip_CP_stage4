package controllers;

import beans.IUserBean;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(name = "SettingsController")
public class SettingsController extends HttpServlet implements JsonToStringAndDateConverter {

    @EJB
    IUserBean userBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI().substring(request.getContextPath().length());
        String[] pathArr = path.split("/");
        String result = String.valueOf(0);
        JSONObject jsonObject;
        User user;
        String username;
        ObjectMapper mapper;
        String password;
        try{

            switch (pathArr[2]) {
                case "get_user":
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    user = userBean.get(username);
                    mapper = new ObjectMapper();
                    result = mapper.writeValueAsString(user);
                    //result = user.getEmail();
                    break;

                case "post_email":
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    String email = jsonObject.getString("email");
                    username = jsonObject.getString("username");
                    user = userBean.get(username);
                    user.setEmail(email);
                    result = String.valueOf(userBean.update(user));
                    break;

                case "check_password":
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    password = jsonObject.getString("password");
                    username = jsonObject.getString("username");
                    user = userBean.get(username);
                    result = String.valueOf(user.getPassword() == password.hashCode());
                    break;

                case "post_password":
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    password = jsonObject.getString("password");
                    username = jsonObject.getString("username");
                    user = userBean.get(username);
                    user.setPassword(password.hashCode());
                    result = String.valueOf(userBean.update(user));
                    break;
            }

        }catch(JSONException e){
            throw new IOException("Error parsing JSON request string");
        }finally {
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
