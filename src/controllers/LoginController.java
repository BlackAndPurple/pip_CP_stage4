package controllers;

import beans.IMailBean;
import beans.IParentBean;
import beans.IPeopleBean;
import beans.IUserBean;
import models.Parent;
import models.People;
import models.User;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LoginController extends HttpServlet implements JsonToStringAndDateConverter{

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IUserBean userBean;

    @EJB
    IParentBean parentBean;

    @EJB
    IMailBean mailBean;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        JSONObject jsonObject;
        String username;
        String result = String.valueOf(false);
        switch (pathArr[2]) {
            case "username_exists":
                try{
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    username = jsonObject.getString("username");
                    User user = userBean.get(username);
                    if (user != null){
                        result = String.valueOf(true);
                    }
                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = resp.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }
                break;
            case "user_exists":

                try {

                    //jsonObject = new JSONObject(jb.toString());
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    String password = jsonObject.getString("password");
                    String stringResponse = String.valueOf(userBean.get(jsonObject.getString("username"),
                            password.hashCode()) != null);
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

                try{
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    String name = jsonObject.getString("name");
                    String middleName = jsonObject.has("middle_name") ? jsonObject.getString("middle_name") : null;
                    String surname = jsonObject.getString("surname");
                    Boolean sex = jsonObject.getString("sex").equals("male");
                    Date dateOfBirth = parseDate(jsonObject.getString("date_of_birth"));
                    String stringResponse = String.valueOf(peopleBean.getId(peopleBean.get(name, middleName, surname, sex, dateOfBirth)));
                    PrintWriter out = resp.getWriter();
                    out.print(stringResponse);
                    out.flush();
                    out.close();
                } catch (JSONException e) {
                    // crash and burn
                    throw new IOException("Error parsing JSON request string");
                }

                break;
            case "parent_exists":
                result = String.valueOf(false);
                try{
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    Long personId = jsonObject.getLong("personId");
                    Parent parent = parentBean.get(personId);
                    if (parent != null){
                        result = String.valueOf(true);
                    }
                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = resp.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }
                break;

            case "reset_password":
                try{
                    //do smth
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    username = jsonObject.getString("username");
                    User user = userBean.get(username);
                    Random random = new Random();
                    String newPassword = String.valueOf(random.nextInt(10000) + 10000);
                    user.setPassword(newPassword.hashCode());
                    if (userBean.update(user))
                        result = String.valueOf(true);
                    mailBean.send(newPassword, user.getEmail());

                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = resp.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }

                break;

            case "add_user":
                String response = String.valueOf(false);
                try{
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    username = jsonObject.getString("username");
                    String password = jsonObject.getString("password");
                    String personId = jsonObject.getString("personId");
                    String email = jsonObject.getString("email");
                    People person = peopleBean.get(Long.parseLong(personId));
                    if (person != null) {
                        userBean.add(username, password.hashCode(), email, person);
                        response = String.valueOf(true);
                    }else response = String.valueOf(false);

                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                    //response = String.valueOf(false);
                }finally {
                    PrintWriter out = resp.getWriter();
                    out.print(response);
                    out.flush();
                    out.close();
                }
                break;
        }
    }
}
