package com.alkac.mobile.client.views.charts.area;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AreaChartsViewPlace extends AppPlace {

    private String token = "Area";

    public AreaChartsViewPlace() {
    }

    public AreaChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<AreaChartsViewPlace> {

        @Override
        public String getToken(AreaChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public AreaChartsViewPlace getPlace(String token) {
            return new AreaChartsViewPlace(token);
        }
    }
}
