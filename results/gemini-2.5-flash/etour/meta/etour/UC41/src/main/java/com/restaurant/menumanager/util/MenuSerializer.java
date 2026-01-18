package com.restaurant.menumanager.util;

import com.restaurant.menumanager.model.Menu;
import com.restaurant.menumanager.model.MenuItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for serializing and deserializing Menu objects to/from JSON format.
 * This class uses the org.json library for JSON processing.
 */
public class MenuSerializer {

    /**
     * Serializes a Menu object into a JSON string.
     *
     * @param menu The Menu object to serialize.
     * @return A JSON string representation of the Menu.
     */
    public String serialize(Menu menu) {
        if (menu == null) {
            return null;
        }

        JSONObject menuJson = new JSONObject();
        menuJson.put("dayOfWeek", menu.getDayOfWeek());

        JSONArray itemsArray = new JSONArray();
        for (MenuItem item : menu.getItems()) {
            JSONObject itemJson = new JSONObject();
            itemJson.put("name", item.getName());
            itemJson.put("price", item.getPrice());
            itemJson.put("description", item.getDescription());
            itemsArray.put(itemJson);
        }
        menuJson.put("items", itemsArray);

        return menuJson.toString(4); // Pretty print with 4 spaces indent
    }

    /**
     * Deserializes a JSON string into a Menu object.
     *
     * @param jsonString The JSON string to deserialize.
     * @return A Menu object, or null if the jsonString is invalid or null.
     */
    public Menu deserialize(String jsonString) {
        if (jsonString == null || jsonString.trim().isEmpty()) {
            return null;
        }

        try {
            JSONObject menuJson = new JSONObject(jsonString);
            String dayOfWeek = menuJson.getString("dayOfWeek");
            Menu menu = new Menu(dayOfWeek);

            JSONArray itemsArray = menuJson.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject itemJson = itemsArray.getJSONObject(i);
                String name = itemJson.getString("name");
                double price = itemJson.getDouble("price");
                String description = itemJson.getString("description");
                menu.addItem(new MenuItem(name, price, description));
            }
            return menu;
        } catch (Exception e) {
            System.err.println("Error deserializing menu: " + e.getMessage());
            return null;
        }
    }
}