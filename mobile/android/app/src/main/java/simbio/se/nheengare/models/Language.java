package simbio.se.nheengare.models;

import org.json.JSONObject;

public class Language extends AbstractModel {

    private static final long serialVersionUID = 1L;
    private final LANGUAGE id;
    private final String name;
    private final String iso;

    public Language(JSONObject json) {
        name = json.optString("name");
        iso = json.optString("iso");
        id = chooseLanguageUsingId(json.optInt("id"));
    }

    public static LANGUAGE chooseLanguageUsingId(int id) {
        switch (id) {
            case 1:
                return LANGUAGE.NHEENGATU;
            case 2:
                return LANGUAGE.PORTUGUESE;
            case 3:
                return LANGUAGE.SPANISH;
            case 4:
                return LANGUAGE.ENGLISH;
            case 5:
                return LANGUAGE.GUARANI;
            case 6:
                return LANGUAGE.TUPI;
            default:
                return LANGUAGE.UNKNOWN;
        }
    }

    public static int chooseLanguageId(LANGUAGE l) {
        switch (l) {
            case NHEENGATU:
                return 1;
            case PORTUGUESE:
                return 2;
            case SPANISH:
                return 3;
            case ENGLISH:
                return 4;
            case GUARANI:
                return 5;
            case TUPI:
                return 6;
            default:
                return 0;
        }
    }

    public LANGUAGE getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso() {
        return iso;
    }

    public enum LANGUAGE {
        UNKNOWN, NHEENGATU, PORTUGUESE, SPANISH, ENGLISH, GUARANI, TUPI
    }

}
