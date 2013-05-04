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

import simbio.se.nheengare.R;
import android.content.Context;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Grammatical extends ModelAbstract {

	// serializable
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private ArrayList<GrammaticalClassification> classifications = new ArrayList<GrammaticalClassification>();

	// Exterializable
	public Grammatical() {
	}

	public Grammatical(JSONObject json, Context context) {
		id = json.optInt("id");

		// name
		switch (id) {
		case 1:
			name = context.getString(R.string.grammatical_class_article);
			break;
		case 2:
			name = context.getString(R.string.grammatical_class_adjective);
			break;
		case 3:
			name = context.getString(R.string.grammatical_class_numeral);
			break;
		case 4:
			name = context.getString(R.string.grammatical_class_pronoun);
			break;
		case 5:
			name = context.getString(R.string.grammatical_class_verb);
			break;
		case 6:
			name = context.getString(R.string.grammatical_class_adverb);
			break;
		case 7:
			name = context.getString(R.string.grammatical_class_preposition);
			break;
		case 8:
			name = context.getString(R.string.grammatical_class_conjunction);
			break;
		case 9:
			name = context.getString(R.string.grammatical_class_interjection);
			break;
		case 10:
			name = context.getString(R.string.grammatical_class_noun);
			break;
		default:
			name = json.optString("name");
			break;
		}

		JSONArray array = json.optJSONArray("classification");
		for (int c = 0; c < array.length(); c++)
			classifications.add(new GrammaticalClassification(array
					.optJSONObject(c)));
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<GrammaticalClassification> getClassifications() {
		return classifications;
	}

	// find
	@Override
	public boolean isYourThisId(int id) {
		return id == this.id;
	}

	// serialize and userialize
	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException {
		super.readExternal(input);
		id = input.readInt();
		name = (String) input.readObject();
		classifications = (ArrayList<GrammaticalClassification>) input
				.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException {
		super.writeExternal(output);
		output.writeInt(id);
		output.writeObject(name);
		output.writeObject(classifications);
	}
}
