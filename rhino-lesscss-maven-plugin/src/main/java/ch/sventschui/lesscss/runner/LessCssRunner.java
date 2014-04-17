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
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.maven.plugin.MojoExecutionException;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import ch.sventschui.lesscss.configuration.Options;

public class LessCssRunner {

    private Reader less = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("less-rhino-1.7.0.js"));

    private Context cx;

    private Scriptable scope;

    private int line = 1;

    public LessCssRunner() {

        // TODO: we should exit the context at some point
        cx = Context.enter();
        scope = cx.initStandardObjects();

        try {

            // proxy some functions used by less(c)-rhino.js to the JavaScriptFunctionsHelper (will be added to the
            // scope for each run, since it relays on the input file folder)
            cx.evaluateString(scope, "var readFile = function(a, b) { return ''+lessCssMavenHelper.readFile(a,b); };",
                    "<cmd>", line++, null);
            cx.evaluateString(scope, "var writeFile = function(a, b) { lessCssMavenHelper.writeFile(a,b); };", "<cmd>",
                    line++, null);
            cx.evaluateString(scope, "var print = function(a) { lessCssMavenHelper.print(a); };", "<cmd>", line++, null);
            cx.evaluateString(scope, "var quit = function(a) { lessCssMavenHelper.quit(a); };", "<cmd>", line++, null);
            cx.evaluateReader(scope, less, "less-rhino-1.7.0.js", line++, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            try {
                less.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public void run(Options options, File input, File output) throws MojoExecutionException {

        output.getParentFile().mkdirs();

        Reader lessc = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("lessc-rhino-1.7.0.js"));

        JavaScriptFunctionsHelper h = new JavaScriptFunctionsHelper(input.getParentFile());
        Object wrappedHelper = Context.javaToJS(h, scope);
        ScriptableObject.putProperty(scope, "lessCssMavenHelper", wrappedHelper);

        cx.evaluateString(scope, "var arguments = [];", "<cmd>", line++, null);
        cx.evaluateString(scope, "arguments.push('" + input.getName() + "');", "<cmd>", line++, null);
        cx.evaluateString(scope, "arguments.push('" + getNormailzedCanonicalPath(output) + "');", "<cmd>", line++, null);

        // TODO: evaluate options/arguments

        try {
            cx.evaluateReader(scope, lessc, "lessc-rhino-1.7.0.js", line++, null);
        } catch (IOException e) {
            try {
                lessc.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getNormailzedCanonicalPath(File f) throws MojoExecutionException {
        try {
            return f.getCanonicalPath().replaceAll("\\\\", "/");
        } catch (IOException e) {
            throw new MojoExecutionException("", e);
        }
    }

}
