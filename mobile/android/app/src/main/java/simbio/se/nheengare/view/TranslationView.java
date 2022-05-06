package simbio.se.nheengare.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import simbio.se.nheengare.R;
import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.models.WordWeight;

public class TranslationView extends AbstractView {

    private final int id;

    public TranslationView(Context context, int flagIdResource, String word, WordWeight ww) {
        super(context, R.layout.view_translation);
        findTextViewById(R.id.textViewTranslation).setText(word);
        findImageViewById(R.id.imageViewTranslation).setImageResource(flagIdResource);
        findProgressBarById(R.id.progressBarTranslation).setProgress((int) (ww.getWeight() * 100.0));
        id = ww.getWordId();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Word", id);
        view.getContext().startActivity(intent);
    }

}
