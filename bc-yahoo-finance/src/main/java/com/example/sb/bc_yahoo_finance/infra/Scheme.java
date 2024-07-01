package com.example.sb.bc_yahoo_finance.infra;

public enum Scheme {
  HTTPS("https"),
  HTTP("http"),
  ;

  private String value;

  private Scheme(String value){
    this.value = value;
  }

  public String lowerCase(){
    return this.value.toLowerCase();
  }

}
