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

import org.json.JSONException;
import org.json.JSONObject;

import simbio.se.nheengare.utils.SimbiLog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class MainActivity extends AbstractActivity implements TextWatcher {

	// variables
	private AutoCompleteTextView actv;
	private static final String[] COUNTRIES = new String[] { "Azul", "Amarelo",
			"Amado", "Amei", "amando" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SimbiLog.log(this, savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// load database
		try {
			InputStream is = getAssets().open("db.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			JSONObject jsonObject = new JSONObject(new String(buffer));
			SimbiLog.print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (JSONException j) {

		}

		// load AutoCompleteTextView of search
		actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewMain);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		actv.setAdapter(adapter);
		actv.addTextChangedListener(this);
		actv.setThreshold(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		SimbiLog.log(this, menu);
		return false;
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
