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
package simbio.se.nheengare.core;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Analytics {

	private static Analytics analytics;

	private Tracker mGaTracker;
	private GoogleAnalytics mGaInstance;

	public Analytics(Context context) {
		mGaInstance = GoogleAnalytics.getInstance(context);
		mGaTracker = mGaInstance.getTracker("UA-38962564-1");
		GAServiceManager.getInstance().setDispatchPeriod(30);
		String deviceId = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		if (deviceId == null)
			deviceId = "anonymous";
		track("/user/" + deviceId);
	}

	public static Analytics getAnalytics(Context context) {
		if (analytics == null)
			analytics = new Analytics(context);
		return analytics;
	}

	public void track(String track) {
		mGaTracker.sendView(track);
		GAServiceManager.getInstance().dispatch();
	}
}
