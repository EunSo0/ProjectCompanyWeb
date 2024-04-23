package com.study.springboot.customer.qna.dto;

import com.study.springboot.customer.qna.QnA;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QnAResponseDto {
  private Long qnaIdx;
  private String qnaName;
  private String qnaPw;
  private String qnaTitle;
  private String qnaContent;
  private LocalDateTime qnaDate;

  public QnAResponseDto(QnA entity) {
    this.qnaIdx = entity.getQnaIdx();
    this.qnaName = entity.getQnaName();
    this.qnaPw = entity.getQnaPw();
    this.qnaTitle = entity.getQnaTitle();
    this.qnaContent = entity.getQnaContent();
    this.qnaDate = entity.getQnaDate();
  }
}
