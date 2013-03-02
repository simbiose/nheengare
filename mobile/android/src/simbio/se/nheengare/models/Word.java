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
package simbio.se.nheengare.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.SparseIntArray;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Word extends ModelAbstract {

	private int id;
	private int langId;
	private ArrayList<String> writes = new ArrayList<String>();
	private ArrayList<Integer> wordsEqualsIds = new ArrayList<Integer>();
	private ArrayList<Tradutions> tradutions = new ArrayList<Tradutions>();
	private ArrayList<Integer> sourceIds = new ArrayList<Integer>();
	private ArrayList<Integer> grammaticalsIds = new ArrayList<Integer>();
	private SparseIntArray grammaticals = new SparseIntArray();
	private ArrayList<ExamplePhrases> examples = new ArrayList<ExamplePhrases>();

	public Word(JSONObject json) {
		id = json.optInt("id");
		langId = json.optInt("lang");
		JSONArray array = json.optJSONArray("examples");
		for (int c = 0; c < array.length(); c++)
			examples.add(new ExamplePhrases(array.optJSONObject(c)));
		array = json.optJSONArray("grammatical");
		for (int c = 0; c < array.length(); c++) {
			JSONObject o = array.optJSONObject(c);
			int x = o.optInt("class");
			grammaticalsIds.add(x);
			JSONArray a = o.optJSONArray("classification");
			for (int i = 0; i < a.length(); i++)
				grammaticals.put(x, a.optInt(i));
		}
		array = json.optJSONArray("source");
		for (int c = 0; c < array.length(); c++)
			sourceIds.add(array.optInt(c));
		array = json.optJSONArray("tradutions");
		for (int c = 0; c < array.length(); c++)
			tradutions.add(new Tradutions(array.optJSONObject(c)));
		array = json.optJSONArray("equals");
		for (int c = 0; c < array.length(); c++)
			wordsEqualsIds.add(array.optInt(c));
		array = json.optJSONArray("write");
		for (int c = 0; c < array.length(); c++)
			writes.add(array.optString(c));
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public int getLangId() {
		return langId;
	}

	public ArrayList<String> getWrites() {
		return writes;
	}

	public ArrayList<Integer> getWordsEqualsIds() {
		return wordsEqualsIds;
	}

	public ArrayList<Tradutions> getTradutions() {
		return tradutions;
	}

	public ArrayList<Integer> getSourceIds() {
		return sourceIds;
	}

	public ArrayList<Integer> getGrammaticalsIds() {
		return grammaticalsIds;
	}

	public SparseIntArray getGrammaticals() {
		return grammaticals;
	}

	public ArrayList<ExamplePhrases> getExamples() {
		return examples;
	}

}
