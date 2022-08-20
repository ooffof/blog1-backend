package top.cuizilin.website.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.cuizilin.website.utils.Constants;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public String handleBindException(BindException exception){
        return exception.getAllErrors().get(0).getDefaultMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException exception){
        return exception.getLocalizedMessage().substring(exception.getLocalizedMessage().indexOf(":")+1);
    }



    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> handleException(Exception ex){
        //debug
        ex.printStackTrace();

        Map<String, String> exceptionMap = new HashMap<>();
        exceptionMap.put("code", Constants.generalExceptionCode);
        exceptionMap.put("message", Constants.generalExceptionMessage);
        return exceptionMap;
    }

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map<String, String> handleMyException(MyException myex){
        Map<String, String> myExceptionMap = new HashMap<>();
        myExceptionMap.put("code", myex.getErrorCode());
        myExceptionMap.put("message", myex.getErrorMessage());
        return myExceptionMap;
    }

}
