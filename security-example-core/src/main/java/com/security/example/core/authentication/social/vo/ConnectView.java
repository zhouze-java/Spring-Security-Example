package com.security.example.core.authentication.social.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周泽
 * @date Create in 22:01 2019/8/22
 * @Description 绑定成功视图
 */
public class ConnectView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<>(1);


        response.setContentType("application/json;charset=UTF-8");

        if (model.get("connection") == null) {
            result.put("message", "解绑成功");

        } else {
            result.put("message", "绑定成功");
        }

        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

}
