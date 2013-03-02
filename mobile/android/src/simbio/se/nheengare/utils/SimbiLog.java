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

import android.util.Log;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class SimbiLog {

	public static void here() {
		print("Aqui");
	}

	public static void print(String msg) {
		if (Config.LOG) {
			Log.d(Config.LOG_TOKEN, msg);
		}
	}

	public static void print(Object... params) {
		if (Config.LOG) {
			String s = "";
			for (Object string : params) {
				s += " .:. " + (string == null ? "Null" : string.toString());
			}
			s = s.substring(5);
			print(s);
		}
	}

	public static void log(Object instance, Object... params) {
		if (Config.LOG_STACK_TRACE) {
			String s = "";
			int i = 1;
			for (Object string : params) {
				s += i + ":" + (string == null ? "Null" : string.toString())
						+ " ";
				i++;
			}
			Log.d(Config.LOG_TOKEN_STACK_TRACE, "Method: "
					+ Thread.currentThread().getStackTrace()[4] + " instance: "
					+ (instance == null ? "Null" : instance) + " params: " + s);
		}
	}

	public static void printException(Exception e) {
		if (Config.LOG) {
			Log.e(Config.LOG_TOKEN, e.toString());
		}
	}

}
