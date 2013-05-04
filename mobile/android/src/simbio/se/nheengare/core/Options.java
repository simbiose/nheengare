package simbio.se.nheengare.core;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Options {

	private SharedPreferences prefs;
	private static ArrayList<IOptionsChangedListener> optionsChangedListeners = new ArrayList<IOptionsChangedListener>();

	// keys
	private final String preferencesKey = "simbio.se.nheengare";
	private final String pkFilterSearch = "pkFilterSearch";
	private final String pkFilterTranslation = "pkFilterTranslation";
	private final String pkFsNh = "pkFsNh";
	private final String pkFsPt = "pkFsPt";
	private final String pkFsEs = "pkFsEs";
	private final String pkFsIn = "pkFsIn";
	private final String pkFtNh = "pkFtNh";
	private final String pkFtPt = "pkFtPt";
	private final String pkFtEs = "pkFtEs";
	private final String pkFtIn = "pkFtIn";

	public Options(Context context) {
		prefs = context.getSharedPreferences(preferencesKey,
				Context.MODE_PRIVATE);
	}

	// Listener
	public static void addOptionsChangeListener(IOptionsChangedListener listener) {
		optionsChangedListeners.add(listener);
	}

	public static void removeOptionsChangeListener(
			IOptionsChangedListener listener) {
		optionsChangedListeners.remove(listener);
	}

	private void sendUpdateMessageToListeners() {
		for (IOptionsChangedListener listener : optionsChangedListeners)
			listener.onOptionsChanged(this);
	}

	// search filter
	public boolean filterSearchLanguages() {
		return prefs.getBoolean(pkFilterSearch, false);
	}

	public void setFilterSearchLanguages(Boolean filter) {
		prefs.edit().putBoolean(pkFilterSearch, filter).commit();
		sendUpdateMessageToListeners();
	}

	// translaion on detail filter
	public boolean filterTranslationLanguages() {
		return prefs.getBoolean(pkFilterTranslation, false);
	}

	public void setFilterTranslationLanguages(Boolean filter) {
		prefs.edit().putBoolean(pkFilterTranslation, filter).commit();
		sendUpdateMessageToListeners();
	}

	// search languages filters
	// nheengatu
	public boolean filterSearchShowNheengatu() {
		return prefs.getBoolean(pkFsNh, true);
	}

	public void setFilterSearchShowNheengatu(Boolean show) {
		prefs.edit().putBoolean(pkFsNh, show).commit();
		sendUpdateMessageToListeners();
	}

	// portuguese
	public boolean filterSearchShowPortuguese() {
		return prefs.getBoolean(pkFsPt, true);
	}

	public void setFilterSearchShowPortuguese(Boolean show) {
		prefs.edit().putBoolean(pkFsPt, show).commit();
		sendUpdateMessageToListeners();
	}

	// Spanish
	public boolean filterSearchShowSpanish() {
		return prefs.getBoolean(pkFsEs, true);
	}

	public void setFilterSearchShowSpanish(Boolean show) {
		prefs.edit().putBoolean(pkFsEs, show).commit();
		sendUpdateMessageToListeners();
	}

	// English
	public boolean filterSearchShowEnglish() {
		return prefs.getBoolean(pkFsIn, true);
	}

	public void setFilterSearchShowEnglish(Boolean show) {
		prefs.edit().putBoolean(pkFsIn, show).commit();
		sendUpdateMessageToListeners();
	}

	// translation on detail languages filters
	// nheengatu
	public boolean filterTranslationShowNheengatu() {
		return prefs.getBoolean(pkFtNh, true);
	}

	public void setFilterTranslationShowNheengatu(Boolean show) {
		prefs.edit().putBoolean(pkFtNh, show).commit();
		sendUpdateMessageToListeners();
	}

	// portuguese
	public boolean filterTranslationShowPortuguese() {
		return prefs.getBoolean(pkFtPt, true);
	}

	public void setFilterTranslationShowPortuguese(Boolean show) {
		prefs.edit().putBoolean(pkFtPt, show).commit();
		sendUpdateMessageToListeners();
	}

	// Spanish
	public boolean filterTranslationShowSpanish() {
		return prefs.getBoolean(pkFtEs, true);
	}

	public void setFilterTranslationShowSpanish(Boolean show) {
		prefs.edit().putBoolean(pkFtEs, show).commit();
		sendUpdateMessageToListeners();
	}

	// English
	public boolean filterTranslationShowEnglish() {
		return prefs.getBoolean(pkFtIn, true);
	}

	public void setFilterTranslationShowEnglish(Boolean show) {
		prefs.edit().putBoolean(pkFtIn, show).commit();
		sendUpdateMessageToListeners();
	}
}
