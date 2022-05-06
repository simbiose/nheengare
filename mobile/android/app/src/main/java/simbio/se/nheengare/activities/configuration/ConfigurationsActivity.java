package simbio.se.nheengare.activities.configuration;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;

public class ConfigurationsActivity extends ConfigurationsActivityAbstract implements OnCheckedChangeListener {

    private Switch swFilterSearch = null;
    private Switch swFilterSearchNheengatu = null;
    private Switch swFilterSearchPortuguese = null;
    private Switch swFilterSearchSpanish = null;
    private Switch swFilterSearchEnglish = null;
    private Switch swFilterTranslation = null;
    private Switch swFilterTranslationNheengatu = null;
    private Switch swFilterTranslationPortuguese = null;
    private Switch swFilterTranslationSpanish = null;
    private Switch swFilterTranslationEnglish = null;

    @Override
    protected void clearTemp() {
        swFilterSearch = null;
        swFilterSearchNheengatu = null;
        swFilterSearchPortuguese = null;
        swFilterSearchSpanish = null;
        swFilterSearchEnglish = null;
        swFilterTranslation = null;
        swFilterTranslationNheengatu = null;
        swFilterTranslationPortuguese = null;
        swFilterTranslationSpanish = null;
        swFilterTranslationEnglish = null;
        super.clearTemp();
    }

    @Override
    protected void loadOnThread() {
        swFilterSearch = findSwitchById(R.id.switchConfigSearch);
        swFilterSearchNheengatu = findSwitchById(R.id.switchConfigSearchNheengatu);
        swFilterSearchPortuguese = findSwitchById(R.id.switchConfigSearchPortuguese);
        swFilterSearchSpanish = findSwitchById(R.id.switchConfigSearchSpanish);
        swFilterSearchEnglish = findSwitchById(R.id.switchConfigSearchEnglish);
        swFilterTranslation = findSwitchById(R.id.switchConfigTranslation);
        swFilterTranslationNheengatu = findSwitchById(R.id.switchConfigTranslationNheengatu);
        swFilterTranslationPortuguese = findSwitchById(R.id.switchConfigTranslationPortuguese);
        swFilterTranslationSpanish = findSwitchById(R.id.switchConfigTranslationSpanish);
        swFilterTranslationEnglish = findSwitchById(R.id.switchConfigTranslationEnglish);

        swFilterSearch.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterSearchLanguages());
        swFilterSearchNheengatu.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterSearchShowNheengatu());
        swFilterSearchPortuguese.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterSearchShowPortuguese());
        swFilterSearchSpanish.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterSearchShowSpanish());
        swFilterSearchEnglish.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterSearchShowEnglish());

        swFilterTranslation.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterTranslationLanguages());
        swFilterTranslationNheengatu.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterTranslationShowNheengatu());
        swFilterTranslationPortuguese.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterTranslationShowPortuguese());
        swFilterTranslationSpanish.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterTranslationShowSpanish());
        swFilterTranslationEnglish.setChecked(BlackBoard
                .getBlackBoard(getApplicationContext()).getOptions()
                .filterTranslationShowEnglish());

        swFilterSearch.setOnCheckedChangeListener(this);
        swFilterSearchNheengatu.setOnCheckedChangeListener(this);
        swFilterSearchPortuguese.setOnCheckedChangeListener(this);
        swFilterSearchSpanish.setOnCheckedChangeListener(this);
        swFilterSearchEnglish.setOnCheckedChangeListener(this);
        swFilterTranslation.setOnCheckedChangeListener(this);
        swFilterTranslationNheengatu.setOnCheckedChangeListener(this);
        swFilterTranslationPortuguese.setOnCheckedChangeListener(this);
        swFilterTranslationSpanish.setOnCheckedChangeListener(this);
        swFilterTranslationEnglish.setOnCheckedChangeListener(this);
        super.loadOnThread();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int viewId = buttonView.getId();
        if (viewId == R.id.switchConfigSearch) {
            changeFilterSearchFilter(isChecked);
        } else if (viewId == R.id.switchConfigTranslation) {
            changeFilterTranslationFilter(isChecked);
        } else if (viewId == R.id.switchConfigSearchNheengatu) {
            changeFilterSearchNheengatu(isChecked);
        } else if (viewId == R.id.switchConfigSearchPortuguese) {
            changeFilterSearchPortuguese(isChecked);
        } else if (viewId == R.id.switchConfigSearchSpanish) {
            changeFilterSearchSpanish(isChecked);
        } else if (viewId == R.id.switchConfigSearchEnglish) {
            changeFilterSearchEnglish(isChecked);
        } else if (viewId == R.id.switchConfigTranslationNheengatu) {
            changeFilterTranslationNheengatu(isChecked);
        } else if (viewId == R.id.switchConfigTranslationPortuguese) {
            changeFilterTranslationPortuguese(isChecked);
        } else if (viewId == R.id.switchConfigTranslationSpanish) {
            changeFilterTranslationSpanish(isChecked);
        } else if (viewId == R.id.switchConfigTranslationEnglish) {
            changeFilterTranslationEnglish(isChecked);
        }
    }

    public Switch findSwitchById(int id) {
        return super.findViewById(id);
    }

}
