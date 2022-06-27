package top.mxzero.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.exception.ServiceNotFoundException;
import top.mxzero.common.exception.UserUnAuthExceptio;
import top.mxzero.common.http.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handlerHttpMessageNotReadableException(Exception e, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        ResponseData<?> fail = ResponseData.FAIL;
        fail.setStatus(response.getStatus());
        fail.setMessage(e.getMessage());

        return fail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletResponse response){
        response.setStatus(422);
        ResponseData<?> fail = ResponseData.FAIL;
        fail.setStatus(422);
        fail.setMessage("Validation error");

        List<String> list = new LinkedList<>();

        e.getAllErrors().forEach(objectError -> list.add(objectError.getDefaultMessage()));

        fail.setData(list);

        return fail;
    }

    @ExceptionHandler(UserUnAuthExceptio.class)
    public Object handlerUserUnAuthException(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseData<?> fail = ResponseData.FAIL;

        fail.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        fail.setMessage("Unauthorized");
        return fail;
    }

    @ExceptionHandler({NoHandlerFoundException.class, ServiceNotFoundException.class})
    public Object handlerNotFoundException(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        ResponseData<?> fail = ResponseData.FAIL;

        fail.setStatus(HttpServletResponse.SC_NOT_FOUND);
        fail.setMessage(String.format("%s Not found", request.getRequestURI()));
        return fail;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handlerHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        ResponseData<?> fail = ResponseData.FAIL;

        fail.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        fail.setMessage(String.format("Request method '%s' not supported", request.getMethod()));
        return fail;
    }

    @ExceptionHandler(ServiceException.class)
    public Object handlerServiceException(Exception e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        ResponseData<?> fail = ResponseData.FAIL;
        fail.setMessage(e.getMessage());
        fail.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return fail;
    }

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("{} {} {}", request.getRequestURI(), e.getClass().getName(), e.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        ResponseData<?> fail = ResponseData.FAIL;
        fail.setMessage(e.getMessage());
        fail.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return fail;
    }
}

