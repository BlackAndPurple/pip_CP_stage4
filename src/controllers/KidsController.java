package controllers;

import beans.IKidBean;
import beans.IParentBean;
import beans.IPeopleBean;
import beans.IUserBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataPass.KidCard;
import models.Kid;
import models.Parent;
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        String[] pathArr = path.split("/");
        String result = String.valueOf(0);
        User user;
        JSONObject jsonObject;
        String username;
        People person;
        ObjectMapper mapper;

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
