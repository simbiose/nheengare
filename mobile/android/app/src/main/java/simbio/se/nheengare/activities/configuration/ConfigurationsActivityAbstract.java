package simbio.se.nheengare.activities.configuration;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import simbio.se.nheengare.R;
import simbio.se.nheengare.activities.AbstractActivity;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.view.animation.ResizeHeightAnimation;

public class ConfigurationsActivityAbstract extends AbstractActivity {

    private RelativeLayout rlFilterSearch = null;
    private RelativeLayout rlFilterTranslation = null;
    private int rlfsHeight;
    private int rlftHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void loadOnThread() {
        rlFilterSearch = findRelativeLayoutById(R.id.relativeLayoutConfigSearch);
        rlFilterTranslation = findRelativeLayoutById(R.id.relativeLayoutConfigTranslation);
    }

    @Override
    protected void loadOnUiThread() {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {
            }
            runOnUiThread(() -> {
                rlfsHeight = rlFilterSearch.getHeight();
                rlftHeight = rlFilterTranslation.getHeight();

                if (!BlackBoard.getBlackBoard(getApplicationContext())
                        .getOptions().filterSearchLanguages()) {
                    rlFilterSearch.getLayoutParams().height = 0;
                    rlFilterSearch.requestLayout();
                }

                if (!BlackBoard.getBlackBoard(getApplicationContext())
                        .getOptions().filterTranslationLanguages()) {
                    rlFilterTranslation.getLayoutParams().height = 0;
                    rlFilterTranslation.requestLayout();
                }

                show(new int[]{R.id.scrollViewConfig});
            });
        }).start();
    }

    protected void changeFilterSearchFilter(Boolean filter) {
        rlFilterSearch.startAnimation(new ResizeHeightAnimation(rlFilterSearch,
                (filter ? rlfsHeight : 0)));
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterSearchLanguages(filter);
    }

    protected void changeFilterTranslationFilter(Boolean filter) {
        rlFilterTranslation.startAnimation(new ResizeHeightAnimation(
                rlFilterTranslation, (filter ? rlftHeight : 10)));
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterTranslationLanguages(filter);
    }

    protected void changeFilterSearchNheengatu(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterSearchShowNheengatu(filter);
    }

    protected void changeFilterSearchPortuguese(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterSearchShowPortuguese(filter);
    }

    protected void changeFilterSearchSpanish(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterSearchShowSpanish(filter);
    }

    protected void changeFilterSearchEnglish(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterSearchShowEnglish(filter);
    }

    protected void changeFilterTranslationNheengatu(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterTranslationShowNheengatu(filter);
    }

    protected void changeFilterTranslationPortuguese(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterTranslationShowPortuguese(filter);
    }

    protected void changeFilterTranslationSpanish(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterTranslationShowSpanish(filter);
    }

    protected void changeFilterTranslationEnglish(Boolean filter) {
        BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
                .setFilterTranslationShowEnglish(filter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            backToHome();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
