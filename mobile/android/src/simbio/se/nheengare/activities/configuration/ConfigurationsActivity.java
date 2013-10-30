/**
 * Este arquivo é parte do programa Nheengaré



    Nheengaré é um software livre; você pode redistribui-lo e/ou 

    modifica-lo dentro dos termos da Licença Pública Geral GNU como 

    publicada pela Fundação do Software Livre (FSF); na versão 3 da 

    Licença.



    Este programa é distribuido na esperança que possa ser  util, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer

    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a

    Licença Pública Geral GNU para maiores detalhes.



    Você deve ter recebido uma cópia da Licença Pública Geral GNU

    junto com este programa, se não, escreva para a Fundação do Software

    Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package simbio.se.nheengare.activities.configuration;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;
import android.annotation.SuppressLint;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
@SuppressLint("NewApi")
public class ConfigurationsActivity extends ConfigurationsActivityAbstract
		implements OnCheckedChangeListener {

	// temp
	private ToggleButton tbFilterSearch = null;
	private ToggleButton tbFilterSearchNheengatu = null;
	private ToggleButton tbFilterSearchPortuguese = null;
	private ToggleButton tbFilterSearchSpanish = null;
	private ToggleButton tbFilterSearchEnglish = null;
	private ToggleButton tbFilterTranslation = null;
	private ToggleButton tbFilterTranslationNheengatu = null;
	private ToggleButton tbFilterTranslationPortuguese = null;
	private ToggleButton tbFilterTranslationSpanish = null;
	private ToggleButton tbFilterTranslationEnglish = null;

	@Override
	protected void clearTemp() {
		tbFilterSearch = null;
		tbFilterSearchNheengatu = null;
		tbFilterSearchPortuguese = null;
		tbFilterSearchSpanish = null;
		tbFilterSearchEnglish = null;
		tbFilterTranslation = null;
		tbFilterTranslationNheengatu = null;
		tbFilterTranslationPortuguese = null;
		tbFilterTranslationSpanish = null;
		tbFilterTranslationEnglish = null;
		super.clearTemp();
	}

	@Override
	protected void loadOnThread() {
		tbFilterSearch = findToggleButtonViewById(R.id.toggleConfigSearch);
		tbFilterSearchNheengatu = findToggleButtonViewById(R.id.toggleConfigSearchNheengatu);
		tbFilterSearchPortuguese = findToggleButtonViewById(R.id.toggleConfigSearchPortuguese);
		tbFilterSearchSpanish = findToggleButtonViewById(R.id.toggleConfigSearchSpanish);
		tbFilterSearchEnglish = findToggleButtonViewById(R.id.toggleConfigSearchEnglish);
		tbFilterTranslation = findToggleButtonViewById(R.id.toggleConfigTranslation);
		tbFilterTranslationNheengatu = findToggleButtonViewById(R.id.toggleConfigTranslationNheengatu);
		tbFilterTranslationPortuguese = findToggleButtonViewById(R.id.toggleConfigTranslationPortuguese);
		tbFilterTranslationSpanish = findToggleButtonViewById(R.id.toggleConfigTranslationSpanish);
		tbFilterTranslationEnglish = findToggleButtonViewById(R.id.toggleConfigTranslationEnglish);

		tbFilterSearch.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterSearchLanguages());
		tbFilterSearchNheengatu.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterSearchShowNheengatu());
		tbFilterSearchPortuguese.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterSearchShowPortuguese());
		tbFilterSearchSpanish.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterSearchShowSpanish());
		tbFilterSearchEnglish.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterSearchShowEnglish());

		tbFilterTranslation.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterTranslationLanguages());
		tbFilterTranslationNheengatu.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterTranslationShowNheengatu());
		tbFilterTranslationPortuguese.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterTranslationShowPortuguese());
		tbFilterTranslationSpanish.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterTranslationShowSpanish());
		tbFilterTranslationEnglish.setChecked(BlackBoard
				.getBlackBoard(getApplicationContext()).getOptions()
				.filterTranslationShowEnglish());

		tbFilterSearch.setOnCheckedChangeListener(this);
		tbFilterSearchNheengatu.setOnCheckedChangeListener(this);
		tbFilterSearchPortuguese.setOnCheckedChangeListener(this);
		tbFilterSearchSpanish.setOnCheckedChangeListener(this);
		tbFilterSearchEnglish.setOnCheckedChangeListener(this);
		tbFilterTranslation.setOnCheckedChangeListener(this);
		tbFilterTranslationNheengatu.setOnCheckedChangeListener(this);
		tbFilterTranslationPortuguese.setOnCheckedChangeListener(this);
		tbFilterTranslationSpanish.setOnCheckedChangeListener(this);
		tbFilterTranslationEnglish.setOnCheckedChangeListener(this);
		super.loadOnThread();
	}

	// events handlers
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.toggleConfigSearch:
			changeFilterSearchFilter(isChecked);
			break;
		case R.id.toggleConfigTranslation:
			changeFilterTranslationFilter(isChecked);
			break;
		case R.id.toggleConfigSearchNheengatu:
			changeFilterSearchNheengatu(isChecked);
			break;
		case R.id.toggleConfigSearchPortuguese:
			changeFilterSearchPortuguese(isChecked);
			break;
		case R.id.toggleConfigSearchSpanish:
			changeFilterSearchSpanish(isChecked);
			break;
		case R.id.toggleConfigSearchEnglish:
			changeFilterSearchEnglish(isChecked);
			break;
		case R.id.toggleConfigTranslationNheengatu:
			changeFilterTranslationNheengatu(isChecked);
			break;
		case R.id.toggleConfigTranslationPortuguese:
			changeFilterTranslationPortuguese(isChecked);
			break;
		case R.id.toggleConfigTranslationSpanish:
			changeFilterTranslationSpanish(isChecked);
			break;
		case R.id.toggleConfigTranslationEnglish:
			changeFilterTranslationEnglish(isChecked);
			break;
		default:
			break;
		}
	}
}