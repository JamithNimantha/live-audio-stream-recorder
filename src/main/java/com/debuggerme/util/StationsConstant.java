package com.debuggerme.util;

import java.util.Arrays;

public class StationsConstant {

//    private static final String url = "http://proradiocloud.antfarm.co.za/ant-lre-sabc/";

    public static String getStationUrl(String station){
        switch (Stations.getStatus(station)){
            case UKHOZIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/c04e80a90111477a88867b697e2203c0/";
            case UMHLOBOWENENEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/7844ef4be8164a66a0e21bdfe374bff5/";
            case TRUFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/f94d47320933430fb3ae414ed0cd355d/";
            case THOBELAFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/58024f759ef343e5b43f99b0c55d84aa/";
            case FIVEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/9f38102608b345dcb53c1db0bd57f2d3/";
            case LOTUSFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/9d1c7019ff894e5191b954eff03d7c77/";
            case LIGWALAGWALAFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/568fc5738cce4434aa6db69e928084be/";
            case LESEDIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/273707ee34a94d87a51c4785b48256a5/";
            case IKWEKWEZIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/ff6c43748de44108a31d127b4b205c12/";
            case GOODHOPEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/1e60ff4028fe4164a3e43071da9fb86f/";
            case MOTSWEDINGFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/97f660b5d3c949e094ca1d8c983551d2/";
            case MUNGHANALONENEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/14e03c487fa44d3686ff65f483373d62/";
            case PHALAPHALAFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/0e6fc9cfa7aa4264ad93f87dc4f75c3b/";
            case RSG100104FM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/b08ea007857740318ea034f0b41504a7/";


        }
        return null;
    }

    public static String getChunkUrl(String station){
        switch (Stations.getStatus(station)){
            case UKHOZIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/c04e80a90111477a88867b697e2203c0/chunklist_w290202276.m3u8";
            case UMHLOBOWENENEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/7844ef4be8164a66a0e21bdfe374bff5/chunklist_w389032444.m3u8";
            case TRUFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/f94d47320933430fb3ae414ed0cd355d/chunklist_w2034877256.m3u8";
            case THOBELAFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/58024f759ef343e5b43f99b0c55d84aa/chunklist_w1906478488.m3u8";
            case FIVEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/9f38102608b345dcb53c1db0bd57f2d3/chunklist_w1111153403.m3u8";
            case LOTUSFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/9d1c7019ff894e5191b954eff03d7c77/chunklist_w1370435815.m3u8";
            case LIGWALAGWALAFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/568fc5738cce4434aa6db69e928084be/chunklist_w1092853689.m3u8";
            case LESEDIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/273707ee34a94d87a51c4785b48256a5/chunklist_w238120024.m3u8";
            case IKWEKWEZIFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/ff6c43748de44108a31d127b4b205c12/chunklist_w109664004.m3u8";
            case GOODHOPEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/1e60ff4028fe4164a3e43071da9fb86f/chunklist_w166725756.m3u8";
            case MOTSWEDINGFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/97f660b5d3c949e094ca1d8c983551d2/chunklist_w259220965.m3u8";
            case MUNGHANALONENEFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/14e03c487fa44d3686ff65f483373d62/chunklist_w897458937.m3u8";
            case PHALAPHALAFM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/0e6fc9cfa7aa4264ad93f87dc4f75c3b/chunklist_w60494421.m3u8";
            case RSG100104FM:
                return "http://proradiocloud.antfarm.co.za/ant-lre-sabc/b08ea007857740318ea034f0b41504a7/chunklist_w834798800.m3u8";
        }
        return null;
    }

    /*
    this method return all the items in a enum class as a string array
     */

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

}
