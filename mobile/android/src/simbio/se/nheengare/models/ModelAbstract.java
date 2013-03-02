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

import java.io.Serializable;
import java.util.ArrayList;

import simbio.se.nheengare.core.ISearch;

import com.google.gson.Gson;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class ModelAbstract implements Comparable<ModelAbstract>, Serializable,
		ISearch {

	// serializable
	private static final long serialVersionUID = 1L;

	// constants
	public static final int CRITERIA_MAX = 100;
	public static final int CRITERIA_MIN = 0;

	// string to criteria the sort
	public static String criteria;

	// criteria range of instance
	protected int criteriaWeight = CRITERIA_MAX;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public int compareTo(ModelAbstract another) {
		return criteriaWeight - another.criteriaWeight;
	}

	public void setCriteriaWeight(ArrayList<String> compareBestToCriteria) {
		int last = CRITERIA_MIN;
		for (String string : compareBestToCriteria) {
			setCriteriaWeight(string);
			if (criteriaWeight < last)
				criteriaWeight = last;
			else
				last = criteriaWeight;
		}
	}

	public void setCriteriaWeight(String compareToCriteria) {
		if (compareToCriteria.contains(criteria)) {
			criteriaWeight = CRITERIA_MAX;
		} else {
			criteriaWeight = CRITERIA_MIN;
		}
	}

	public float getCriteriaWeight() {
		return criteriaWeight;
	}

	// search
	@Override
	public boolean isYourThisId(int id) {
		return false;
	}

}
