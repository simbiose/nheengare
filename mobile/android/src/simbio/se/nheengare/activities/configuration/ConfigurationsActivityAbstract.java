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

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);

		// configure screen
		if (android.os.Build.VERSION.SDK_INT >= 11)
			getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void clearTemp() {
		rlFilterSearch = null;
		rlFilterTranslation = null;
	}

	@Override
	protected void loadOnThread() {
		rlFilterSearch = findRelativeLayoutById(R.id.relativeLayputConfigSearch);
		rlFilterTranslation = findRelativeLayoutById(R.id.relativeLayputConfigTranslation);
	}

	@Override
	protected void loadOnUiThread() {
		if (!BlackBoard.getBlackBoard().getOptions().filterSearchLanguages()) {
			rlFilterSearch.getLayoutParams().height = 0;
		}

		if (!BlackBoard.getBlackBoard().getOptions()
				.filterTranslationLanguages()) {
			rlFilterTranslation.getLayoutParams().height = 0;
		}

		show(new int[] { R.id.scrollViewConfig });
	}

	@Override
	protected void trackerPage(Analytics analytics) {
		analytics.track("/Config");
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