package com.sankadilshan.myday.expense.util;

import org.apache.tomcat.util.json.JSONParser;

public class GraphqlUtilResponse {

    public static Object generateGraphqlResponse(Object obj){
        return obj;
    }

    public static Object convertStringToJson(String str) {
        try {
            JSONParser jsonParser =  new JSONParser(str);
            return jsonParser.object();
        } catch (Exception e) {
            return null;
        }
    }
}
