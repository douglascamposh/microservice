package com.dh.newsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Setter
@Table(name = "bulletin_comment")
@ToString
public class BulletinComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private int repliesCounter = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Bulletin bulletin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    private Boolean isDeleted = false;
}
