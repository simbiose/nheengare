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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simbio.se.nheengare.models.Grammatical;
import simbio.se.nheengare.models.Language;
import simbio.se.nheengare.models.Source;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.SimbiLog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class MainActivity extends AbstractActivity implements TextWatcher {

	// variables
	private ArrayList<Source> sources = new ArrayList<Source>();
	private ArrayList<Language> languages = new ArrayList<Language>();
	private ArrayList<Grammatical> grammaticals = new ArrayList<Grammatical>();
	private ArrayList<Word> words = new ArrayList<Word>();
	private ArrayAdapter<String> adapter;

	// views
	private EditText edtInput;
	private ListView listResults;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SimbiLog.log(this, savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// load database
		try {
			// load file
			InputStream is = getAssets().open("db.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			// load json objects
			JSONObject jsonObject = new JSONObject(new String(buffer));
			JSONArray jsonArray = jsonObject.optJSONArray("sources");
			for (int c = 0; c < jsonArray.length(); c++)
				sources.add(new Source(jsonArray.optJSONObject(c)));
			jsonArray = jsonObject.optJSONArray("languages");
			for (int c = 0; c < jsonArray.length(); c++)
				languages.add(new Language(jsonArray.optJSONObject(c)));
			jsonArray = jsonObject.optJSONArray("grammatical_class");
			for (int c = 0; c < jsonArray.length(); c++)
				grammaticals.add(new Grammatical(jsonArray.optJSONObject(c)));
			jsonArray = jsonObject.optJSONArray("words");
			for (int c = 0; c < jsonArray.length(); c++)
				words.add(new Word(jsonArray.optJSONObject(c)));
		} catch (IOException e) {
			SimbiLog.printException(e);
		} catch (JSONException j) {
			SimbiLog.printException(j);
		}

		// load EditView of search
		edtInput = (EditText) findViewById(R.id.autoCompleteTextViewMain);
		edtInput.addTextChangedListener(this);

		// load list to show words
		listResults = (ListView) findViewById(R.id.listViewMain);
		refreshList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		SimbiLog.log(this, menu);
		return false;
	}

	// refresh list

	public void refreshList() {
		ArrayList<String> values = new ArrayList<String>();
		for (Word w : words)
			for (String s : w.getWrites())
				values.add(s);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		listResults.setAdapter(adapter);
	}

	// textWatcher
	@Override
	public void afterTextChanged(Editable s) {
		SimbiLog.log(this, s);
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

}
