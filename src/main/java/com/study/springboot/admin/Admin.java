package com.study.springboot.admin;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="company_member_admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_idx", nullable = false)
  private Long memberIdx;
  @Column(name = "member_id", nullable = false)
  private String memberId;
  @Column(name = "member_pw", nullable = false)
  private String memberPw;
  @Column(name = "member_name", nullable = false)
  private String memberName;
  @Column(name = "member_email", nullable = false)
  private String memberEmail;
  @Column(name = "member_join_date", nullable = false)
  private LocalDateTime memberJoinDate = LocalDateTime.now();

  @Builder
  public Admin(String memberId, String memberPw, String memberName, String memberEmail) {
    this.memberId = memberId;
    this.memberPw = memberPw;
    this.memberName = memberName;
    this.memberEmail = memberEmail;
    this.memberJoinDate = LocalDateTime.now();
  }
}
