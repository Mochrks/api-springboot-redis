package com.redis_project.springboot.redis.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {

    public static <T> ResponseEntity<Map<String, Object>> getAllDataSuccess(List<T> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", HttpStatus.OK.value());
        response.put("total", data.size());
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Map<String, Object>> getDataByIdSuccess(T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", HttpStatus.OK.value());
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Map<String, Object>> createSuccess(T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", HttpStatus.CREATED.value());
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static ResponseEntity<Map<String, Object>> deleteSuccess() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", HttpStatus.OK.value());
        response.put("message", "Resource deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
