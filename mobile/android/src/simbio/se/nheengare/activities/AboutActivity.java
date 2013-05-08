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
package simbio.se.nheengare.activities;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.utils.OldDalvikVirtualMachineHelper;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class AboutActivity extends AbstractActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		// configure screen
		if (android.os.Build.VERSION.SDK_INT >= 11)
			OldDalvikVirtualMachineHelper.setDisplayHomeAsUpEnabled(this, true);
	}

	@Override
	protected void trackerPage(Analytics analytics) {
		analytics.track("/About");
	}

	// onClicks handler
	public void onClickOpenSimbioseBrowser(View v) {
		analytics.track("/Simbiose");
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(getString(R.string.about_organization_url)));
		startActivity(i);
	}

	// menu handler
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			analytics.track("/Menu/About/Home");
			backToHome();
			return true;
		default:
			analytics.track("/Menu/About/Cancel");
			return super.onOptionsItemSelected(item);
		}
	}

}
