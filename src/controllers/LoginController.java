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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginController extends HttpServlet {

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IUserBean userBean;

    private StringBuffer getJsonString(HttpServletRequest req){
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { return null; }
        return jb;
    }

    private Date parseDate(String stringDate){
        Date date = null;
        if ((stringDate != null) && !stringDate.equals("")) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        JSONObject jsonObject;
        switch (pathArr[2]) {
            case "username_exists":
                break;
            case "user_exists":
//                StringBuffer jb = new StringBuffer();
//                String line = null;
//                try {
//                    BufferedReader reader = req.getReader();
//                    while ((line = reader.readLine()) != null)
//                        jb.append(line);
//                } catch (Exception e) { /*report an error*/ }

                try {

                    //jsonObject = new JSONObject(jb.toString());
                    jsonObject = new JSONObject(getJsonString(req).toString());
                    String stringResponse = String.valueOf(userBean.get(jsonObject.getString("username"),
                            jsonObject.getInt("password")) != null);
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
            case "add_user":
                break;
        }
    }
}
