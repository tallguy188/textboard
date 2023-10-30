package org.example.domain;
import org.example.standard.util.Ut;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;
    private String action;
    private String queryString;

    private Map<String, String> paramsMap;

    public Rq(String cmd) {

        paramsMap = new HashMap<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();

        if (cmdBits.length == 1){
            return;
        }

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

    public String getAction() {
        return action;  // 삭제, 수정 등 행위 반환

    }

    public int getParamAsInt(String paramName, int defaultValue) {

        return Ut.str.parseInt(paramsMap.get(paramName), defaultValue);

        // 클래스 안에 내부클래스 str을 만든다.
    }
}