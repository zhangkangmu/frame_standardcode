package com.hong.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User2 {
    private Integer userId;
    private String username;
    private Date userBirthday;
    private String userSex;
    private String userAddress;
}
