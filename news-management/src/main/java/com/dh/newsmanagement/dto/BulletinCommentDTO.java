package com.dh.newsmanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class BulletinCommentDTO {
    private Long commentId;

    private Long accountId;

    private int repliesCounter;

    private String content;

    private UserDTO senderUser;

    private Long wallMessageId;

    private Date createdDate;

    private Boolean isDeleted;
}
