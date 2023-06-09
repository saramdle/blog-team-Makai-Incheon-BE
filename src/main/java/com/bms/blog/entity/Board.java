package com.bms.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="BOARD")
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseEntity{

    @Id
    @Column(name="UUID")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "USER_UUID", referencedColumnName = "UUID")
    private User user;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENTS_PATH")
    private String contentsPath;

    @Column(name="TAGS")
    private String tags;

    @Column(name="VIEW_COUNT")
    private int viewCount;

    @Column(name="DELETE_DATE")
    LocalDateTime deleteDate;

    public Board(String uuid, User user, String title, String contentsPath, String tags, int viewCount, LocalDateTime deleteDate){
        this.uuid = uuid;
        this.user = user;
        this.title = title;
        this.contentsPath = contentsPath;
        this.tags = tags;
        this.viewCount = viewCount;
        this.deleteDate = deleteDate;
    }
}
