package ch.sventschui.lesscss.runner;

/*
 * (C) Copyright 2014 Sven Tschui (http://eniu.ch/) and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the GNU Lesser
 * General Public License (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class JavaScriptFunctionsHelper {

    File dir;

    public JavaScriptFunctionsHelper(File parentFile) {
        dir = parentFile;
    }

    public String readFile(String path, String enc) throws IOException {
        File file = new File(dir, path);

        return FileUtils.readFileToString(file);
    }

    public void print(Object obj) {
        System.out.println("JS print: " + obj.toString());
    }

    public void quit(Object obj) {
        System.out.println("JS exit code: " + obj.toString());
    }

}
