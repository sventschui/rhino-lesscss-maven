package ch.sventschui.lesscss;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
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

            for (String file : fileSetManager.getIncludedFiles(fileSet)) {

                File input = new File(inBase, file);
                
                if(buildContext != null && !buildContext.hasDelta(file)) {
                    continue;
                }

                File output = new File(outBase, file);

                try {
                    
                    getLog().info("Run " + input.getAbsolutePath());
                    
                    runner.run(OptionsMerge.merge(options, directory.getOptions()), input, output);
                } catch (OptionsMergeFailedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                
                    continue;
                }
                
                if(buildContext != null) {
                    buildContext.refresh(output);
                }

            }

        }
    }
}
