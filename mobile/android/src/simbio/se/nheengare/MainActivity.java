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

import simbio.se.nheengare.activities.AbstractActivity;
import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.models.ModelAbstract;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.SimbiLog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

	// shared
	public static MainActivity shared = null;

	// variables
	private ArrayAdapter<String> adapter;
	private ArrayList<String> values = new ArrayList<String>();

	// views
	private EditText edtInput;
	private ListView listResults;

	// Threads
	private boolean searchLock = false;
	private boolean searchAgain = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SimbiLog.log(this, savedInstanceState);
		super.onCreate(savedInstanceState);
		shared = this;
		setContentView(R.layout.activity_main);

		// load EditView of search
		edtInput = findEditTextById(R.id.autoCompleteTextViewMain);
		edtInput.addTextChangedListener(this);

		// load list to show words
		listResults = findListViewById(R.id.listViewMain);
		listResults.setOnItemClickListener(this);
		refreshList();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Analytics.getAnalytics(getApplicationContext()).track("/Main");
	}

	// refresh list
	public void refreshList() {
		SimbiLog.log(this);
		if (!searchLock)
			new Thread(this).start();
		else
			setSearchAgain(true);
	}

	// textWatcher
	@Override
	public void afterTextChanged(Editable s) {
		SimbiLog.log(this, s);
		refreshList();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		SimbiLog.log(this, s, start, count, after);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		SimbiLog.log(this, s, start, before, count);
	}

	// multithread
	@Override
	public void run() {
		SimbiLog.log(this);
		setSearchLock(true);
		ModelAbstract.criteria = edtInput.getText().toString();
		Collections
				.sort(getBlackBoard().getWords(), Collections.reverseOrder());
		values.clear();
		for (Word w : getBlackBoard().getWords())
			for (String s : w.getWrites())
				values.add(s);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				SimbiLog.log(this);
				listResults.setAdapter(adapter);
			}
		});
		if (searchAgain) {
			setSearchAgain(false);
			run();
		}
		setSearchLock(false);
	}

	public synchronized void setSearchAgain(boolean searchAgain) {
		SimbiLog.log(this, searchAgain);
		this.searchAgain = searchAgain;
	}

	public synchronized void setSearchLock(boolean searchLock) {
		SimbiLog.log(this, searchLock);
		this.searchLock = searchLock;
	}

	// list click
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(getApplicationContext(), DetailActivity.class);
		i.putExtra("Word", getBlackBoard().getWords().get(arg2).getId());
		startActivity(i);
	}

}
