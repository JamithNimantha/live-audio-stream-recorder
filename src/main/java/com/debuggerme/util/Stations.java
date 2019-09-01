package com.debuggerme.util;

public enum Stations{
    UKHOZIFM,HESOYAM;
    public static Stations getStatus(String station) {
        if (station != null) return Stations.valueOf(station);
        return null;
    }
}
