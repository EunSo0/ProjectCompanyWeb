package com.study.springboot.customer.qna;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="company_qna")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnA {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "qna_idx", nullable = false)
  private Long qnaIdx;
  @Column(name = "qna_name", nullable = false)
  private String qnaName;
  @Column(name = "qna_pw", nullable = false)
  private String qnaPw;
  @Column(name = "qna_title", nullable = false)
  private String qnaTitle;
  @Column(name = "qna_content", nullable = false)
  private String qnaContent;
  @Column(name = "qna_date", nullable = false)
  private LocalDateTime qnaDate = LocalDateTime.now();

  @Builder
  public QnA(String qnaName, String qnaPw, String qnaTitle, String qnaContent) {
    this.qnaName = qnaName;
    this.qnaPw = qnaPw;
    this.qnaTitle = qnaTitle;
    this.qnaContent = qnaContent;
    this.qnaDate = LocalDateTime.now();
  }
}
