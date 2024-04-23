package com.study.springboot.member.dto;

import lombok.Data;

@Data
public class MemberLoginDto {
  private String memberId;
  private String memberPw;
}
