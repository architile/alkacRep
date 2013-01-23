package com.alkac.mobile.client.views.charts.pie;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PieChartsViewPlace extends AppPlace {

    private String token = "Pie";

    public PieChartsViewPlace() {
    }

    public PieChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<PieChartsViewPlace> {

        @Override
        public String getToken(PieChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public PieChartsViewPlace getPlace(String token) {
            return new PieChartsViewPlace(token);
        }
    }
}
