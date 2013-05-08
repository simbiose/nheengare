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
import simbio.se.nheengare.activities.AbstractActivity;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.utils.OldDalvikVirtualMachineHelper;
import simbio.se.nheengare.utils.SimbiLog;
import simbio.se.nheengare.view.animation.ResizeHeigthAnimation;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class ConfigurationsActivityAbstract extends AbstractActivity {

	// temp
	private RelativeLayout rlFilterSearch = null;
	private RelativeLayout rlFilterTranslation = null;
	private int rlfsHeigth;
	private int rlftHeigth;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);

		// configure screen
		if (android.os.Build.VERSION.SDK_INT >= 11)
			OldDalvikVirtualMachineHelper.setDisplayHomeAsUpEnabled(this, true);
	}

	@Override
	protected void loadOnThread() {
		rlFilterSearch = findRelativeLayoutById(R.id.relativeLayputConfigSearch);
		rlFilterTranslation = findRelativeLayoutById(R.id.relativeLayputConfigTranslation);
	}

	@Override
	protected void loadOnUiThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					SimbiLog.printException(e);
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						rlfsHeigth = rlFilterSearch.getHeight();
						rlftHeigth = rlFilterTranslation.getHeight();

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

						show(new int[] { R.id.scrollViewConfig });
					}
				});
			}
		}).start();
	}

	@Override
	protected void trackerPage(Analytics analytics) {
		analytics.track("/Config");
	}

	// Configures master
	protected void changeFilterSearchFilter(Boolean filter) {
		rlFilterSearch.startAnimation(new ResizeHeigthAnimation(rlFilterSearch,
				(filter ? rlfsHeigth : 0)));
		BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
				.setFilterSearchLanguages(filter);
	}

	protected void changeFilterTranslationFilter(Boolean filter) {
		rlFilterTranslation.startAnimation(new ResizeHeigthAnimation(
				rlFilterTranslation, (filter ? rlftHeigth : 10)));
		BlackBoard.getBlackBoard(getApplicationContext()).getOptions()
				.setFilterTranslationLanguages(filter);
	}

	// Configures search
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

	// Configures translations
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

	// menu handler
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			analytics.track("/Menu/Config/Home");
			backToHome();
			return true;
		default:
			analytics.track("/Menu/Config/Cancel");
			return super.onOptionsItemSelected(item);
		}
	}
}