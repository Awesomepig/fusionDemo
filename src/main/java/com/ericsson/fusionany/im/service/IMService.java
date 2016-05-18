package com.ericsson.fusionany.im.service;

import com.ericsson.fusionany.im.enmu.MessageType;
import net.sf.json.JSONArray;

/**
 * Service for IM
 * Created by eric on 16-5-16.
 */
public interface IMService {

    /**
     *
     * @param start start position begin to query,all <i>negative number</i>will be reset to  <i>0</i>
     * @param total total message count to return, all <i>negative number</i>will be reset to  <i>10</i>
     * @param type message type need to be query, @see com.ericsson.fusionany.im.enmu.MessageType
     * @return the result list in json array
     */
    public JSONArray getMessageList(int start,int total, MessageType type);

    /**
     * add text message
     * @param content text message content
     * @return if message add successfully
     */
    public boolean addMessageText (String content);

    /**
     * add emoji message
     * @param code emoji code
     * @return if message add successfully
     */
    public boolean addMessageEmoji (String code);

    /**
     * add file message
     * @param filename origin file name
     * @param relPath relative path of file
     * @return if message add successfully
     */
    public boolean addMessageFile (String filename,String relPath);

}
