package com.study.springboot.community.dto;

import com.study.springboot.community.Community;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunitySaveRequestDto {
  private String noticeTitle;
  private String noticeContent;
  private String noticeMemberId;

  @Builder
  public CommunitySaveRequestDto(String noticeTitle, String noticeContent, String noticeMemberId) {
    this.noticeTitle = noticeTitle;
    this.noticeContent = noticeContent;
    this.noticeMemberId = noticeMemberId;
  }
  public Community toEntity() {
    return Community.builder()
        .noticeTitle(this.noticeTitle)
        .noticeContent(this.noticeContent)
        .noticeMemberId(this.noticeMemberId)
        .build();
  }
}
