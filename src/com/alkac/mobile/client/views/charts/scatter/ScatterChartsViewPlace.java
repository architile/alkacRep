package com.alkac.mobile.client.views.charts.scatter;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ScatterChartsViewPlace extends AppPlace {

    private String token = "Scatter";

    public ScatterChartsViewPlace() {
    }

    public ScatterChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<ScatterChartsViewPlace> {

        @Override
        public String getToken(ScatterChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public ScatterChartsViewPlace getPlace(String token) {
            return new ScatterChartsViewPlace(token);
        }
    }
}
