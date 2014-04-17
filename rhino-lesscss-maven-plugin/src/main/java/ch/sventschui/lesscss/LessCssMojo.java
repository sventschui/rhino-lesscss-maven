package ch.sventschui.lesscss;

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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;
import org.sonatype.plexus.build.incremental.BuildContext;

import ch.sventschui.lesscss.configuration.Directory;
import ch.sventschui.lesscss.configuration.Options;
import ch.sventschui.lesscss.configuration.utilities.OptionsMerge;
import ch.sventschui.lesscss.configuration.utilities.OptionsMergeFailedException;
import ch.sventschui.lesscss.runner.LessCssRunner;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal lesscss
 * 
 * @phase compile
 */
public class LessCssMojo extends AbstractMojo {

    /**
     * Directories
     * @parameter
     * @required
     */
    private Directory[] directories;

    /**
     * Options
     * @parameter
     */
    private Options options;

    /**
     * @component
     */
    private BuildContext buildContext;

    private FileSetManager fileSetManager = new FileSetManager();

    public void execute() throws MojoExecutionException {

        LessCssRunner runner = new LessCssRunner();

        for (Directory directory : directories) {

            FileSet fileSet = directory.getInput();
            File inBase = new File(fileSet.getDirectory());
            File outBase = directory.getOutput();

            for (String filename : fileSetManager.getIncludedFiles(fileSet)) {

                File input = new File(inBase, filename);

                if (buildContext != null && !buildContext.hasDelta(filename)) {
                    continue;
                }

                File output = new File(outBase, filename.replaceAll("\\.less$", ".css"));

                try {

                    getLog().info("Run " + input.getAbsolutePath());

                    runner.run(OptionsMerge.merge(options, directory.getOptions()), input, output);
                } catch (OptionsMergeFailedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    continue;
                }

                if (buildContext != null) {
                    buildContext.refresh(output);
                }

            }

        }
    }
}
