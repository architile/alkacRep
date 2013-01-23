package com.alkac.mobile.client.views.charts.bar;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class BarChartsViewPlace extends AppPlace {

    private String token = "Bar";

    public BarChartsViewPlace() {
    }

    public BarChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<BarChartsViewPlace> {

        @Override
        public String getToken(BarChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public BarChartsViewPlace getPlace(String token) {
            return new BarChartsViewPlace(token);
        }
    }
}
