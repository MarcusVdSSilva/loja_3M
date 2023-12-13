package com.example.projetoa3.Biblioteca;

import com.example.projetoa3.Objetos.Retorno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Json {

    public static Gson novoGson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson;
    }

    public static <Generico> Generico toObject(String json, Class<Generico> Generic){
        if(Biblioteca.isStringVazia(json))
            return null;
        try {
            Gson gson = novoGson();
            return gson.fromJson(json, Generic);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Retorno toRetorno(String json){
        if(Biblioteca.isStringVazia(json))
            return new Retorno();
        try {
            Gson gson = novoGson();
            return gson.fromJson(json, Retorno.class);
        }
        catch (Exception e){
            return new Retorno();
        }
    }

    public static boolean toBoolean(String json){
        if(json.contentEquals("1"))
            return true;
        else if(json.contentEquals("0"))
            return false;

        if(Biblioteca.isStringVazia(json))
            return false;
        try {
            Gson gson = novoGson();
            return gson.fromJson(json, Boolean.class);
        }
        catch (Exception e){
            return false;
        }
    }


    public static <T> ArrayList<T> toArrayList(String json, Class<T> typeClass) {
        if (Biblioteca.isStringVazia(json))
            return new ArrayList<>();

        try {
            Gson gson = novoGson();

            Type type = TypeToken.getParameterized(ArrayList.class, typeClass).getType();
            return gson.fromJson(json, type);
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static String toJson(Object json){
        Gson gson = Json.novoGson();
        return gson.toJson(json);
    }

    public static int toInteger(String json) {
        try {
            Gson gson = novoGson();
            return gson.fromJson(json, Integer.class);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
