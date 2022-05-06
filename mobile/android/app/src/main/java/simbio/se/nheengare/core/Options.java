package simbio.se.nheengare.core;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Options {

    private static final ArrayList<IOptionsChangedListener> optionsChangedListeners = new ArrayList<>();
    private static final String preferencesKey = "simbio.se.nheengare";
    private static final String pkFilterSearch = "pkFilterSearch";
    private static final String pkFilterTranslation = "pkFilterTranslation";
    private static final String pkFsNh = "pkFsNh";
    private static final String pkFsPt = "pkFsPt";
    private static final String pkFsEs = "pkFsEs";
    private static final String pkFsIn = "pkFsIn";
    private static final String pkFtNh = "pkFtNh";
    private static final String pkFtPt = "pkFtPt";
    private static final String pkFtEs = "pkFtEs";
    private static final String pkFtIn = "pkFtIn";
    private final SharedPreferences prefs;

    public Options(Context context) {
        prefs = context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE);
    }

    public static void addOptionsChangeListener(IOptionsChangedListener listener) {
        optionsChangedListeners.add(listener);
    }

    public static void removeOptionsChangeListener(IOptionsChangedListener listener) {
        optionsChangedListeners.remove(listener);
    }

    private void sendUpdateMessageToListeners() {
        for (IOptionsChangedListener listener : optionsChangedListeners) {
            listener.onOptionsChanged(this);
        }
    }

    public boolean filterSearchLanguages() {
        return prefs.getBoolean(pkFilterSearch, false);
    }

    public void setFilterSearchLanguages(Boolean filter) {
        prefs.edit().putBoolean(pkFilterSearch, filter).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterTranslationLanguages() {
        return prefs.getBoolean(pkFilterTranslation, false);
    }

    public void setFilterTranslationLanguages(Boolean filter) {
        prefs.edit().putBoolean(pkFilterTranslation, filter).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterSearchShowNheengatu() {
        return prefs.getBoolean(pkFsNh, true);
    }

    public void setFilterSearchShowNheengatu(Boolean show) {
        prefs.edit().putBoolean(pkFsNh, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterSearchShowPortuguese() {
        return prefs.getBoolean(pkFsPt, true);
    }

    public void setFilterSearchShowPortuguese(Boolean show) {
        prefs.edit().putBoolean(pkFsPt, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterSearchShowSpanish() {
        return prefs.getBoolean(pkFsEs, true);
    }

    public void setFilterSearchShowSpanish(Boolean show) {
        prefs.edit().putBoolean(pkFsEs, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterSearchShowEnglish() {
        return prefs.getBoolean(pkFsIn, true);
    }

    public void setFilterSearchShowEnglish(Boolean show) {
        prefs.edit().putBoolean(pkFsIn, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterTranslationShowNheengatu() {
        return prefs.getBoolean(pkFtNh, true);
    }

    public void setFilterTranslationShowNheengatu(Boolean show) {
        prefs.edit().putBoolean(pkFtNh, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterTranslationShowPortuguese() {
        return prefs.getBoolean(pkFtPt, true);
    }

    public void setFilterTranslationShowPortuguese(Boolean show) {
        prefs.edit().putBoolean(pkFtPt, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterTranslationShowSpanish() {
        return prefs.getBoolean(pkFtEs, true);
    }

    public void setFilterTranslationShowSpanish(Boolean show) {
        prefs.edit().putBoolean(pkFtEs, show).apply();
        sendUpdateMessageToListeners();
    }

    public boolean filterTranslationShowEnglish() {
        return prefs.getBoolean(pkFtIn, true);
    }

    public void setFilterTranslationShowEnglish(Boolean show) {
        prefs.edit().putBoolean(pkFtIn, show).apply();
        sendUpdateMessageToListeners();
    }

}
