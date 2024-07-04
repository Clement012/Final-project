package com.example.bc_stock_web.infra;

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
