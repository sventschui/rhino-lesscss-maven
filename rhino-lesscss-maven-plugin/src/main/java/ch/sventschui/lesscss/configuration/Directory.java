package ch.sventschui.lesscss.configuration;

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

import org.apache.maven.shared.model.fileset.FileSet;

public class Directory {

    private FileSet input;

    private File output;

    private Options options;

    public FileSet getInput() {
        return input;
    }

    public void setInput(FileSet input) {
        this.input = input;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

}
