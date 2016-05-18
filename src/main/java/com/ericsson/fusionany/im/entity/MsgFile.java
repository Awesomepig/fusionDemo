package com.ericsson.fusionany.im.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * fd_msg_file entity
 * Created by eric on 16-5-16.
 */
@Entity( name = "fd_msg_file")
@Table( name = "fd_msg_file")
public class MsgFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    private Long id;
    @Column(name = "fil_filename",length = 100)
    private String name;
    @Column(name = "fil_path",length = 500)
    private String relativePath;

    public MsgFile() {
    }

    public MsgFile(String name, String relativePath) {
        this.name = name;
        this.relativePath = relativePath;
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
     * get the file name
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the relative file path
     * @return
     */
    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
