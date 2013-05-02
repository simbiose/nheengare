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
package simbio.se.nheengare.core;

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
import android.content.Context;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class BlackBoard {

	private static BlackBoard blackBoard;

	private ArrayList<Source> sources = new ArrayList<Source>();
	private ArrayList<Language> languages = new ArrayList<Language>();
	private ArrayList<Grammatical> grammaticals = new ArrayList<Grammatical>();
	private ArrayList<Word> words = new ArrayList<Word>();
	private Options options;

	public BlackBoard(Context context) {
		// load options
		options = new Options(context);

		// load database
		try {
			// load file
			InputStream is = context.getAssets().open("db.json");
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
				grammaticals.add(new Grammatical(jsonArray.optJSONObject(c),
						context));
			jsonArray = jsonObject.optJSONArray("words");
			for (int c = 0; c < jsonArray.length(); c++)
				words.add(new Word(jsonArray.optJSONObject(c)));
		} catch (IOException e) {
			SimbiLog.printException(e);
		} catch (JSONException j) {
			SimbiLog.printException(j);
		}
	}

	public static BlackBoard getBlackBoard(Context context) {
		if (blackBoard == null)
			blackBoard = new BlackBoard(context);
		return blackBoard;
	}

	public Options getOptions() {
		return options;
	}

	public ArrayList<Source> getSources() {
		return sources;
	}

	public ArrayList<Language> getLanguages() {
		return languages;
	}

	public ArrayList<Grammatical> getGrammaticals() {
		return grammaticals;
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	// finders
	public <T extends ISearch> T getObjectWithId(ArrayList<T> itens, int id) {
		for (T iSearch : itens)
			if (iSearch.isYourThisId(id))
				return iSearch;
		return null;
	}

	public Word getWordWithId(int id) {
		return (Word) getObjectWithId(words, id);
	}

	public Grammatical getGrammaticalWithId(int id) {
		return (Grammatical) getObjectWithId(grammaticals, id);
	}

}
