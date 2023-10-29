package org.example;
import java.util.HashMap;
import java.util.Map;

public class Rq {
    String cmd;
    String action;
    String queryString;

    Map<String, String> paramsMap;

    Rq(String cmd) {

        paramsMap = new HashMap<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        queryString = cmdBits[1].trim();
        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramsMap.put(paramName, paramValue); // 맵에 Name과 Value를 저장
        }
    }

    String getAction() {
        return action;  // 삭제, 수정 등 행위 반환

    }

    public int getParamAsInt(String paramName, int defaultValue) {

        String paramValue = paramsMap.get(paramName);
        // paramName에 일치하는 값이 존재하지 않으면 null을 반환


        if (paramValue != null) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException e) {
            }


        }
    return defaultValue;
    }
}
