<%@ page language="java" import="java.util.*,com.why.model.*"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'asks.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    </head>

    <body>
        <%
            Ask ask = (Ask) request.getAttribute("ask");
            if (null != ask) {
        %>
        <h3><%=ask.getTitle()%></h3>
        <p><%=ask.getBody()%></p>
        <%
            } else {
        %>
        抱歉， 我们找不到你的问题
        <%
            }
        %>
        
        <font color="#cdefab">我要回答</font>:<br>
        <form action="SubmitAnswer" method="post">
            <textarea rows="8" cols="30"></textarea>
            <input type="submit" value="提交">
        </form>
    </body>
</html>
