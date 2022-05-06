package simbio.se.nheengare.core;

import simbio.se.nheengare.R;
import simbio.se.nheengare.models.Language.LANGUAGE;

public class Flag {

    public static int getFlagResourceId(LANGUAGE language, FLAG_SIZE flagSize) {
        switch (language) {
            case NHEENGATU:
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
            case PORTUGUESE:
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
            case SPANISH:
                switch (flagSize) {
                    case FLAG_SIZE_16:
                        return R.drawable.es_16;
                    case FLAG_SIZE_24:
                        return R.drawable.es_24;
                    case FLAG_SIZE_32:
                        return R.drawable.es_32;
                    case FLAG_SIZE_48:
                        return R.drawable.es_48;
                }
            case ENGLISH:
                switch (flagSize) {
                    case FLAG_SIZE_16:
                        return R.drawable.en_16;
                    case FLAG_SIZE_24:
                        return R.drawable.en_24;
                    case FLAG_SIZE_32:
                        return R.drawable.en_32;
                    case FLAG_SIZE_48:
                        return R.drawable.en_48;
                }
            case GUARANI:
                switch (flagSize) {
                    case FLAG_SIZE_16:
                        return R.drawable.gr_16;
                    case FLAG_SIZE_24:
                        return R.drawable.gr_24;
                    case FLAG_SIZE_32:
                        return R.drawable.gr_32;
                    case FLAG_SIZE_48:
                        return R.drawable.gr_48;
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
        return 0;
    }

    public enum FLAG_SIZE {
        FLAG_SIZE_16, FLAG_SIZE_24, FLAG_SIZE_32, FLAG_SIZE_48
    }

}
