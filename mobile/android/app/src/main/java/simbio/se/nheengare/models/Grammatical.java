package simbio.se.nheengare.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import simbio.se.nheengare.R;

public class Grammatical extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;
    private final ArrayList<GrammaticalClassification> classifications = new ArrayList<>();

    public Grammatical(JSONObject json, Context context) {
        id = json.optInt("id");

        switch (id) {
            case 1:
                name = context.getString(R.string.grammatical_class_article);
                break;
            case 2:
                name = context.getString(R.string.grammatical_class_adjective);
                break;
            case 3:
                name = context.getString(R.string.grammatical_class_numeral);
                break;
            case 4:
                name = context.getString(R.string.grammatical_class_pronoun);
                break;
            case 5:
                name = context.getString(R.string.grammatical_class_verb);
                break;
            case 6:
                name = context.getString(R.string.grammatical_class_adverb);
                break;
            case 7:
                name = context.getString(R.string.grammatical_class_preposition);
                break;
            case 8:
                name = context.getString(R.string.grammatical_class_conjunction);
                break;
            case 9:
                name = context.getString(R.string.grammatical_class_interjection);
                break;
            case 10:
                name = context.getString(R.string.grammatical_class_noun);
                break;
            default:
                name = json.optString("name");
                break;
        }

        JSONArray array = json.optJSONArray("classification");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            classifications.add(new GrammaticalClassification(array.optJSONObject(c)));
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GrammaticalClassification> getClassifications() {
        return classifications;
    }

    @Override
    public boolean isYourThisId(int id) {
        return id == this.id;
    }

}
