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

import simbio.se.nheengare.activities.AboutActivity;
import simbio.se.nheengare.activities.AbstractActivity;
import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.models.ModelAbstract;
import simbio.se.nheengare.models.Word;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class MainActivity extends AbstractActivity implements TextWatcher,
		Runnable, OnItemClickListener {

	// variables
	private ArrayAdapter<String> adapter;

	// views
	private EditText edtInput;
	private ListView listResults;

	// Threads
	private boolean searchLock = false;
	private boolean searchAgain = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void loadOnThread() {
		// load EditView of search
		edtInput = findEditTextById(R.id.autoCompleteTextViewMain);
		edtInput.addTextChangedListener(this);

		// load adapter
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				new ArrayList<String>());

		// load list to show words
		listResults = findListViewById(R.id.listViewMain);
		listResults.setOnItemClickListener(this);
	}

	@Override
	protected void loadOnUiThread() {
		listResults.setAdapter(adapter);
		refreshList();
		show(new int[] { R.id.autoCompleteTextViewMain, R.id.listViewMain });
	}

	@Override
	protected void trackerPage(Analytics analytics) {
		analytics.track("/Main");
	}

	// refresh list
	public void refreshList() {
		if (!searchLock)
			new Thread(this).start();
		else
			setSearchAgain(true);
	}

	// textWatcher
	@Override
	public void afterTextChanged(Editable s) {
		refreshList();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		listResults.setSelectionAfterHeaderView();
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	// multithread
	@SuppressLint("DefaultLocale")
	@Override
	public void run() {
		setSearchLock(true);
		ModelAbstract.criteria = edtInput.getText().toString().toLowerCase();
		Collections
				.sort(getBlackBoard().getWords(), Collections.reverseOrder());
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				adapter.clear();
				for (Word w : getBlackBoard().getWords())
					for (String s : w.getWrites())
						adapter.add(s);
				adapter.notifyDataSetChanged();
			}
		});
		if (searchAgain) {
			setSearchAgain(false);
			run();
		}
		setSearchLock(false);
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
		i.putExtra("Word", getBlackBoard().getWords().get(arg2).getId());
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
		case R.id.action_about:
			analytics.track("/Menu/Main/About");
			startActivity(new Intent(getApplicationContext(),
					AboutActivity.class));
			return true;
		case R.id.action_speak:
			analytics.track("/Menu/Main/Speak");
			sendEmail(getString(R.string.action_email_subject_main),
					getString(R.string.action_email_content_main));
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

}
