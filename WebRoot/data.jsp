<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.*,spider.*,com.alibaba.fastjson.*" %>
<%
    //http://localhost:8080/spider/data.jsp?pageNum=1&category=fresh   可看小清新第一页爬下的内容
	//获取页码数
	String pageNum = request.getParameter("pageNum");
	//获取图片类型
	String category = request.getParameter("category");
	//获取图片集合
	List<Image> images = SpiderUtil.queryImageList(category,pageNum);
	//设置返回值的类型
	response.setContentType("text/html; charset=utf-8");
	out.print(JSONArray.toJSONString(images, true));
%>