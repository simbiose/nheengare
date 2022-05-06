package simbio.se.nheengare.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Phrase extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int languageId;
    private final String phrase;
    private final ArrayList<Phrase> translations = new ArrayList<>();

    public Phrase(JSONObject json) {
        languageId = json.optInt("lang");
        phrase = json.optString("phrase");
        JSONArray array = json.optJSONArray("translations");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            translations.add(new Phrase(array.optJSONObject(c)));
        }
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getPhrase() {
        return phrase;
    }

    public ArrayList<Phrase> getTranslations() {
        return translations;
    }

}
