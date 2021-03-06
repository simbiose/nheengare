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
package simbio.se.nheengare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simbio.se.nheengare.activities.AboutActivity;
import simbio.se.nheengare.activities.AbstractActivity;
import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.activities.configuration.ConfigurationsActivity;
import simbio.se.nheengare.activities.configuration.ConfigurationsActivityV14;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.core.Options;
import simbio.se.nheengare.models.AbstractModel;
import simbio.se.nheengare.models.Language.LANGUAGE;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.OldDalvikVirtualMachineHelper;
import simbio.se.nheengare.utils.SimbiLog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class MainActivity extends AbstractActivity implements TextWatcher, Runnable, OnItemClickListener {

	// variables
	private ArrayAdapter<String> adapter;
	private ArrayList<String> adapterAux = new ArrayList<String>();
	private ArrayList<Word> words = new ArrayList<Word>();

	// views
	private EditText edtInput;
	private ListView listResults;
	private ProgressBar pbSearch;

	// Threads
	private boolean firstShow = true;
	private boolean searchLock = false;
	private boolean searchAgain = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		onOptionsChanged(null);
	}

	@Override
	protected void loadOnThread() {
		// load EditView of search
		edtInput = findEditTextById(R.id.autoCompleteTextViewMain);
		edtInput.addTextChangedListener(this);

		// load adapter
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>());

		// load list to show words
		listResults = findListViewById(R.id.listViewMain);
		listResults.setOnItemClickListener(this);

		// load search loading
		pbSearch = (ProgressBar) findViewById(R.id.progressBarMainSearchWord);
	}

	@Override
	protected void loadOnUiThread() {
		listResults.setAdapter(adapter);
		refreshList();
		findViewById(R.id.autoCompleteTextViewMain).setVisibility(View.VISIBLE);
	}

	@Override
	protected void trackerPage(Analytics analytics) {
		analytics.track("/Main");
	}

	// refresh list
	public void refreshList() {
		new Thread(this).start();
	}

	// textWatcher
	@Override
	public void afterTextChanged(Editable s) {
		refreshList();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		listResults.setSelectionAfterHeaderView();
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	// multithread
	@SuppressLint({ "DefaultLocale", "NewApi" })
	@Override
	public void run() {
		if (searchLock) {
			setSearchAgain(true);
		} else {
			setSearchLock(true);
			AbstractModel.criteria = edtInput.getText().toString().toLowerCase();

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					pbSearch.setVisibility(View.VISIBLE);
				}
			});

            ArrayList<Word> words = new ArrayList<Word>();
			words.addAll(getBlackBoard().getWords());

			Options options = BlackBoard.getBlackBoard(getApplicationContext()).getOptions();

			if (options.filterSearchLanguages()) {
				ArrayList<LANGUAGE> langFilter = new ArrayList<LANGUAGE>();
				if (!options.filterSearchShowNheengatu())
					langFilter.add(LANGUAGE.LANGUAGE_NHEENGATU);
				if (!options.filterSearchShowPortuguese())
					langFilter.add(LANGUAGE.LANGUAGE_PORTUGUESE);
				if (!options.filterSearchShowSpanish())
					langFilter.add(LANGUAGE.LANGUAGE_SPANISH);
				if (!options.filterSearchShowEnglish())
					langFilter.add(LANGUAGE.LANGUAGE_ENGLISH);
				if (!langFilter.isEmpty()) {
					ArrayList<Word> wordsToRemove = new ArrayList<Word>();
					for (Word w : words)
						if (langFilter.contains(w.getLanguage()))
							wordsToRemove.add(w);
					words.removeAll(wordsToRemove);
				}
			}

			Collections.sort(words, Collections.reverseOrder());
			List<Word> listTemp = words.subList(0, (Math.min(100, words.size())));
			adapterAux.clear();
			for (Word w : listTemp)
				for (String s : w.getWrites())
					adapterAux.add(s);
			listTemp = null;
            this.words.clear();
            this.words.addAll(words);
            words = null;

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					adapter.clear();
					if (android.os.Build.VERSION.SDK_INT >= 11)
						OldDalvikVirtualMachineHelper.arrayAdapterAddAll(adapter, adapterAux);
					else
						for (String s : adapterAux)
							adapter.add(s);
					adapter.notifyDataSetChanged();

					if (firstShow) {
						firstShow = false;
						show(new int[] { R.id.autoCompleteTextViewMain, R.id.listViewMain });
						listResults.setEmptyView(findViewById(R.id.emptyViewMain));
					}

					setSearchLock(false);
					pbSearch.setVisibility(View.INVISIBLE);
					if (searchAgain) {
						setSearchAgain(false);
						new Thread(MainActivity.this).start();
					}
				}
			});
		}
	}

	public synchronized void setSearchAgain(boolean searchAgain) {
		this.searchAgain = searchAgain;
	}

	public synchronized void setSearchLock(boolean searchLock) {
		this.searchLock = searchLock;
	}

	// list click
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(getApplicationContext(), DetailActivity.class);
		i.putExtra("Word", words.get(arg2).getId());
		startActivity(i);
	}

	// menus
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_config:
			analytics.track("/Menu/Main/Config");
			if (android.os.Build.VERSION.SDK_INT >= 14)
				startActivity(new Intent(getApplicationContext(), ConfigurationsActivityV14.class));
			else
				startActivity(new Intent(getApplicationContext(), ConfigurationsActivity.class));
			return true;
		case R.id.action_about:
			analytics.track("/Menu/Main/About");
			startActivity(new Intent(getApplicationContext(), AboutActivity.class));
			return true;
		case R.id.action_speak:
			analytics.track("/Menu/Main/Speak");
			sendEmail(getString(R.string.action_email_subject_main), getString(R.string.action_email_content_main));
			return true;
		case R.id.action_share:
			analytics.track("/Menu/Main/Share");
			share(getString(R.string.action_share_content_main));
			return true;
		default:
			analytics.track("/Menu/Main/Cancel");
			return super.onOptionsItemSelected(item);
		}
	}

	// Options updated
	@Override
	public void onOptionsChanged(Options newOptions) {
		new Thread(this).start();
	}

}
