package controllers;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface JsonToStringAndDateConverter {
    default StringBuffer getJsonString(HttpServletRequest req){
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { return null; }
        return jb;
    }
    default Date parseDate(String stringDate){
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

}
