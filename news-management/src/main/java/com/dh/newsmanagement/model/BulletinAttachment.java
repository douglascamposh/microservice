package com.dh.newsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "bulletin_attachment")
public class BulletinAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private String field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Bulletin bulletin;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private Boolean isDeleted = false;
}
