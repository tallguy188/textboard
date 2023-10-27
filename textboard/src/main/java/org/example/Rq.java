package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rq {
    String cmd;
    String action;
    String queryString;

    List<String> paramNames;  // paramName들을 저장
    List<String> paramValues; // paramValue들을 저장
    Rq(String cmd) {

        paramNames = new ArrayList<>();

        paramValues = new ArrayList<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?",2);
        action = cmdBits[0].trim();
        queryString = cmdBits[0].trim();
        String[] queryStringBits = queryString.split("&");

        for (int i=0; i<queryStringBits.length;i++) {
            String queryParamStr = queryStringBits[i];
            String [] queryParamStrBits = queryParamStr.split("=",2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramNames.add(paramName);
            paramValues.add(paramValue);
        }
    }
    String getAction() {
        return action;

    }

    public int getParamAsInt(String paramName, int defaultValue) {

        int index = paramNames.indexOf(paramName);  // paramName이 없다면 -1반환

        if (index == -1) return defaultValue;

        String paramValue = paramValues.get(index);

        try {

            return Integer.parseInt(paramValue);
        }
        catch(NumberFormatException e) {
            return defaultValue;
        }
    }


}
