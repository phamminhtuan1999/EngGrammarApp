package com.ductho.nguphaptienganh.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter implements Serializable {
 
 @TypeConverter // note this annotation
    public String fromStringsList(List<String> strings) {
        if (strings == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(strings, type);
        return json;
    }
 
    @TypeConverter // note this annotation
    public List<String> toStringsList(String strings) {
        if (strings == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> stringList = gson.fromJson(strings, type);
        return stringList;
    }
 
}