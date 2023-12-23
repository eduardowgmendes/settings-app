package br.com.edu.settingsapplication.database.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.settingsapplication.domain.QuickAction;

public class QuickActionsConverter {

    @TypeConverter
    public static List<QuickAction> fromString(String value) {
        Type listType = new TypeToken<List<QuickAction>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<QuickAction> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
