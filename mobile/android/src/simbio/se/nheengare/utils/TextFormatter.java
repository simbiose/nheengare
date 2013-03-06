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
package simbio.se.nheengare.utils;

import java.util.ArrayList;

import android.text.Html;
import android.widget.TextView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class TextFormatter {

	public static void setTextWithBoldedWords(TextView txt, String phrase,
			ArrayList<String> words) {
		txt.setText(Html.fromHtml(makeTextWithBoldedWords(phrase, words)));
	}

	public static String makeTextWithBoldedWords(String phrase,
			ArrayList<String> words) {
		String formatted = new String();
		for (String string : words)
			formatted = makeTextWithBoldedWord(phrase, string);
		return formatted;
	}

	public static String makeTextWithBoldedWord(String phrase, String word) {
		String spl[] = phrase.split("\\s*(?i)" + word + "\\s*");
		String formatted = new String();
		for (int c = 0; c < spl.length; c++)
			formatted += spl[c]
					+ (c < spl.length - 1 ? "<b> " + word + " </b>" : "");
		return formatted;
	}
}
