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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class AbstractView implements OnClickListener {

	protected View self;

	public AbstractView(Context context, int layoutId) {
		self = ((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				layoutId, null);
		self.setOnClickListener(this);
	}

	public View getView() {
		return self;
	}

	// find standart view
	public EditText findEditTextById(int id) {
		return (EditText) self.findViewById(id);
	}

	public TextView findTextViewById(int id) {
		return (TextView) self.findViewById(id);
	}

	public ListView findListViewById(int id) {
		return (ListView) self.findViewById(id);
	}

	public ImageView findImageViewById(int id) {
		return (ImageView) self.findViewById(id);
	}

	public ProgressBar findProgressBarById(int id) {
		return (ProgressBar) self.findViewById(id);
	}

	public LinearLayout findLinearLayoutById(int id) {
		return (LinearLayout) self.findViewById(id);
	}

	// onclick
	@Override
	public void onClick(View v) {
	}

}
