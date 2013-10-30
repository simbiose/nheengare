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
public class AbstractModel implements Comparable<AbstractModel>, Serializable, ISearch {

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
	public int compareTo(AbstractModel another) {
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
		if (criteria.length() == 0)
			criteriaWeight = CRITERIA_MAX;
		else
			criteriaWeight = CRITERIA_MAX - computeLevenshteinDistance(criteria, compareToCriteria);
	}

	private static int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	/**
	 * Levenshtein Distance
	 * http://en.wikibooks.org/wiki/Algorithm_Implementation
	 * /Strings/Levenshtein_distance#Java
	 */
	public static int computeLevenshteinDistance(CharSequence str1, CharSequence str2) {
		int[][] distance = new int[str1.length() + 1][str2.length() + 1];
		for (int i = 0; i <= str1.length(); i++)
			distance[i][0] = i;
		for (int j = 1; j <= str2.length(); j++)
			distance[0][j] = j;
		for (int i = 1; i <= str1.length(); i++)
			for (int j = 1; j <= str2.length(); j++)
				distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1, distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
		return distance[str1.length()][str2.length()];
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
