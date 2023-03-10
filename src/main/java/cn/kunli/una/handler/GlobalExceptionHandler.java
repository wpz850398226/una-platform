package cn.kunli.una.handler;

import cn.kunli.una.pojo.sys.SysException;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.error.BeanValidateErrorVo;
import cn.kunli.una.service.sys.SysExceptionService;
import cn.kunli.una.utils.common.UserUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: 全局异常处理
 * @author: Ponzio
 * @create: 2020-07-20 10:13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private SysExceptionService sysExceptionService;

    @ExceptionHandler(value = NoSuchFieldException.class)
    public void noSuchFieldException(BindException validateEx) {
        log.info("反射异常：没有该字段");
        //不做处理
    }

    @ExceptionHandler(value = BindException.class)
    public SysResult bindExceptionHandler(BindException validateEx) {

        BindingResult bindingResult = validateEx.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<BeanValidateErrorVo> result = new ArrayList<>();
        allErrors.stream().forEach(error -> {
            BeanValidateErrorVo errorVo = new BeanValidateErrorVo();
            if (error instanceof FieldError) {
                errorVo.setErrorField(((FieldError) error).getField());
            }
            errorVo.setErrorMsg(error.getDefaultMessage());
            result.add(errorVo);
        });
        return new SysResult().fail(result.get(0).getErrorField()+":"+result.get(0).getErrorMsg(),result);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public SysResult validateHandler(MethodArgumentNotValidException validateEx) {

        BindingResult bindingResult = validateEx.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<BeanValidateErrorVo> result = new ArrayList<>();
        allErrors.stream().forEach(error -> {
            BeanValidateErrorVo errorVo = new BeanValidateErrorVo();
            if (error instanceof FieldError) {
                errorVo.setErrorField(((FieldError) error).getField());
            }
            errorVo.setErrorMsg(error.getDefaultMessage());
            result.add(errorVo);
        });
        return new SysResult().fail("错误：参数格式不正确",result);
    }

    /**
     * 处理JSON解析异常
     *
     * @param jsonParseEx
     * @return
     */
    @ExceptionHandler(value = JsonMappingException.class)
    public SysResult jsonParseHandler(JsonMappingException jsonParseEx) {

        List<String> errorField = jsonParseEx.getPath().stream().map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.toList());
        BeanValidateErrorVo beanValidateErrorVo = new BeanValidateErrorVo();
        beanValidateErrorVo.setErrorField(errorField.get(0));
        beanValidateErrorVo.setErrorMsg(jsonParseEx.getOriginalMessage());
        return new SysResult().fail("错误：JSON解析出现问题",beanValidateErrorVo);
    }

    /**
     * 空指针异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {NullPointerException.class})
    public SysResult nullException(HttpServletRequest request, NullPointerException ex) {
        ex.printStackTrace();
        SysException sysException = new SysException();
        sysException.setName("空指针异常处理");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"空指针异常处理！", sysException,null);
    }

    /**
     * 参数异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public SysResult badRequestException(HttpServletRequest request, IllegalArgumentException ex) {
        SysException sysException = new SysException();
        sysException.setName("参数异常");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("参数异常！", sysException);
    }

    /**
     * SQL异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {UncategorizedSQLException.class})
    public SysResult sqlException(HttpServletRequest request, UncategorizedSQLException ex) {
        SysException sysException = new SysException();
        sysException.setName("SQL异常处理");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("错误：SQL异常处理", sysException);
    }

    /**
     * Mybatis异常
     *
     * @return
     */
    @ExceptionHandler(value = {BadSqlGrammarException.class})
    public SysResult mybatisException(BadSqlGrammarException ex, HttpServletRequest request) {
        SysException sysException = new SysException();
        sysException.setName("Mybatis异常");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("错误：Mybatis异常", sysException);
    }

    /**
     * SpringMVC类型不匹配异常
     *
     * @param ex
     * @return
     */
    public SysResult typeMismathException(HttpServletRequest request, TypeMismatchException ex) {
        SysException sysException = new SysException();
        sysException.setName("SpringMVC类型不匹配异常");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("错误：SpringMVC类型不匹配", sysException);
    }


    /**
     * SpringMVC入参类型转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ConversionNotSupportedException.class})
    public SysResult conversionException(HttpServletRequest request, ConversionNotSupportedException ex) {
        SysException sysException = new SysException();
        sysException.setName("SpringMVC入参类型转换异常");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("错误：SpringMVC入参类型转换异常", sysException);
    }

    /**
     * SpringMVC方法传入的参数类型不匹配异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public SysResult methodArgumengTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) throws UnsupportedEncodingException {
        SysException sysException = new SysException();
        sysException.setName("SpringMVC方法传入的参数类型不匹配异常");
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("错误：参数类型不匹配", sysException);
    }

    /**
     * 自定义异常类型，返回前端
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = UnaResponseException.class)
    public SysResult unaExceptionHandler(UnaResponseException unaResponseException) {
        return new SysResult().fail(unaResponseException.getMessage());
    }

    /**
     * 反射异常
     *
     * @param ex
     * @return
     */
    /*@ExceptionHandler(value = {UndeclaredThrowableException.class})
    public void undeclaredThrowableException(HttpServletRequest request, UndeclaredThrowableException ex) {
        //反射异常，不予处理
        ex.printStackTrace();
        SysException sysException = new SysException();
        sysException.setName("反射异常处理");
        sysException.setErrorInfo(ex.getMessage());
        saveExceptionRecord(sysException, ex, request);
    }*/

    /**
     * 顶级异常处理
     *
     * @param ex
     * @return
     */
    /*@ExceptionHandler(value = {Exception.class})
    public SysResult exception(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        SysException sysException = new SysException();
        sysException.setName("顶级异常处理");
        sysException.setErrorInfo(ex.getMessage());
        saveExceptionRecord(sysException, ex, request);

        return new SysResult().fail("错误：初级异常", sysException);
    }*/


    /**
     * 获取错误信息
     *
     * @param ex
     * @return
     */
    public String getErrorMsgByEx(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter, true));
        String errorMsg = stringWriter.getBuffer().toString();
        return errorMsg;
    }

    /**
     * 获取请求路径、参数、请求体
     */
    public void saveExceptionRecord(SysException ex, Exception source, HttpServletRequest request) {
        //获取用户ID
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        //设置基本信息
        if(loginUser!=null){
            ex.setUsername(loginUser.getUsername());
            ex.setCreatorId(loginUser.getId());
            ex.setModifierId(loginUser.getId());
            //获取错误信息
            String errorMsgByEx = getErrorMsgByEx(source);
            ex.setErrorInfo(errorMsgByEx);
            //获取请求参数
            String requestParams = getRequestParams(request);
            ex.setInParam(requestParams);

            sysExceptionService.saveRecord(ex);
            //写入到文件
//        writeToFile(ex);
        }
    }

    /**
     * 获取request中的请求参数
     *
     * @return
     */
    private String getRequestParams(HttpServletRequest request) {
        /*ContentCachingRequestWrapper copyRequest = (ContentCachingRequestWrapper) ((ShiroHttpServletRequest) request).getRequest();
        byte[] contentAsByteArray = copyRequest.getContentAsByteArray();
        return new String(contentAsByteArray);*/
        return null;
    }



}
