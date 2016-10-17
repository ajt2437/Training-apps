package com.bignerdranch.android.photogallery;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by alepena01 on 9/27/16.
 */
public class GalleryDeserializer implements JsonDeserializer<GalleryItem[]> {

    @Override
    public GalleryItem[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement photos = json.getAsJsonObject().get("photos");
        JsonElement photoArray = photos.getAsJsonObject().get("photo");

        //Deserializing it
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(new GalleryItemFieldNamingStrategy()).create();

        return gson.fromJson(photoArray, GalleryItem[].class);

    }

    private class GalleryItemFieldNamingStrategy implements FieldNamingStrategy {

        @Override
        public String translateName(Field f) {
            switch (f.getName()) {
                case "mId":
                    return "id";
                case "mCaption":
                    return "title";
                case "mUrl":
                    return "url_s";
                default:
                    return f.getName();
            }
        }
    }
}
