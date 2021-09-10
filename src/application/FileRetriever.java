package application;

import java.io.File;
import java.util.ArrayList;

public class FileRetriever {
    private File directory;
    private ArrayList<String> fileList;

    public FileRetriever (File directory) {
        this.directory = directory;
        this.fileList = new ArrayList<>();

        for (File file: directory.listFiles()) {
            // if file is not a directory add ot to list
            // Does not support subfolder file discovery
            if (!file.isDirectory()) {
                // add names of each file in a directory to list
                fileList.add(file.getName());
            }
        }

    }

    public String getDirectoryAsString() {
        return this.directory.toString();
    }

    public ArrayList<String> getFileList() {
        return this.fileList;
    }

    @Override
    public String toString() {
        return this.getFileList().toString();
    }
}
