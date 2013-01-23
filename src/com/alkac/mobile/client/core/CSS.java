/*******************************************************************************
 * CSS.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.core;

/* 
 * Ext GWT 2.2.1 - Ext for GWT 
 * Copyright(c) 2007-2010, Ext JS, LLC. 
 * licensing@extjs.com 
 *  
 * http://extjs.com/license 
 */  
 
/**
 * This class is a utility class from Ext GWT.
 * This is a GPL project so we are reusing it here.
 */
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
  
/** 
 * Provides functions for removing and adding stylesheets. 
 */  
public class CSS {  
  
  /** 
   * Removes a style or link tag by id. 
   *  
   * @param id the id of the tag 
   */  
  public static void removeStyleSheet(String id) {  
    Element elem = DOM.getElementById(id);  
    if (elem != null) {  
      Element p = DOM.getParent(elem);  
      DOM.setElementProperty(p, "disabled", "disabled");  
      DOM.removeChild(p, elem);  
    }  
  }  
  
  /** 
   * Adds a stylesheet to the document. 
   *  
   * @param id the id 
   * @param url the stylesheet url 
   */  
  public static void addStyleSheet(String id, String url) {  
    Element link = DOM.createElement("link");  
    link.setPropertyString("rel", "stylesheet");  
    link.setPropertyString("type", "text/css");  
    link.setPropertyString("id", id);  
    link.setPropertyString("href", url);  
    link.setPropertyString("disabled", "");  
  
    Element elem = getHeadElement();  
  
    Element all = null;  
  
    for (int i = 0; i < elem.getChildNodes().getLength(); i++) {  
      Node node = elem.getChildNodes().getItem(i).cast();  
      if (node instanceof Element) {  
        Element child = (Element) node;  
        String tag = child.getTagName();  
        if (tag != null && child.getTagName().equalsIgnoreCase("link")) {  
          String href = child.getAttribute("href");  
          if (href.length() != 0 && href.indexOf("gxt-all.css") != -1) {  
            all = child;  
            break;  
          }  
        }  
      }  
    }  
  
    if (all != null) {  
      int idx = DOM.getChildIndex(elem, all);  
      DOM.insertChild(elem, link, idx + 1);  
    } else {  
      DOM.appendChild(elem, link);  
    }  
  
  }  
  
  /** 
   * Adds a rules string in a <code>&ltdstyle>&ltd/style></code> element. 
   *  
   * @param style the <code>&ltdstyle>&ltd/style></code> element 
   * @param cssStr the rules string 
   */  
  native public static void setRules(Element style, String cssStr) /*-{ 
    style.setAttribute("type", "text/css"); 
    if(style.styleSheet){// IE 
    style.styleSheet.cssText = cssStr; 
    } else {// w3c 
    while (style.firstChild) { 
    style.removeChild(style.firstChild); 
    } 
    var cssText = $doc.createTextNode(cssStr); 
    style.appendChild(cssText); 
    } 
  }-*/;
  
  public static native Element getHeadElement() /*-{
    return $doc.getElementsByTagName("head")[0];
  }-*/;
  
}  