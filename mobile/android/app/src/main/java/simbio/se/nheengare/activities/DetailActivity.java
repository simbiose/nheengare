package simbio.se.nheengare.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.core.Flag;
import simbio.se.nheengare.core.Flag.FLAG_SIZE;
import simbio.se.nheengare.core.Options;
import simbio.se.nheengare.models.Language.LANGUAGE;
import simbio.se.nheengare.models.Translation;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.models.WordWeight;
import simbio.se.nheengare.utils.Config;
import simbio.se.nheengare.view.AfiView;
import simbio.se.nheengare.view.ExampleUseView;
import simbio.se.nheengare.view.GrammaticalView;
import simbio.se.nheengare.view.TranslationView;

public class DetailActivity extends AbstractActivity {

    private Word word;

    private TextView tempTxtHeader = null;
    private ImageView tempImgHeader = null;
    private LinearLayout tempTranslation = null;
    private ArrayList<View> tempTranslations = null;
    private LinearLayout tempGrammatical = null;
    private View tempGrammaticals = null;
    private LinearLayout tempExample = null;
    private View tempExamples = null;
    private LinearLayout tempAfi = null;
    private View tempAfis = null;

    @Override
    protected void clearTemp() {
        tempTxtHeader = null;
        tempImgHeader = null;
        tempTranslation = null;
        tempTranslations = null;
        tempGrammatical = null;
        tempGrammaticals = null;
        tempExample = null;
        tempExamples = null;
        tempAfi = null;
        tempAfis = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void loadOnThread() {
        ArrayList<LANGUAGE> langFilter = new ArrayList<>();
        Options options = BlackBoard.getBlackBoard(getApplicationContext()).getOptions();

        if (options.filterTranslationLanguages()) {
            if (!options.filterTranslationShowNheengatu()) {
                langFilter.add(LANGUAGE.NHEENGATU);
            }
            if (!options.filterTranslationShowPortuguese()) {
                langFilter.add(LANGUAGE.PORTUGUESE);
            }
            if (!options.filterTranslationShowSpanish()) {
                langFilter.add(LANGUAGE.SPANISH);
            }
            if (!options.filterTranslationShowEnglish()) {
                langFilter.add(LANGUAGE.ENGLISH);
            }
        }

        int wordId = getIntent().getExtras().getInt("Word");
        if (wordId == Config.WORD_WIDGET_DEFAULT_ID) {
            finish();
        }
        word = getBlackBoard().getWordWithId(wordId);

        tempTxtHeader = findTextViewById(R.id.textViewDetailTitle);
        tempImgHeader = findImageViewById(R.id.imageViewDetailFlag);

        tempTranslation = findLinearLayoutById(R.id.linearLayoutDetailTranslations);
        tempTranslations = new ArrayList<>();
        for (Translation t : word.getTranslations()) {
            if (!langFilter.contains(t.getLanguage())) {
                int flagResourceId = Flag.getFlagResourceId(t.getLanguage(), FLAG_SIZE.FLAG_SIZE_24);
                for (WordWeight ww : t.getWords()) {
                    tempTranslations.add(new TranslationView(getApplicationContext(), flagResourceId, getBlackBoard().getWordWithId(ww.getWordId()).getWriteUnique(), ww).getView());
                }
            }
        }

        tempGrammatical = findLinearLayoutById(R.id.linearLayoutDetailGrammaticalClass);
        if (!word.getGrammaticalsIds().isEmpty()) {
            tempGrammaticals = new GrammaticalView(getApplicationContext(), word.getGrammaticalsIds()).getView();
        }

        tempExample = findLinearLayoutById(R.id.linearLayoutDetailExamplesUse);
        if (!word.getExamples().isEmpty()) {
            tempExamples = new ExampleUseView(getApplicationContext(), word.getExamples(), word.getWrites()).getView();
        }

        tempAfi = findLinearLayoutById(R.id.linearLayoutDetailAfi);
        if (!word.getAfis().isEmpty()) {
            tempAfis = new AfiView(getApplicationContext(), word.getAfis()).getView();
        }
    }

    protected void loadOnUiThread() {
        setTitle(word.getWriteUnique());

        tempTxtHeader.setText(word.getWriteUnique());
        tempImgHeader.setImageResource(Flag.getFlagResourceId(word.getLanguage(), FLAG_SIZE.FLAG_SIZE_32));

        if (word.getTranslations().isEmpty()) {
            tempTranslation.getLayoutParams().height = 0;
        } else {
            for (View v : tempTranslations) {
                tempTranslation.addView(v);
            }
        }

        if (word.getGrammaticalsIds().isEmpty()) {
            tempGrammatical.getLayoutParams().height = 0;
        } else {
            tempGrammatical.addView(tempGrammaticals);
        }

        if (word.getExamples().isEmpty()) {
            tempExample.getLayoutParams().height = 0;
        } else {
            tempExample.addView(tempExamples);
        }

        if (word.getAfis().isEmpty()) {
            tempAfi.getLayoutParams().height = 0;
        } else {
            tempAfi.addView(tempAfis);
        }

        show(new int[]{R.id.textViewDetailTitle, R.id.imageViewDetailFlag, R.id.scrollViewDetailItems});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            backToHome();
            return true;
        }
        if (itemId == R.id.action_share) {
            share(String.format(getString(R.string.action_share_content_detail), word.getWriteUnique()));
            return true;
        }
        if (itemId == R.id.action_report_error) {
            sendEmail(String.format(getString(R.string.action_email_subject_detail), word.getWriteUnique()), String.format(getString(R.string.action_email_content_detail), word.getWriteUnique()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
