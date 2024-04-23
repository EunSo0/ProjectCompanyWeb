package com.study.springboot.member.dto;

import com.study.springboot.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
  private Long memberIdx;
  private String memberId;
  private String memberPw;
  private String memberName;
  private String memberEmail;
  private int memberEmailReceive;
  private int memberPwQuestion;
  private String memberPwAnswer;
  private String memberGender;
  private LocalDate memberBirthDate;
  private LocalDateTime memberJoinDate;

  public MemberResponseDto(Member entity){
    this.memberIdx = entity.getMemberIdx();
    this.memberId = entity.getMemberId();
    this.memberPw = entity.getMemberPw();
    this.memberName = entity.getMemberName();
    this.memberEmail = entity.getMemberEmail();
    this.memberEmailReceive = entity.getMemberEmailReceive();
    this.memberPwQuestion = entity.getMemberPwQuestion();
    this.memberPwAnswer = entity.getMemberPwAnswer();
    this.memberGender = entity.getMemberGender();
    this.memberBirthDate = entity.getMemberBirthDate();
    this.memberJoinDate = entity.getMemberJoinDate();
  }

}