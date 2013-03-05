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
package simbio.se.nheengare.activities;

import simbio.se.nheengare.MainActivity;
import simbio.se.nheengare.R;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.core.Flag;
import simbio.se.nheengare.core.Flag.FLAG_SIZE;
import simbio.se.nheengare.models.Tradutions;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.models.WordWeight;
import simbio.se.nheengare.utils.SimbiLog;
import simbio.se.nheengare.view.ExampleUseView;
import simbio.se.nheengare.view.GrammaticalView;
import simbio.se.nheengare.view.TranslationView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.LinearLayout;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
@SuppressLint("NewApi")
public class DetailActivity extends AbstractActivity {

	private Word word;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SimbiLog.log(this, savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// configure screen
		if (android.os.Build.VERSION.SDK_INT >= 11)
			getActionBar().setDisplayHomeAsUpEnabled(true);

		// load word
		word = getBlackBoard().getWordWithId(
				getIntent().getExtras().getInt("Word"));
		setTitle(word.getWriteUnique());

		// setup header view
		findTextViewById(R.id.textViewDetailTitle).setText(
				word.getWriteUnique());
		findImageViewById(R.id.imageViewDetailFlag)
				.setImageResource(
						Flag.getFlagResourceId(word.getLangId(),
								FLAG_SIZE.FLAG_SIZE_32));

		// setup translations view
		LinearLayout llTranslations = findLinearLayoutById(R.id.linearLayoutDeatilTranslations);
		if (word.getTradutions().isEmpty())
			llTranslations.getLayoutParams().height = 0;
		else {
			for (Tradutions t : word.getTradutions()) {
				int flagResourceId = Flag.getFlagResourceId(t.getLanguageId(),
						FLAG_SIZE.FLAG_SIZE_24);
				for (WordWeight ww : t.getWords())
					llTranslations.addView(new TranslationView(
							getApplicationContext(), flagResourceId,
							getBlackBoard().getWordWithId(ww.getWordId())
									.getWriteUnique(), ww).getView());
			}
		}

		// setup grammatical class view
		LinearLayout llGrammatical = findLinearLayoutById(R.id.linearLayoutDeatilGrammaticalClass);
		if (word.getGrammaticalsIds().isEmpty())
			llGrammatical.getLayoutParams().height = 0;
		else
			llGrammatical.addView(new GrammaticalView(getApplicationContext(),
					word.getGrammaticalsIds()).getView());

		// setup examples view
		LinearLayout llExamples = findLinearLayoutById(R.id.linearLayoutDeatilExamplesUse);
		if (word.getExamples().isEmpty())
			llExamples.getLayoutParams().height = 0;
		else
			llExamples.addView(new ExampleUseView(getApplicationContext(), word
					.getExamples(), word.getWrites()).getView());

		// setup AFI TODO
	}

	@Override
	protected void onStart() {
		super.onStart();
		Analytics.getAnalytics(getApplicationContext()).track(
				"/Detail/" + word.getId());
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = new Intent(this, MainActivity.class);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				TaskStackBuilder.from(this).addNextIntent(upIntent)
						.startActivities();
				finish();
			} else {
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
