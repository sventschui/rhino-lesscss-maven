package ch.sventschui.lesscss.configuration;

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
