package controllers;

import beans.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataPass.GroupCard;
import dataPass.KidCard;
import models.*;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "KidsController")
public class KidsController extends HttpServlet implements JsonToStringAndDateConverter{

    @EJB
    IUserBean userBean;

    @EJB
    IParentBean parentBean;

    @EJB
    IPeopleBean peopleBean;

    @EJB
    IKidBean kidBean;

    @EJB
    IAccountBean accountBean;

    @EJB
    IMedBean medBean;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        String[] pathArr = path.split("/");
        String result = String.valueOf(0);
        User user;
        JSONObject jsonObject;
        String username;
        People person;
        ObjectMapper mapper;
        Long kidId;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy"); //date format

        try{
        switch (pathArr[2]) {
            case "get_kid_card":
                jsonObject = new JSONObject(getJsonString(request).toString());
                username = jsonObject.getString("username");
                user = userBean.get(username);
                person = peopleBean.get(user.getPerson_id());
                Parent parent = parentBean.get(person.getPerson_id());
                List<Kid> kids = kidBean.getKidsByParentId(parent.getParent_id());
                List<KidCard> kidCards = new ArrayList<>();
                for (Kid kid : kids){
                    person = kid.getPerson();
                    kidCards.add(new KidCard(kid.getKid_id(), person.getName(),
                            person.getMiddleName(), person.getSurname(),
                            person.isSex()));
                }
                mapper = new ObjectMapper();
                result = mapper.writeValueAsString(kidCards);
                break;
            case "get_group_cards":
                jsonObject = new JSONObject(getJsonString(request).toString());
                kidId = jsonObject.getLong("kidId");
                List<KidAccount> accounts = accountBean.get(kidId);
                List<GroupCard> groupCards = new ArrayList<>();

                for(KidAccount account : accounts){
                    groupCards.add(new GroupCard(account.getGroup().getName(),
                                                account.getGroup().getTypeOfGroup(),
                                                account.getDate_of_creating(),
                                                account.getDate_of_leaving()));
                }

                mapper = new ObjectMapper();
                mapper.setDateFormat(df);
                result = mapper.writeValueAsString(groupCards);

                break;

            case "get_latest_med":
                jsonObject = new JSONObject(getJsonString(request).toString());
                kidId = jsonObject.getLong("kidId");
                MedInfo medInfo = medBean.getLatest(kidId);
                mapper = new ObjectMapper();
                mapper.setDateFormat(df);
                result = mapper.writeValueAsString(medInfo);
                break;

            case "get_kid_person":
                jsonObject = new JSONObject(getJsonString(request).toString());
                kidId = jsonObject.getLong("kidId");
                Kid kid = kidBean.get(kidId);
                person = peopleBean.get(kid.getPerson().getPerson_id());
                mapper = new ObjectMapper();
                mapper.setDateFormat(df);
                result = mapper.writeValueAsString(person);
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
