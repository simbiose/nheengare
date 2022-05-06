package simbio.se.nheengare.models;

import org.json.JSONObject;

public class GrammaticalClassification extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;

    public GrammaticalClassification(JSONObject json) {
        id = json.optInt("id");
        name = json.optString("name");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
