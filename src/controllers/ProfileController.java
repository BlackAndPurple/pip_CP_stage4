package controllers;

import beans.IContactsBean;
import beans.IParentBean;
import beans.IPeopleBean;
import beans.IUserBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Parent;
import models.ParentContacts;
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
import java.text.SimpleDateFormat;


public class ProfileController extends HttpServlet implements JsonToStringConverter{

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IUserBean userBean;

    @EJB
    IParentBean parentBean;

    @EJB
    IContactsBean contsctsBean;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        String[] pathArr = path.split("/");
        String username;
        JSONObject jsonObject;
        String result = String.valueOf(0);
        ObjectMapper mapper;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy"); //date format
        switch (pathArr[2]) {
            case "get_person":

                try {
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    User user = userBean.get(username);
                    People person = peopleBean.get(user.getPerson_id());
                    mapper = new ObjectMapper();
                    mapper.setDateFormat(df);
                    result = mapper.writeValueAsString(person);
//                    jsonObject = new JSONObject(person);
//                    result = jsonObject.toString();
                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = response.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }



                break;
            case "get_contacts":
                try {
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    User user = userBean.get(username);
                    People person = peopleBean.get(user.getPerson_id());
                    Parent parent = parentBean.get(person.getPerson_id());
                    ParentContacts contacts = contsctsBean.getLatest(parent.getParent_id());
                    mapper = new ObjectMapper();
                    result = mapper.writeValueAsString(contacts);
                   // jsonObject = new JSONObject(contacts);
                    //result = jsonObject.toString();
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
