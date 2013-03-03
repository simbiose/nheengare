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
import simbio.se.nheengare.models.ExamplePhrases;
import simbio.se.nheengare.models.Phrase;
import simbio.se.nheengare.utils.SimbiLog;
import android.content.Context;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class ExampleUseView extends AbstractView {

	public ExampleUseView(Context context, ArrayList<ExamplePhrases> phrases,
			ArrayList<String> words) {
		super(context, R.layout.view_examples);
		SimbiLog.log(this, context, phrases, words);
		String s = new String();
		for (ExamplePhrases examplePhrases : phrases)
			for (Phrase p : examplePhrases.getSentences())
				s += p.getPhrase() + "\n";
		if (s.length() > 0)
			s = s.substring(0, s.length() - 2);
		findTextViewById(R.id.textViewExample).setText(s);
	}

}
