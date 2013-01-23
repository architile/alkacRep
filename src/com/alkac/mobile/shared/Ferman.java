package com.alkac.mobile.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: Pınar
 * Date: 01.05.2012
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 */
public class Ferman implements IsSerializable {
    Zgort zgort;
    private Location location;

    public Ferman() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setZgort(Zgort zgort) {
        this.zgort = zgort;
    }

    public Zgort getZgort() {
        return zgort;
    }
}
