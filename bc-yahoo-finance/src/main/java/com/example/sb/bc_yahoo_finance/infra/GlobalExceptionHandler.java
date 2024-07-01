package com.example.sb.bc_yahoo_finance.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//RestControllerAdvice // @ContollerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(NumberFormatException.class) // catch
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> numberFormatExceptionHandler(NumberFormatException e) {
    return ApiResp.<Void>builder() //
        .error(ErrorCode.NFE) //
        .build();
  }
  @ExceptionHandler(NullPointerException.class) // catch
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> nullPointerExceptionHandler(NullPointerException e) {
    return ApiResp.<Void>builder() //
        .error(ErrorCode.NPE) //
        .build();
  }
  @ExceptionHandler(ArithmeticException.class) // catch
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> arithmeticExceptionHandler(ArithmeticException e) {
    return ApiResp.<Void>builder() //
        .error(ErrorCode.AE) //
        .build();
  }
  @ExceptionHandler(BusinessRuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse businessRuntimeExceptionHandler(
      BusinessRuntimeException e) {
    return new ErrorResponse(e.getCode(), e.getMessage());
  }
  // @ExceptionHandler(UserNotFoundException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ErrorResponse notFoundExceptionHandler(
  //   UserNotFoundException e) {
  //   return new ErrorResponse(e.getCode(), e.getMessage());
  // }
  // @ExceptionHandler(InvalidInputException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ErrorResponse invalidInputExceptionHandler(
  //   InvalidInputException e) {
  //   return new ErrorResponse(e.getCode(), e.getMessage());
  // }
  // @ExceptionHandler(RestTemplateException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ErrorResponse restTemplateExceptionHandler(
  //   RestTemplateException e) {
  //   return new ErrorResponse(e.getCode(), e.getMessage());
  }
 /*  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse exceptionHandler(
    Exception e) {
    return new ErrorResponse(99999, "Other invalid input Exception.");
  } */


  // Alternative
  // @ExceptionHandler({NumberFormatException.class, NullPointerException.class,
  //     ArithmeticException.class})
  // public ErrorResponse exceptionHandler(RuntimeException e) {
  //   if (e instanceof NumberFormatException) {
  //     return ErrorResponse.of(ErrorCode.NFE.getCode(), ErrorCode.NFE.getDesc());
  //   } else if (e instanceof NullPointerException) {
  //     return ErrorResponse.of(ErrorCode.NPE.getCode(), ErrorCode.NPE.getDesc());
  //   } else if (e instanceof ArithmeticException) {
  //     return ErrorResponse.of(ErrorCode.AE.getCode(), ErrorCode.AE.getDesc());
  //   }
  //   return ErrorResponse.of(99999, "Unhandled Exception.");
  // }

  


