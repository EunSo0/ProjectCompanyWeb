package com.study.springboot.member.dto;

import com.study.springboot.member.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MembeSaveRequestDto {
  private String memberId;
  private String memberPw;
  private String memberName;
  private String memberEmail;
  private int memberEmailReceive;
  private int memberPwQuestion;
  private String memberPwAnswer;
  private String memberGender;
  private LocalDate memberBirthDate;

  @Builder
  public MembeSaveRequestDto(String memberId, String memberPw,
                             String memberName, String memberEmail, int memberEmailReceive,
                             int memberPwQuestion, String memberPwAnswer, String memberGender,
                             LocalDate memberBirthDate) {
    this.memberId = memberId;
    this.memberPw = memberPw;
    this.memberName = memberName;
    this.memberEmail = memberEmail;
    this.memberEmailReceive = memberEmailReceive;
    this.memberPwQuestion = memberPwQuestion;
    this.memberPwAnswer = memberPwAnswer;
    this.memberGender = memberGender;
    this.memberBirthDate = memberBirthDate;
  }
  public Member toEntity() {
    return Member.builder()
        .memberId(this.memberId)
        .memberPw(this.memberPw)
        .memberName(this.memberName)
        .memberEmail(this.memberEmail)
        .memberEmailReceive(this.memberEmailReceive)
        .memberPwQuestion(this.memberPwQuestion)
        .memberPwAnswer(this.memberPwAnswer)
        .memberGender(this.memberGender)
        .memberBirthDate(this.memberBirthDate)
        .build();
  }
}
