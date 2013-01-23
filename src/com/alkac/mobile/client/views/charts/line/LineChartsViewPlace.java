package com.alkac.mobile.client.views.charts.line;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LineChartsViewPlace extends AppPlace {

    private String token = "Line";

    public LineChartsViewPlace() {
    }

    public LineChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<LineChartsViewPlace> {

        @Override
        public String getToken(LineChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public LineChartsViewPlace getPlace(String token) {
            return new LineChartsViewPlace(token);
        }
    }
}
