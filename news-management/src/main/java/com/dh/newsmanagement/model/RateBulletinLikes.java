package com.dh.newsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rate_bulletin_likes")
public class RateBulletinLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private Long userId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @ToString.Exclude
    private Bulletin bulletin;
}
