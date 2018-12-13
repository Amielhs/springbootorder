package com.tt.springbootorder.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Description: 自定义响应数据结构
 * 这个类是提供给门户，ios，安卓，微信商城用的
 * 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 其他自行处理
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 * @Author: xyf
 * @CreateDate: 2018/6/27 16:31
 * @UpdateUser: xuyunfei
 * @UpdateDate: 2018/6/27 16:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class SpringJSONResult {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object result;

//    private String ok;    // 不使用

    public SpringJSONResult() {
    }

    public SpringJSONResult(Integer status, String msg, Object result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public SpringJSONResult(Object result) {
        this.status = 200;
        this.msg = "OK";
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

   /* public Object getData() {
        return result;
    }

    public void setData(Object result) {
        this.result = result;
    }*/

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    //    public String getOk() {
//        return ok;
//    }

//    public void setOk(String ok) {
//        this.ok = ok;
//    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public static SpringJSONResult build(Integer status, String msg, Object result) {
        return new SpringJSONResult(status, msg, result);
    }

    public static SpringJSONResult ok(Object result) {
        return new SpringJSONResult(result);
    }

    /*public static SpringJSONResult ok() {
        return new SpringJSONResult(null);
    }*/

    public static SpringJSONResult errorMsg(String msg) {
        return new SpringJSONResult(500, msg, null);
    }

    public static SpringJSONResult errorMap(Object result) {
        return new SpringJSONResult(501, "error", result);
    }

    public static SpringJSONResult errorTokenMsg(String msg) {
        return new SpringJSONResult(502, msg, null);
    }

    public static SpringJSONResult errorException(String msg) {
        return new SpringJSONResult(555, msg, null);
    }

    /*
         * description:
         * @author xyf
         * @param
         *  * @param jsonData
         * @param clazz
         * @return com.tt.springdemo.entity.SpringJSONResult
         * @throws
         */
    public static SpringJSONResult formatToEntity(String jsonData, Class<?> clazz) {

        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, SpringJSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (IOException e) {
            return null;
        }

    }
}
