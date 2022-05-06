package simbio.se.nheengare.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import simbio.se.nheengare.models.Language.LANGUAGE;

public class Translation extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final LANGUAGE language;
    private final ArrayList<WordWeight> words = new ArrayList<>();

    public Translation(JSONObject json) {
        language = Language.chooseLanguageUsingId(json.optInt("lang"));
        JSONArray array = json.optJSONArray("translate");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            words.add(new WordWeight(array.optJSONObject(c)));
        }
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public ArrayList<WordWeight> getWords() {
        return words;
    }

}
