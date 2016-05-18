package com.ericsson.fusionany.im.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * fd_msg_emoji entity
 * Created by eric on 16-5-16.
 */
@Entity( name = "fd_msg_emoji")
@Table( name = "fd_msg_emoji")
public class MsgEmoji implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Long id;
    @Column(name = "emj_code",length = 100)
    private String code;


    public MsgEmoji() {
    }

    public MsgEmoji(String code) {
        this.code = code;
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
     * get the emoji code
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
