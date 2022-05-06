package simbio.se.nheengare.models;

import org.json.JSONObject;

public class WordWeight extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int wordId;
    private final double weight;

    public WordWeight(JSONObject json) {
        wordId = json.optInt("word");
        weight = json.optDouble("weight");
    }

    public int getWordId() {
        return wordId;
    }

    public double getWeight() {
        return weight;
    }

}
