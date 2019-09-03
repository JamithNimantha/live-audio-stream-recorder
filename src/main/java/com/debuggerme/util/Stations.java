package com.debuggerme.util;

public enum Stations{
    UKHOZIFM,UMHLOBOWENENEFM,TRUFM,THOBELAFM,FIVEFM,LOTUSFM,LIGWALAGWALAFM,LESEDIFM,IKWEKWEZIFM,GOODHOPEFM,MOTSWEDINGFM,MUNGHANALONENEFM,PHALAPHALAFM,RSG100104FM;
    public static Stations getStatus(String station) {
        if (station != null) return Stations.valueOf(station);
        return null;
    }
}
