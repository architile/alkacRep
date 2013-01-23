package com.alkac.mobile.server;

import com.alkac.mobile.client.views.tutoni.UserInfo;
import com.alkac.mobile.shared.Ferman;
import com.alkac.mobile.shared.Location;
import com.alkac.mobile.shared.Zgort;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Pınar
 * Date: 01.05.2012
 * Time: 17:34
 * To change this template use File | Settings | File Templates.
 */
public class Tasiyici {
    static Location tasiyiciLocation = new Location(39.8799, 32.8295);
    static UserInfo tasiyiciUserInfo = UserInfo.Zero;
    static int kapishMesafesi = 300;
    static boolean locked = false;
    static Timer the_king;
    static ArrayList<UserInfo> sonTasiyiciList;                                 // döngülü kuyruk
    static int sonTasiyiciListIndex;                                            // döngülü kuyruk index
    static int MAXLIST = 5;                                                     // döngülü kuyruk eleman sayısı
    static Queue<AbstractMap.SimpleEntry<UserInfo, Location>> kuyruk;             // son 2 sn.deki gelen istekler

    public static void init() {
        sonTasiyiciList = new ArrayList<UserInfo>(MAXLIST);
        for (int i=0;i<MAXLIST; i++) {
            sonTasiyiciList.add(null);
        }
        sonTasiyiciListIndex = 0;
        the_king = new Timer();
        kuyruk = new LinkedList<AbstractMap.SimpleEntry<UserInfo, Location>>();

        the_king.schedule(new TimerTask() {
            @Override
            public void run() {
                if (locked) return;
                int lastAccessIndex = MAXLIST;      // döngülü kuyruğa göreceli index
                AbstractMap.SimpleEntry<UserInfo, Location> lastAccessor = null;
                while (!kuyruk.isEmpty()) {
                    AbstractMap.SimpleEntry<UserInfo, Location> entry = kuyruk.remove();
                    int index = sonTasiyiciList.indexOf(entry.getKey());
                    if (index == -1) {
                        lastAccessIndex = -1;
                        lastAccessor = entry;
                    } else if ((sonTasiyiciListIndex + MAXLIST - index) % MAXLIST < lastAccessIndex) {
                        lastAccessIndex = (sonTasiyiciListIndex + MAXLIST - index) % MAXLIST;
                        lastAccessor = entry;
                    }
                }
                if (lastAccessor!=null)
                {
                    locked = true;
                    tasiyiciUserInfo = lastAccessor.getKey();
                    tasiyiciLocation = lastAccessor.getValue();
                    sonTasiyiciList.set(sonTasiyiciListIndex ,tasiyiciUserInfo);
                    sonTasiyiciListIndex  = sonTasiyiciListIndex  == MAXLIST-1 ? 0 : sonTasiyiciListIndex +1;

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            locked = false;
                        }
                    }, 10000);

                }

            }
        }, 0, 2000);
    }

    public static Ferman getFerman(UserInfo userInfo, Location location) {
        Ferman ferman = new Ferman();
        if (userInfo == tasiyiciUserInfo) {
            tasiyiciLocation = location;
            ferman.setLocation(location);
            ferman.setZgort(Zgort.TasimayaDevam);
            return ferman;
        }
        // aşağıdaki kod thread safe olmalı.
        if (isNearAndAvailable(location))
            kuyruk.add(new AbstractMap.SimpleEntry<UserInfo, Location>(userInfo,location));

        ferman.setLocation(tasiyiciLocation);
        ferman.setZgort(Zgort.Normal);
        return ferman;

    }

    private static boolean isNearAndAvailable(Location location) {
        if (locked) return false;
        float uzaklik = distFromAsMeters(location.getLatitude(), location.getLongitude(), tasiyiciLocation.getLatitude(), tasiyiciLocation.getLongitude());
        return uzaklik < kapishMesafesi;  //To change body of created methods use File | Settings | File Templates.
    }

    public static float distFromAsMeters(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return new Float(dist * meterConversion).floatValue();
    }

    public static void setTasiyiciLocation(Location tasiyiciLocation) {
        Tasiyici.tasiyiciLocation = tasiyiciLocation;
    }

    public static void setKapishMesafesi(int kapishMesafesi) {
        Tasiyici.kapishMesafesi = kapishMesafesi;
        locked = false;
    }
}
