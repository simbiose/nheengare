package simbio.se.nheengare.utils;

import android.text.Html;
import android.widget.TextView;

import java.util.ArrayList;

public class TextFormatter {

    public static void setTextWithBoldedWords(TextView txt, String phrase, ArrayList<String> words) {
        txt.setText(Html.fromHtml(makeTextWithBoldedWords(phrase, words)));
    }

    public static String makeTextWithBoldedWords(String phrase, ArrayList<String> words) {
        String formatted = "";
        for (String string : words) {
            formatted = makeTextWithBoldedWord(phrase, string);
        }
        return formatted;
    }

    public static String makeTextWithBoldedWord(String phrase, String word) {
        String[] spl = phrase.split("\\s*(?i)" + word + "\\s*");
        StringBuilder formatted = new StringBuilder();
        for (int c = 0; c < spl.length; c++) {
            formatted.append(spl[c]);
            formatted.append(c < spl.length - 1 ? "<b> " + word + " </b>" : "");
        }
        return formatted.toString();
    }

}
