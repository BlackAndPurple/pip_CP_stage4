package controllers;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public interface JsonToStringConverter {
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

}
