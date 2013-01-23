package com.alkac.mobile.client.views.charts.radar;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RadarChartsViewPlace extends AppPlace {

    private String token = "Radar";

    public RadarChartsViewPlace() {
    }

    public RadarChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<RadarChartsViewPlace> {

        @Override
        public String getToken(RadarChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public RadarChartsViewPlace getPlace(String token) {
            return new RadarChartsViewPlace(token);
        }
    }
}
