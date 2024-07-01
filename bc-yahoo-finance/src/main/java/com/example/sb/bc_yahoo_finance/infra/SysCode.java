package com.example.sb.bc_yahoo_finance.infra;

import lombok.Getter;

@Getter
public enum SysCode {
  OK(0, "success."), //
  USER_NOT_FOUND(1, "User not found."), //
  INVALID_INPUT(2, "Invalid Input."), //
  RESTTEMPLATE_ERROR(3, "RestTemplate Error - JsonPlaceHolder."), //
  ;

  private int code;
  private String desc;

  private SysCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
