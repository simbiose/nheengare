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

import simbio.se.nheengare.R;
import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.models.WordWeight;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class TranslationView extends AbstractView {

	private int id;

	public TranslationView(Context context, int flagIdResource, String word,
			WordWeight ww) {
		super(context, R.layout.view_translation);
		// customize values
		findTextViewById(R.id.textViewTranslation).setText(word);
		findImageViewById(R.id.imageViewTranslation).setImageResource(
				flagIdResource);
		findProgressBarById(R.id.progressBarTranslation).setProgress(
				(int) (ww.getWeight() * 100.0));
		id = ww.getWordId();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		Intent i = new Intent(v.getContext(), DetailActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("Word", id);
		v.getContext().startActivity(i);
	}

}
