package com.ericsson.fusionany.im.service.impl;

import com.ericsson.fusionany.im.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by eric on 16-5-16.
 */
@Service("fileService")
public class FileServiceImpl implements FileService{
    @Override
    public File createFile(InputStream in,String originName) throws FileNotFoundException {
        File file = null;
        byte[] buffer = new byte[1024];
        String filePath = "/home/eric/Space/Idea_space/fusionDemo/src/main/webapp/resources/file/"+originName;
        FileOutputStream fou = new FileOutputStream(new File(filePath));
        int count = 0;
        try {
            while (count!=-1){
                count=in.read(buffer);
                fou.write(buffer);
            }
            return new File(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (fou!=null){
                        fou.flush();
                        fou.close();
                    }
                    if (in!=null){
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
