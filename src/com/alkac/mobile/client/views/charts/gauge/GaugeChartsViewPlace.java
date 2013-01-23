package com.alkac.mobile.client.views.charts.gauge;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GaugeChartsViewPlace extends AppPlace {

    private String token = "Gauge";

    public GaugeChartsViewPlace() {
    }

    public GaugeChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<GaugeChartsViewPlace> {

        @Override
        public String getToken(GaugeChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public GaugeChartsViewPlace getPlace(String token) {
            return new GaugeChartsViewPlace(token);
        }
    }
}
