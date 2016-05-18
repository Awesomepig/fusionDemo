package com.ericsson.fusionany.im.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Message Table entity
 * Created by eric on 16-5-16.
 */
@Entity( name = "fd_message")
@Table( name = "fd_message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Long id;
    @Column(name = "mes_type")
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mes_time")
    private Date date;
    @Column(name = "mes_sub_id")
    private Long subId;
    @Column(name = "mes_if_del")
    private Integer ifDel;

    public Message() {
        // do nothing
    }

    public Message(Integer type, Long subId) {
        this.type = type;
        this.subId = subId;
        this.ifDel = 0;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", type=" + type +
                ", date=" + date +
                ", subId=" + subId +
                ", ifDel=" + ifDel +
                '}';
    }

    /**
     * get the primary key
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get Message type
     * @return message type code <ul>
     *     <li>1:text</li>
     *     <li>2:emoji</li>
     *     <li>3:file</li>
     * </ul>
     */
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * get message last modify date
     * @return
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * get the primary key of the specified message type
     * @return
     */
    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    /**
     * get the status of the message
     * @return <ul>
     *     <li>0:alive</li>
     *     <li>1:deleted</li>
     * </ul>
     */
    public Integer getIfDel() {
        return ifDel;
    }

    public void setIfDel(Integer ifDel) {
        this.ifDel = ifDel;
    }
}
