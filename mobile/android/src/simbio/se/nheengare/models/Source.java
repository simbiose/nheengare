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

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Source extends AbstractModel {

	// serializable
	private static final long serialVersionUID = 1L;

	private int id;
	private ArrayList<String> tutors = new ArrayList<String>();
	private ArrayList<String> urls = new ArrayList<String>();
	private String isbn;

	public Source(JSONObject json) {
		id = json.optInt("id");
		JSONArray array = json.optJSONArray("tutors");
		for (int c = 0; c < array.length(); c++)
			tutors.add(array.optString(c));
		array = json.optJSONArray("urls");
		for (int c = 0; c < array.length(); c++)
			urls.add(array.optString(c));
		isbn = json.optString("ISBN");
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public ArrayList<String> getTutors() {
		return tutors;
	}

	public ArrayList<String> getUrls() {
		return urls;
	}

	public String getIsbn() {
		return isbn;
	}
}
