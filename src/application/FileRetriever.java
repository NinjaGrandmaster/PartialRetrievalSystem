package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileRetriever {
    private File directory;
    private List<File> fileList;

    public FileRetriever (File directory) {
        this.directory = directory;
        this.fileList = new ArrayList<>();

        for (File file: directory.listFiles()) {
            // if file is not a directory add ot to list
            // Does not support subfolder file discovery
            if (!file.isDirectory()) {
                // add names of each file in a directory to list
                fileList.add(file);
            }
        }

    }

    public String getDirectoryAsString() {
        return this.directory.toString();
    }

    public List<File> getFileList() {
        return this.fileList;
    }

    @Override
    public String toString() {
        return this.getFileList().toString();
    }
}
