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

        <title>My JSP 'user.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--->
        <script type="text/javascript">
	function follow(viewd, user, divObject) {
		var url = "FollowUser?viewedid=" + viewd + "&" + "uid=" + user;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			//微软IE6
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (null != req) {
			req.open("GET", url, true);
			req.onreadystatechange = comeback; //回调函数的方式，就是一个监听器
			//content-type" content="text/html; charset=UTF-8"
			//req.setRequestHeader("Content-Type",
			//       "application/x-www-form-urlencoded");
			req.send(null);
			//req.send("value1=" + document.getElementById("vv1").value
			//      + "&value2=" + document.getElementById("vv2").value);//post方式必须放入这里， get方式直接加之参数上
		}
	}
	function comeback() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				var respText = req.responseText;
				if ("ok" == respText) {
					document.getElementById("followorno").innerHTML = "取消关注";
				}

			}
		}
	}
	
</script>
    </head>

    <body>
        <%
            User viewedUser = (User) request.getAttribute("vieweduser");
        			User user = (User) session.getAttribute("user");
        			if (null != viewedUser) {
        %>

        我[<%=user.getUsername()%>]正在浏览
        <h6><%=viewedUser.getUsername()%></h6>
        ，
        <%=viewedUser.getTagline()%>
        <br>
        简介：
        <br>
        <p><%=viewedUser.getBio()%>
            <%
                if (!user.getFollowing().contains(viewedUser)) {
            %>
            <font color="red" size="16" id="followorno"
                style="cursor: hand"
                onclick="follow('<%=viewedUser.getId()%>', '<%=user.getId()%>', this);">关注我</font>
            <%
                } else {
            %>
            你已经关注他了
            <%
                }}else{
            %>
            你访问的用户不存在
            <%
                }
            %>
        
    </body>


</html>
