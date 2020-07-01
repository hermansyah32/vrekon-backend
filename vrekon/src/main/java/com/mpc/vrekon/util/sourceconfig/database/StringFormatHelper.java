package com.mpc.vrekon.util.sourceconfig.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StringFormatHelper {
    public String strConvertUC(String str){
        Integer index = 0;
        do {
            index = str.indexOf("_");
            String tmp = str.charAt(index+1)+"";
            str = str.replace("_"+str.charAt(index+1), tmp.toUpperCase());
        } while (index >= 0);

        return str;
    }

    public String strConvertCU(String str){
        Integer countString = str.length();
        String _str = new String();

        for(Integer i = 0; i < countString; i++){
            if (Character.isUpperCase(str.charAt(i)) && i != 0) {
                String tmp = str.charAt(i) + "";
                _str += "_" + tmp.toLowerCase();
            }else if (Character.isUpperCase(str.charAt(i)) && i == 0){
                String tmp = str.charAt(i) + "";
                _str += tmp.toLowerCase();
            }else {
                _str += str.charAt(i);
            }
        }

        return _str;
    }

    public Object prettyPrintObject(Object object){
        Object result = null;
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            result = gson.toJson(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
