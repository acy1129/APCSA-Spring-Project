package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Utilities {

	public void writeJson(List<Item> itemList) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        String json = gson.toJson(itemList, type);
        
        try (FileWriter file = new FileWriter("src/resource/ItemList.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public List<Item> readJson() {
		JsonParser parser = new JsonParser();
		List<Item> list = new ArrayList<Item>();

        try {

            Object obj = parser.parse(new FileReader("src/resource/ItemList.json"));
            JsonArray jsonItemArray = (JsonArray) obj;
            Gson gson = new Gson();
            
            Iterator<JsonElement> iterator = jsonItemArray.iterator();
            while (iterator.hasNext()) {
            	Item item =  gson.fromJson(iterator.next(),Item.class);
            	list.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
	}
	
}
