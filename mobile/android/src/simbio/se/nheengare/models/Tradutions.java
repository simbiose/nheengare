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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import simbio.se.nheengare.models.Language.LANGUAGE;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Tradutions extends ModelAbstract {

	// serializable
	private static final long serialVersionUID = 1L;

	private LANGUAGE language;
	private ArrayList<WordWeight> words = new ArrayList<WordWeight>();

	// Exterializable
	public Tradutions() {
	}

	public Tradutions(JSONObject json) {
		language = Language.chooseLanguageUsingId(json.optInt("lang"));
		JSONArray array = json.optJSONArray("translate");
		for (int c = 0; c < array.length(); c++)
			words.add(new WordWeight(array.optJSONObject(c)));
	}

	// getters and setters
	public LANGUAGE getLanguage() {
		return language;
	}

	public ArrayList<WordWeight> getWords() {
		return words;
	}

	// serialize and userialize
	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException {
		super.readExternal(input);
		language = Language.chooseLanguageUsingId(input.readInt());
		words = (ArrayList<WordWeight>) input.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException {
		super.writeExternal(output);
		output.writeInt(Language.chooseLanguageId(language));
		output.writeObject(words);
	}
}
