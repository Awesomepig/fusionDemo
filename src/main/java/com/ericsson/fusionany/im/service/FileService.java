package com.ericsson.fusionany.im.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by eric on 16-5-16.
 */
public interface FileService {

    public File createFile(InputStream in,String originName) throws FileNotFoundException;
}
