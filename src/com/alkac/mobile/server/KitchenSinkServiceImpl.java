/*******************************************************************************
 * KitchenSinkServiceImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
 * 
 * Copyright (c) 2012 Emitrom LLC. All rights reserved.
 *
 * The Kitchen Sink is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Kitchen Sink is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Kitchen Sink.  If not, see http://www.emitrom.com/gpl_license
 *  
 * For licensing questions, please contact us at licensing@emitrom.com
 *
 ******************************************************************************/
package com.alkac.mobile.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import com.alkac.mobile.client.KitchenSinkService;
import com.alkac.mobile.client.views.tutoni.UserInfo;
import com.alkac.mobile.shared.Ferman;
import com.alkac.mobile.shared.Location;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class KitchenSinkServiceImpl extends RemoteServiceServlet implements KitchenSinkService {

	private final static String SVN_BASE_PATH = "http://svn.emitrom.com/gwt4touch2ks/trunk/kitchensink/";
	
	@Override
	public String getSource(String url) throws IllegalArgumentException {
		return sendGetRequest(SVN_BASE_PATH + "src/" + url);
	}

	@Override
	public String getVersion() {
	    String html = "<small>Touch4j Kitchen Sink ";
	    
	    try {
    	    String version = sendGetRequest(SVN_BASE_PATH + "build.properties");
    	    Properties props = new Properties();
    	    props.load(new StringReader(version));
    	    
    	    html += props.getProperty("version", "N/A");
    	    
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    
	    html += "<br/>Copyright (c) 2012 Emitrom LLC";
	    html += "<br/>Kitchen Sink is licensed under GPLv3";
	    html += "</small>";
	    
	    return html;
	}

    @Override
    public Ferman getFerman(UserInfo userInfo, Location location) {
        return Tasiyici.getFerman(userInfo,location);
    }

    @Override
    public void setAyar(String kapishMesafe, Location location) {
        Tasiyici.setKapishMesafesi(Integer.parseInt(kapishMesafe));
        Tasiyici.setTasiyiciLocation(location);
    }


    public void initGame() {
        Tasiyici.init();
    }

    private String sendGetRequest(String urlStr) {

		String result = null;
		try {

			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			
			bufferedReader.close();
			result = stringBuffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}