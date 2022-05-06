package simbio.se.nheengare.view;

import android.content.Context;

import java.util.ArrayList;

import simbio.se.nheengare.R;

public class AfiView extends AbstractView {

    public AfiView(Context context, ArrayList<String> afis) {
        super(context, R.layout.view_afi);
        StringBuilder stringBuilder = new StringBuilder();
        for (String afi : afis) {
            stringBuilder.append(afi);
            stringBuilder.append("\n");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        findTextViewById(R.id.textViewAfi).setText(stringBuilder.toString());
    }

}
