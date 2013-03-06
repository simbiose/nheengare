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
package simbio.se.nheengare.view;

import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;
import android.content.Context;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class GrammaticalView extends AbstractView {

	public GrammaticalView(Context context, ArrayList<Integer> grammaticalsIds) {
		super(context, R.layout.view_grammatical_class);
		String g = new String();
		for (Integer i : grammaticalsIds) {
			g += BlackBoard.getBlackBoard().getGrammaticalWithId(i).getName()
					+ ", ";
		}
		if (g.length() > 0)
			g = g.substring(0, g.length() - 2);
		findTextViewById(R.id.textViewGrammatical).setText(g);
	}

}
