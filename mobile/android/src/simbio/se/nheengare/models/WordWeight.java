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
public class WordWeight extends ModelAbstract {

	// serializable
	private static final long serialVersionUID = 1L;

	private int wordId;
	private double weight;

	// Exterializable
	public WordWeight() {
	}

	public WordWeight(JSONObject json) {
		wordId = json.optInt("word");
		weight = json.optDouble("weigth");
	}

	// getters and setters
	public int getWordId() {
		return wordId;
	}

	public double getWeight() {
		return weight;
	}

	// serialize and userialize
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException {
		super.readExternal(input);
		wordId = input.readInt();
		weight = input.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException {
		super.writeExternal(output);
		output.writeInt(wordId);
		output.writeDouble(weight);
	}

}
