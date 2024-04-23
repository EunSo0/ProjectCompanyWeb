package com.study.springboot.customer.one2one.dto;

import com.study.springboot.customer.one2one.One2One;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class One2OneSaveRequestDto {
  private String one2oneName;
  private String one2onePhone;
  private String one2oneEmail;
  private String one2oneAddress;
  private String one2oneTitle;
  private String one2oneContent;

  @Builder
  public One2OneSaveRequestDto(String one2oneName, String one2onePhone, String one2oneEmail, String one2oneAddress, String one2oneTitle, String one2oneContent) {
    this.one2oneName = one2oneName;
    this.one2onePhone = one2onePhone;
    this.one2oneEmail = one2oneEmail;
    this.one2oneAddress = one2oneAddress;
    this.one2oneTitle = one2oneTitle;
    this.one2oneContent = one2oneContent;
  }

  public One2One toEntity() {
    return One2One.builder()
        .one2oneName(this.one2oneName)
        .one2onePhone(this.one2onePhone)
        .one2oneEmail(this.one2oneEmail)
        .one2oneAddress(this.one2oneAddress)
        .one2oneTitle(this.one2oneTitle)
        .one2oneContent(this.one2oneContent)
        .build();
  }
}
