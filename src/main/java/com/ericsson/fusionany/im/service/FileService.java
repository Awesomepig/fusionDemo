package com.ericsson.fusionany.im.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Handle upload file
 * Created by eric on 16-5-16.
 */
public interface FileService {

    /**
     * Create a file from input stream to the directory <i>resources/file</i> under web root path
     * @param in inputStream
     * @param originName filename
     * @return the created file's Object
     * @throws FileNotFoundException
     */
    File createFile(InputStream in, String originName) throws FileNotFoundException;
}
