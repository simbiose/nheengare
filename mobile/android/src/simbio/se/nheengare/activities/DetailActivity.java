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

import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.core.Flag;
import simbio.se.nheengare.core.Flag.FLAG_SIZE;
import simbio.se.nheengare.models.Tradutions;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.models.WordWeight;
import simbio.se.nheengare.utils.ActionBarHelper;
import simbio.se.nheengare.view.AfiView;
import simbio.se.nheengare.view.ExampleUseView;
import simbio.se.nheengare.view.GrammaticalView;
import simbio.se.nheengare.view.TranslationView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
@SuppressLint("NewApi")
public class DetailActivity extends AbstractActivity {

	private Word word;

	// temp
	private TextView tempTxtHeader = null;
	private ImageView tempImgHeader = null;
	private LinearLayout tempTradution = null;
	private ArrayList<View> tempTradutions = null;
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
		tempTradution = null;
		tempTradutions = null;
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

		// configure screen
		if (android.os.Build.VERSION.SDK_INT >= 11)
			ActionBarHelper.setDisplayHomeAsUpEnabled(this, true);
	}

	@Override
	protected void loadOnThread() {
		// load word
		word = getBlackBoard().getWordWithId(
				getIntent().getExtras().getInt("Word"));
		setTitle(word.getWriteUnique());

		// setup header view
		tempTxtHeader = findTextViewById(R.id.textViewDetailTitle);
		tempImgHeader = findImageViewById(R.id.imageViewDetailFlag);

		// setup translations view
		tempTradution = findLinearLayoutById(R.id.linearLayoutDeatilTranslations);
		tempTradutions = new ArrayList<View>();
		for (Tradutions t : word.getTradutions()) {
			int flagResourceId = Flag.getFlagResourceId(t.getLanguage(),
					FLAG_SIZE.FLAG_SIZE_24);
			for (WordWeight ww : t.getWords())
				tempTradutions
						.add(new TranslationView(getApplicationContext(),
								flagResourceId, getBlackBoard().getWordWithId(
										ww.getWordId()).getWriteUnique(), ww)
								.getView());
		}

		// setup grammatical class view
		tempGrammatical = findLinearLayoutById(R.id.linearLayoutDeatilGrammaticalClass);
		if (!word.getGrammaticalsIds().isEmpty())
			tempGrammaticals = new GrammaticalView(getApplicationContext(),
					word.getGrammaticalsIds()).getView();

		// setup examples view
		tempExample = findLinearLayoutById(R.id.linearLayoutDeatilExamplesUse);
		if (!word.getExamples().isEmpty())
			tempExamples = new ExampleUseView(getApplicationContext(),
					word.getExamples(), word.getWrites()).getView();

		// setup AFI
		tempAfi = findLinearLayoutById(R.id.linearLayoutDeatilAfi);
		if (!word.getAfis().isEmpty())
			tempAfis = new AfiView(getApplicationContext(), word.getAfis())
					.getView();
	}

	protected void loadOnUiThread() {
		tempTxtHeader.setText(word.getWriteUnique());
		tempImgHeader.setImageResource(Flag.getFlagResourceId(
				word.getLanguage(), FLAG_SIZE.FLAG_SIZE_32));

		if (word.getTradutions().isEmpty())
			tempTradution.getLayoutParams().height = 0;
		else
			for (View v : tempTradutions)
				tempTradution.addView(v);

		if (word.getGrammaticalsIds().isEmpty())
			tempGrammatical.getLayoutParams().height = 0;
		else
			tempGrammatical.addView(tempGrammaticals);

		if (word.getExamples().isEmpty())
			tempExample.getLayoutParams().height = 0;
		else
			tempExample.addView(tempExamples);

		if (word.getAfis().isEmpty())
			tempAfi.getLayoutParams().height = 0;
		else
			tempAfi.addView(tempAfis);

		show(new int[] { R.id.textViewDetailTitle, R.id.imageViewDetailFlag,
				R.id.scrollViewDetailItens });
	}

	@Override
	protected void trackerPage(Analytics analytics) {
		analytics.track("/Detail/" + word.getId());
	}

	// menus
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			analytics.track("/Menu/Detail/Home");
			backToHome();
			return true;
		case R.id.action_share:
			analytics.track("/Menu/Detail/Share");
			share(String.format(
					getString(R.string.action_share_content_detail),
					word.getWriteUnique()));
			return true;
		case R.id.action_repot_error:
			analytics.track("/Menu/Detail/Report");
			sendEmail(String.format(
					getString(R.string.action_email_subject_detail),
					word.getWriteUnique()), String.format(
					getString(R.string.action_email_content_detail),
					word.getWriteUnique()));
			return true;
		default:
			analytics.track("/Menu/Detail/Cancel");
			return super.onOptionsItemSelected(item);
		}
	}

}
