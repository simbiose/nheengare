package simbio.se.nheengare.view;

import android.content.Context;

import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.models.ExamplePhrases;
import simbio.se.nheengare.models.Phrase;
import simbio.se.nheengare.utils.TextFormatter;

public class ExampleUseView extends AbstractView {

    public ExampleUseView(Context context, ArrayList<ExamplePhrases> phrases, ArrayList<String> words) {
        super(context, R.layout.view_examples);
        StringBuilder stringBuilder = new StringBuilder();
        for (ExamplePhrases examplePhrases : phrases) {
            for (Phrase p : examplePhrases.getSentences()) {
                stringBuilder.append(p.getPhrase());
                stringBuilder.append("<br>");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        TextFormatter.setTextWithBoldedWords(findTextViewById(R.id.textViewExample), stringBuilder.toString(), words);
    }

}
