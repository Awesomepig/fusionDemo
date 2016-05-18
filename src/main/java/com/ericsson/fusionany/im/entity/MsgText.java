package com.ericsson.fusionany.im.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * fd_msg_plaintext entity
 * Created by eric on 16-5-16.
 */

@Entity( name = "fd_msg_plaintext")
@Table( name = "fd_msg_plaintext")
public class MsgText implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Long id;
    @Column(name = "ptx_content",length = 500)
    private String content;

    public MsgText() {
    }

    public MsgText(String content) {
        this.content = content;
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
     * get the content of this message
     * @return
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
