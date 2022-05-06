package simbio.se.nheengare.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import simbio.se.nheengare.models.Language.LANGUAGE;

public class Word extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final LANGUAGE language;
    private final ArrayList<String> writes = new ArrayList<>();
    private final ArrayList<String> afis = new ArrayList<>();
    private final ArrayList<Integer> wordsEqualsIds = new ArrayList<>();
    private final ArrayList<Translation> translations = new ArrayList<>();
    private final ArrayList<Integer> sourceIds = new ArrayList<>();
    private final ArrayList<Integer> grammaticalsIds = new ArrayList<>();
    private final ArrayList<ExamplePhrases> examples = new ArrayList<>();
    private final HashMap<Integer, Integer> grammaticals = new HashMap<>();

    public Word(JSONObject json) {
        id = json.optInt("id");
        language = Language.chooseLanguageUsingId(json.optInt("lang"));

        JSONArray array = json.optJSONArray("examples");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            examples.add(new ExamplePhrases(array.optJSONObject(c)));
        }

        array = json.optJSONArray("grammatical");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            JSONObject o = array.optJSONObject(c);
            int x = o.optInt("class");
            grammaticalsIds.add(x);

            JSONArray a = o.optJSONArray("classification");
            if (a == null) return;
            for (int i = 0; i < a.length(); i++) {
                grammaticals.put(x, a.optInt(i));
            }
        }

        array = json.optJSONArray("source");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            sourceIds.add(array.optInt(c));
        }

        array = json.optJSONArray("translations");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            translations.add(new Translation(array.optJSONObject(c)));
        }

        array = json.optJSONArray("equals");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            wordsEqualsIds.add(array.optInt(c));
        }

        array = json.optJSONArray("write");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            writes.add(array.optString(c));
        }

        array = json.optJSONArray("afi");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            afis.add(array.optString(c));
        }
    }

    @Override
    public int compareTo(AbstractModel another) {
        if (another instanceof Word) {
            setCriteriaWeight(writes);
            another.setCriteriaWeight(((Word) another).getWrites());
        }
        return super.compareTo(another);
    }

    public int getId() {
        return id;
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public ArrayList<String> getWrites() {
        return writes;
    }

    public String getWriteUnique() {
        if (writes.size() > 1) {
            return writes.toString();
        } else
            return writes.get(0);
    }

    public ArrayList<Integer> getWordsEqualsIds() {
        return wordsEqualsIds;
    }

    public ArrayList<Translation> getTranslations() {
        return translations;
    }

    public ArrayList<Integer> getSourceIds() {
        return sourceIds;
    }

    public ArrayList<Integer> getGrammaticalsIds() {
        return grammaticalsIds;
    }

    public HashMap<Integer, Integer> getGrammaticals() {
        return grammaticals;
    }

    public ArrayList<ExamplePhrases> getExamples() {
        return examples;
    }

    public ArrayList<String> getAfis() {
        return afis;
    }

    @Override
    public boolean isYourThisId(int id) {
        return id == this.id;
    }

}
