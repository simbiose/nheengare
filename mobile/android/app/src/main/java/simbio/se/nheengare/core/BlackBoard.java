package simbio.se.nheengare.core;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.models.Grammatical;
import simbio.se.nheengare.models.Language;
import simbio.se.nheengare.models.Source;
import simbio.se.nheengare.models.Word;

public class BlackBoard {

    private static BlackBoard blackBoard;

    private final ArrayList<Source> sources = new ArrayList<>();
    private final ArrayList<Language> languages = new ArrayList<>();
    private final ArrayList<Grammatical> grammaticals = new ArrayList<>();
    private final ArrayList<Word> words = new ArrayList<>();
    private final Options options;

    public BlackBoard(Context context) {
        options = new Options(context);

        try {
            InputStream is = context.getResources().openRawResource(R.raw.db);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();

            JSONObject jsonObject = new JSONObject(new String(buffer));
            JSONArray jsonArray = jsonObject.optJSONArray("sources");
            if (jsonArray == null) return;
            for (int c = 0; c < jsonArray.length(); c++) {
                sources.add(new Source(jsonArray.optJSONObject(c)));
            }

            jsonArray = jsonObject.optJSONArray("languages");
            if (jsonArray == null) return;
            for (int c = 0; c < jsonArray.length(); c++) {
                languages.add(new Language(jsonArray.optJSONObject(c)));
            }

            jsonArray = jsonObject.optJSONArray("grammatical_class");
            if (jsonArray == null) return;
            for (int c = 0; c < jsonArray.length(); c++) {
                grammaticals.add(new Grammatical(jsonArray.optJSONObject(c), context));
            }

            jsonArray = jsonObject.optJSONArray("words");
            if (jsonArray == null) return;
            for (int c = 0; c < jsonArray.length(); c++) {
                words.add(new Word(jsonArray.optJSONObject(c)));
            }
        } catch (Exception ignored) {
        }
    }

    public static BlackBoard getBlackBoard(Context context) {
        if (blackBoard == null) {
            blackBoard = new BlackBoard(context);
        }
        return blackBoard;
    }

    public Options getOptions() {
        return options;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public ArrayList<Grammatical> getGrammaticals() {
        return grammaticals;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public <T extends ISearch> T getObjectWithId(ArrayList<T> items, int id) {
        for (T iSearch : items) {
            if (iSearch.isYourThisId(id)) {
                return iSearch;
            }
        }
        return null;
    }

    public Word getWordWithId(int id) {
        return getObjectWithId(words, id);
    }

    public Grammatical getGrammaticalWithId(int id) {
        return getObjectWithId(grammaticals, id);
    }

}
