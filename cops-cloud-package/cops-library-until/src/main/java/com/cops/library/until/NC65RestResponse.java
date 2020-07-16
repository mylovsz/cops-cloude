package com.cops.library.until;

import java.util.HashMap;

/**
 * ResponseBody构造器。一般用于ajax、rest等类型的Web服务
 */
@SuppressWarnings("serial")
public class NC65RestResponse extends HashMap<String, Object> {
    public static NC65RestResponse success(){
        return success("成功");
    }
    public static NC65RestResponse success(String message){
        NC65RestResponse restResponse = new NC65RestResponse();
        restResponse.setSuccess(true);
        restResponse.setMessage(message);
        return restResponse;
    }

    public static NC65RestResponse failure(String message){
        NC65RestResponse restResponse = new NC65RestResponse();
        restResponse.setSuccess(false);
        restResponse.setMessage(message);
        return restResponse;
    }


    public NC65RestResponse setSuccess(Boolean success) {
        if (success != null) put("success", success);
        return this;
    }

    public NC65RestResponse setMessage(String message) {
        if (message != null) put("message", message);
        return this;
    }

    public NC65RestResponse setData(Object data) {
        if (data != null) put("data", data);
        return this;
    }
    public Object getData() {
        return get("data");
    }

    public NC65RestResponse setPage(Integer page) {
        if (page != null) put("page", page);
        return this;
    }
    
    public NC65RestResponse setCurrentPage(Integer currentPage){
    	if (currentPage != null) put("currentPage", currentPage);
        return this;
    }

    public NC65RestResponse setLimit(Integer limit) {
        if (limit != null) put("limit", limit);
        return this;
    }

    public NC65RestResponse setTotal(Long total) {
        if (total != null) put("total", total);
        return this;
    }

    public NC65RestResponse setAny(String key, Object value) {
        if (key != null && value != null) put(key, value);
        return this;
    }
}
