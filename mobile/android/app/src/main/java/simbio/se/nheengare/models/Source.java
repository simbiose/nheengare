package simbio.se.nheengare.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Source extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final ArrayList<String> tutors = new ArrayList<>();
    private final ArrayList<String> urls = new ArrayList<>();
    private final String isbn;

    public Source(JSONObject json) {
        id = json.optInt("id");
        isbn = json.optString("ISBN");

        JSONArray array = json.optJSONArray("tutors");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            tutors.add(array.optString(c));
        }

        array = json.optJSONArray("urls");
        if (array == null) return;
        for (int c = 0; c < array.length(); c++) {
            urls.add(array.optString(c));
        }
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getTutors() {
        return tutors;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public String getIsbn() {
        return isbn;
    }

}
