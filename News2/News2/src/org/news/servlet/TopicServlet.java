﻿package org.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.entity.Topic;
import org.news.service.TopicsService;
import org.news.service.impl.TopicsServiceImpl;

import com.alibaba.fastjson.JSON;

public class TopicServlet extends HttpServlet {
    private static final long serialVersionUID = -8823896301195695638L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        out.flush();
        out.close();
    }

}
