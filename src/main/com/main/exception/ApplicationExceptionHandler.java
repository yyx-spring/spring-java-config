package com.main.exception;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/***
 * 拦截所有的Controller请求
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView error404(Exception ex) {

        return new ModelAndView("404");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public String myExceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        //如果异常有指定的HTTP状态码就继续抛出，不影响异常HTTP状态码的设置
        /*if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }*/
        return new JSONObject().toString();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView myExceptionHandler2(Exception e) {
        e.printStackTrace();
        return new ModelAndView("500");
    }


    /***
     * 对 WebDataBinder 对象进行初始化。WebDataBinder 是 DataBinder 的子类,用于完成由表单字段到 JavaBean 属性的绑定
     * 没有返回值,它必须声明为void，方法参数通常是是 WebDataBinder
     * spring mvc在绑定表单之前，都会先注册这些编辑器
     * @return
     */
    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(int.class, new IntegerEditor());
        binder.registerCustomEditor(long.class, new LongEditor());
        binder.registerCustomEditor(double.class, new DoubleEditor());
        binder.registerCustomEditor(float.class, new FloatEditor());
    }*/


    /***
     * 绑定请求参数到命令对象
     * 暴露表单引用对象为模型数据
     * 暴露@RequestMapping方法返回值为模型数据
     * @return
     */
    /*@ModelAttribute
    public void modelAttribute() {
        //TODO
    }*/
}
