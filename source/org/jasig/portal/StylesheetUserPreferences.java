/**
 * Copyright � 2001 The JA-SIG Collaborative.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Redistributions of any form whatsoever must retain the following
 *    acknowledgment:
 *    "This product includes software developed by the JA-SIG Collaborative
 *    (http://www.jasig.org/)."
 *
 * THIS SOFTWARE IS PROVIDED BY THE JA-SIG COLLABORATIVE "AS IS" AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE JA-SIG COLLABORATIVE OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

 package org.jasig.portal;

import java.util.*;


/**
 * Description of user preferences that are common to all of the core stylesheets
 * @author Peter Kharchenko
 * @version $Revision$
 */

public class StylesheetUserPreferences {
    String name;
    protected Hashtable parameters;

    public StylesheetUserPreferences() {
        parameters=new Hashtable();
    }

    public StylesheetUserPreferences(StylesheetUserPreferences sup) {
        this.name=sup.name;
        this.parameters=new Hashtable(sup.parameters);
    }

    public String getStylesheetName() { return name; }
    public void setStylesheetName(String n) { name=n; }

    public String getParameterValue(String parameterName) {
        return (String) parameters.get(parameterName);
    }

    public void putParameterValue(String parameterName,String parameterValue) {
        this.parameters.put(parameterName,parameterValue);
    }


    public void deleteParameter(String parameterName) {
        this.parameters.remove(parameterName);
    }

    public Hashtable getParameterValues() {
        return parameters;
    }

    public void setParameterValues(Hashtable parameterTable) {
        this.parameters=parameterTable;
    }


    public void synchronizeWithDescription(CoreStylesheetDescription sd) {
        // make sure only the existing parameters are included
        // check if all of the parameters in the preferences occur in the description
        for (Enumeration e = parameters.keys(); e.hasMoreElements();) {
            String pname=(String) e.nextElement();
            if(!sd.containsParameterName(pname))
                parameters.remove(pname);
        }
    }

    public void completeWithDescriptionInformation(CoreStylesheetDescription sd) {
        // check if all of the parameters in the description occur in the preferences
        // This fills out "null" values with the defaults.
        for (Enumeration e = sd.getStylesheetParameterNames() ; e.hasMoreElements() ;) {
            String pname=(String) e.nextElement();
            if(parameters.get(pname)==null) {
                parameters.put(pname,sd.getStylesheetParameterDefaultValue(pname));
            }
        }
    }

}
