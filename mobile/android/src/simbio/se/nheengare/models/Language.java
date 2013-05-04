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

import org.json.JSONObject;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Language extends ModelAbstract {

	// serializable
	private static final long serialVersionUID = 1L;

	public enum LANGUAGE {
		LANGUAGE_UNKNOW, LANGUAGE_NHEENGATU, LANGUAGE_PORTUGUESE, LANGUAGE_SPANISH, LANGUAGE_ENGLISH, LANGUAGE_GUARANI, LANGUAGE_TUPI
	}

	public static LANGUAGE chooseLanguageUsingId(int id) {
		switch (id) {
		case 1:
			return LANGUAGE.LANGUAGE_NHEENGATU;
		case 2:
			return LANGUAGE.LANGUAGE_PORTUGUESE;
		case 3:
			return LANGUAGE.LANGUAGE_SPANISH;
		case 4:
			return LANGUAGE.LANGUAGE_ENGLISH;
		case 5:
			return LANGUAGE.LANGUAGE_GUARANI;
		case 6:
			return LANGUAGE.LANGUAGE_TUPI;
		default:
			return LANGUAGE.LANGUAGE_UNKNOW;
		}
	}

	public static int chooseLanguageId(LANGUAGE l) {
		switch (l) {
		case LANGUAGE_NHEENGATU:
			return 1;
		case LANGUAGE_PORTUGUESE:
			return 2;
		case LANGUAGE_SPANISH:
			return 3;
		case LANGUAGE_ENGLISH:
			return 4;
		case LANGUAGE_GUARANI:
			return 5;
		case LANGUAGE_TUPI:
			return 6;
		default:
			return 0;
		}
	}

	private LANGUAGE id;
	private String name;
	private String iso;

	// Exterializable
	public Language() {
	}

	public Language(JSONObject json) {
		name = json.optString("name");
		iso = json.optString("iso");
		id = chooseLanguageUsingId(json.optInt("id"));
	}

	// getters and setters
	public LANGUAGE getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIso() {
		return iso;
	}

	// serialize and userialize
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException {
		super.readExternal(input);
		id = Language.chooseLanguageUsingId(input.readInt());
		name = (String) input.readObject();
		iso = (String) input.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException {
		super.writeExternal(output);
		output.writeInt(Language.chooseLanguageId(id));
		output.writeObject(name);
		output.writeObject(iso);
	}
}
