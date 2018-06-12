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
import java.util.Date;


public class ProfileController extends HttpServlet implements JsonToStringAndDateConverter{

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IUserBean userBean;

    @EJB
    IParentBean parentBean;

    @EJB
    IContactsBean contactsBean;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        String[] pathArr = path.split("/");
        String username;
        User user;
        People person;
        JSONObject jsonObject;
        String result = String.valueOf(0);
        ObjectMapper mapper;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy"); //date format
        switch (pathArr[2]) {
            case "update_person":
                result = String.valueOf(false);
                try{
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    String name = jsonObject.getString("name");
                    String middleName = jsonObject.getString("middleName");
                    String surname = jsonObject.getString("surname");
                    Boolean sex = jsonObject.getBoolean("sex");
                    Date dateOfBirth = null;
                    try {
                        dateOfBirth = parseDate(jsonObject.getString("dateOfBirth"));
                    }catch(Exception e){
                       // dateOfBirth = new Date();
                    }
                    user = userBean.get(username);
                    peopleBean.update(user.getPerson_id(), name, middleName, surname, sex, dateOfBirth);
                    result = String.valueOf(true);

                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = response.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }

                break;
            case "post_contacts":
                result = String.valueOf(false);
                try{
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    String homeAddress = jsonObject.getString("homeAddress");
                    String job = jsonObject.getString("job");
                    String jobPhoneNumber = jsonObject.getString("jobPhoneNumber");
                    String cellphoneNumber = jsonObject.getString("cellphoneNumber");
                    user = userBean.get(username);
                    Parent parent = parentBean.get(user.getPerson_id());
                    contactsBean.add(parent, homeAddress,job,jobPhoneNumber,cellphoneNumber);
                    result = String.valueOf(true);

                }catch(JSONException e){
                    throw new IOException("Error parsing JSON request string");
                }finally {
                    PrintWriter out = response.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                }
                break;
            case "get_person":

                try {
                    jsonObject = new JSONObject(getJsonString(request).toString());
                    username = jsonObject.getString("username");
                    user = userBean.get(username);
                    person = peopleBean.get(user.getPerson_id());
                    mapper = new ObjectMapper();
                    mapper.setDateFormat(df);
                    result = mapper.writeValueAsString(person);
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
                    user = userBean.get(username);
                    person = peopleBean.get(user.getPerson_id());
                    Parent parent = parentBean.get(person.getPerson_id());
                    ParentContacts contacts = contactsBean.getLatest(parent.getParent_id());
                    mapper = new ObjectMapper();
                    result = mapper.writeValueAsString(contacts);
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
