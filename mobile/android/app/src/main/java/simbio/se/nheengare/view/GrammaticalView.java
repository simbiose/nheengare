package simbio.se.nheengare.view;

import android.content.Context;

import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;

public class GrammaticalView extends AbstractView {

    public GrammaticalView(Context context, ArrayList<Integer> grammaticalsIds) {
        super(context, R.layout.view_grammatical_class);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : grammaticalsIds) {
            stringBuilder.append(BlackBoard.getBlackBoard(context).getGrammaticalWithId(i).getName());
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        findTextViewById(R.id.textViewGrammatical).setText(stringBuilder);
    }

}
