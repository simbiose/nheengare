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
import android.widget.Switch;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
@SuppressLint("NewApi")
public class ConfigurationsActivityV14 extends ConfigurationsActivityAbstract
		implements OnCheckedChangeListener {

	// temp
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

	// events handlers
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.switchConfigSearch:
			changeFilterSearchFilter(isChecked);
			break;
		case R.id.switchConfigTranslation:
			changeFilterTranslationFilter(isChecked);
			break;
		case R.id.switchConfigSearchNheengatu:
			changeFilterSearchNheengatu(isChecked);
			break;
		case R.id.switchConfigSearchPortuguese:
			changeFilterSearchPortuguese(isChecked);
			break;
		case R.id.switchConfigSearchSpanish:
			changeFilterSearchSpanish(isChecked);
			break;
		case R.id.switchConfigSearchEnglish:
			changeFilterSearchEnglish(isChecked);
			break;
		case R.id.switchConfigTranslationNheengatu:
			changeFilterTranslationNheengatu(isChecked);
			break;
		case R.id.switchConfigTranslationPortuguese:
			changeFilterTranslationPortuguese(isChecked);
			break;
		case R.id.switchConfigTranslationSpanish:
			changeFilterTranslationSpanish(isChecked);
			break;
		case R.id.switchConfigTranslationEnglish:
			changeFilterTranslationEnglish(isChecked);
			break;
		default:
			break;
		}
	}

	// this cannot be in abstract because need a api 14
	public Switch findSwitchById(int id) {
		return (Switch) super.findViewById(id);
	}
}