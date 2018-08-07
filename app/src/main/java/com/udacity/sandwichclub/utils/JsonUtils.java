package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
       // Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            List<String> setAlsoKnownAsList = convertJsonArrayToList(alsoKnownAsArray);

            String setDescription = jsonObject.getString("description");
            String setImage = jsonObject.getString("image");
            String setPlaceOfOrigin = jsonObject.getString("placeOfOrigin");

            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            List<String> setIngredientsList = convertJsonArrayToList(ingredientsArray);

            return new Sandwich(mainName, setAlsoKnownAsList, setPlaceOfOrigin, setDescription, setImage, setIngredientsList);

        }  catch (JSONException e) {
            Log.d(TAG, "parseSandwichJson: Json Exception thrown." + e.getMessage());
        }
        return null;
    }

    private static List<String> convertJsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> listObject = new ArrayList<>(jsonArray.length());
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++){
                listObject.add(jsonArray.getString(i));
            }
        }
        return listObject;
    }
}
