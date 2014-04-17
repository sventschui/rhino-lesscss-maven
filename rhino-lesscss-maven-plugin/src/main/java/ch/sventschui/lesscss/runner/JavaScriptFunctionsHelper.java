package ch.sventschui.lesscss.runner;

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
