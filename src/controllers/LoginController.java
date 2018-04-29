package controllers;

import beans.IPeopleBean;
import beans.IUserBean;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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
                //String username = req.getParameter("username");
                //Integer password = req.getParameter("password").hashCode();
                StringBuffer jb = new StringBuffer();
                String line = null;
                try {
                    BufferedReader reader = req.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) { /*report an error*/ }

                try {

                    //JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
                    JSONObject jsonObject = new JSONObject(jb.toString());
                    String stringResponse = String.valueOf(userBean.get(jsonObject.getString("username"),
                            jsonObject.getInt("password")) != null);
                    //jsonObject.getJSONObject()
                    PrintWriter out = resp.getWriter();
                    out.print(stringResponse);
                    out.flush();
                    out.close();
                } catch (JSONException e) {
                    // crash and burn
                    throw new IOException("Error parsing JSON request string");
                }
//                PrintWriter out = resp.getWriter();
//                //String stringResponse = String.valueOf(userBean.get(username, password) != null);
//                out.print(stringResponse);
//                out.flush();
//                out.close();
                //}catch (Exception e){}
                break;
            case "person_exists":
                break;
            case "add_user":
                break;
        }
    }
}
