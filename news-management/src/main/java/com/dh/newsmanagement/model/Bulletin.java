package com.dh.newsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "bulletin")
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long senderUserId;

    private String textId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    private int commentsCounter = 0;

    private int likesCounter = 0;

    @OneToMany(targetEntity = RateBulletinLikes.class, mappedBy = "bulletin", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RateBulletinLikes> likes = new ArrayList<>();

    @OneToMany(targetEntity = BulletinAttachment.class, mappedBy = "bulletin", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BulletinAttachment> attachments = new ArrayList<>();

    @OneToMany(targetEntity = BulletinComment.class, mappedBy = "bulletin", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BulletinComment> comments = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    private Boolean isDeleted = false;
}
