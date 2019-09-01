package com.debuggerme.util;

import java.util.Arrays;

public class StationsConstant {

    public static String getStationUrl(String station){
        switch (Stations.getStatus(station)){
            case UKHOZIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/c04e80a90111477a88867b697e2203c0/";
        }
        return null;
    }

    public static String getChunkUrl(String station){
        switch (Stations.getStatus(station)){
            case UKHOZIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/c04e80a90111477a88867b697e2203c0/chunklist_w290202276.m3u8";
        }
        return null;
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

}
