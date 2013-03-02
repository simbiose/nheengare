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

import simbio.se.nheengare.R;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
public class Flag {

	public static enum FLAG_SIZE {
		FLAG_SIZE_16, FLAG_SIZE_24, FLAG_SIZE_32, FLAG_SIZE_48
	}

	public static int getFlagResourceId(int languageId, FLAG_SIZE flagSize) {
		switch (languageId) {
		case 1:
			switch (flagSize) {
			case FLAG_SIZE_16:
				return R.drawable.br_16;
			case FLAG_SIZE_24:
				return R.drawable.br_24;
			case FLAG_SIZE_32:
				return R.drawable.br_32;
			case FLAG_SIZE_48:
				return R.drawable.br_48;
			}
		case 2:
			switch (flagSize) {
			case FLAG_SIZE_16:
				return R.drawable.pt_16;
			case FLAG_SIZE_24:
				return R.drawable.pt_24;
			case FLAG_SIZE_32:
				return R.drawable.pt_32;
			case FLAG_SIZE_48:
				return R.drawable.pt_48;
			}
		default:
			switch (flagSize) {
			case FLAG_SIZE_16:
				return R.drawable.nu_16;
			case FLAG_SIZE_24:
				return R.drawable.nu_24;
			case FLAG_SIZE_32:
				return R.drawable.nu_32;
			case FLAG_SIZE_48:
				return R.drawable.nu_48;
			}
		}
		// params error
		return 0;
	}

}
