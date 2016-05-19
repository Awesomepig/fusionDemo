package com.ericsson.fusionany.im.service.impl;

import com.ericsson.fusionany.im.dao.IMDao;
import com.ericsson.fusionany.im.enmu.MessageType;
import com.ericsson.fusionany.im.entity.Message;
import com.ericsson.fusionany.im.entity.MsgEmoji;
import com.ericsson.fusionany.im.entity.MsgFile;
import com.ericsson.fusionany.im.entity.MsgText;
import com.ericsson.fusionany.im.service.IMService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 16-5-16.
 */
@Service("imService")
public class IMServiceImpl implements IMService{

    @Autowired
    public IMDao imDao;

    @Override
    public JSONArray getMessageList(int start, int total,boolean ifDel, MessageType type) {
        Criterion[] criterions = null;
        List<Criterion> crList = new ArrayList<>();
        if(ifDel){
            crList.add(Restrictions.eq("ifDel",1));
        }else{
            crList.add(Restrictions.eq("ifDel",0));
        }

        switch (type){
            case TEXT:
                crList.add(Restrictions.eq("type",1));
            case EMOJI:
                crList.add(Restrictions.eq("type",2));
            case FILE:
                crList.add(Restrictions.eq("type",3));
        }

        List<Message> mesList = null;
        if (crList.size()<1){
            mesList = imDao.queryForList(Message.class,start,total,Order.desc("date"), new Criterion[]{});
        }else{
            criterions = new Criterion[crList.size()];
            for (int i=0;i<crList.size();i++){
                criterions[i] = crList.get(i);
            }
            mesList = imDao.queryForList(Message.class,start,total,Order.desc("date"),criterions);
        }
        if (null == mesList){
            return new JSONArray();
        }

        //循环获取指定类型的消息信息,填充到mesList的JsonObject 后面
        JSONArray result = new JSONArray();
        for (Message mes:mesList) {
            JSONObject mesJson = JSONObject.fromObject(mes);
            Integer mesType = mes.getType();
            Long mesId = mes.getSubId();
            JSONObject mesInfo = null;
            switch (mesType){
                case 1 :
                    MsgText mesText = imDao.queryById(MsgText.class, mesId);
                    mesInfo = JSONObject.fromObject(mesText);
                    break;
                case 2 :
                    MsgEmoji mesEmoji = imDao.queryById(MsgEmoji.class, mesId);
                    mesInfo = JSONObject.fromObject(mesEmoji);
                    break;
                case 3 :
                    MsgFile mesFile = imDao.queryById(MsgFile.class, mesId);
                    mesInfo = JSONObject.fromObject(mesFile);
                    break;
            }
            if (null==mesInfo){
                mesJson.element("mesInfo","");
            }else{
                mesJson.element("mesInfo",mesInfo);
            }
            result.element(mesJson);
        }
        return result;
    }

    @Override
    public boolean addMessageText(String content) {
        MsgText mesText = new MsgText(content);

        // 1.添加子表记录,并获得子表主键
        imDao.saveOrUpdateItem("fd_msg_plaintext",mesText);
        Long subId = mesText.getId();

        // 2. 添加主表记录
        Message mes = new Message(1, subId);
        imDao.saveOrUpdateItem("fd_message",mes);
        return true;
    }

    @Override
    public boolean addMessageEmoji(String code) {
        MsgEmoji mesEmoji = new MsgEmoji(code);

        // 1.添加子表记录,并获得子表主键
        imDao.saveOrUpdateItem("fd_msg_emoji",mesEmoji);
        Long subId = mesEmoji.getId();

        // 2. 添加主表记录
        Message mes = new Message(2, subId);
        imDao.saveOrUpdateItem("fd_message",mes);
        return true;
    }

    @Override
    public boolean addMessageFile(String filename, String relPath) {
        MsgFile mesFile = new MsgFile(filename,relPath);

        // 1.添加子表记录,并获得子表主键
        imDao.saveOrUpdateItem("fd_msg_file",mesFile);
        Long subId = mesFile.getId();

        // 2. 添加主表记录
        Message mes = new Message(3, subId);
        imDao.saveOrUpdateItem("fd_message",mes);
        return true;
    }



    public IMDao getImDao() {
        return imDao;
    }

    public void setImDao(IMDao imDao) {
        this.imDao = imDao;
    }
}
