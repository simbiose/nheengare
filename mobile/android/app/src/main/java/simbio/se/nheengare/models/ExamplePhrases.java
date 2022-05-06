package simbio.se.nheengare.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamplePhrases extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int sourceId;
    private final ArrayList<Phrase> sentences = new ArrayList<>();

    public ExamplePhrases(JSONObject json) {
        sourceId = json.optInt("source");
        JSONArray array = json.optJSONArray("sentences");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            sentences.add(new Phrase(array.optJSONObject(c)));
        }
    }

    public int getSourceId() {
        return sourceId;
    }

    public ArrayList<Phrase> getSentences() {
        return sentences;
    }

}
