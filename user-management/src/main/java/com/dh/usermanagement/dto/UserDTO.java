package com.dh.usermanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String name1;
    private String name2;
    private String account;
    private Long userId;
}
