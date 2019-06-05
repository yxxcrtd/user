<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% 
	com.chinaedustar.common.PageVerifyOpe.DoModUserInfo(pageContext);
	com.chinaedustar.hbm.TUser DataInfo = (com.chinaedustar.hbm.TUser) pageContext.getAttribute("CurDataInfo");
	int iUserLev = Integer.parseInt(request.getAttribute("UserLev").toString());
 %>

<html:html lang="true">
  <head>
    <html:base />
    
    <title>查看用户信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  
  <body style="margin:0">
  	<table align="center" width="85%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20"><img src="images/t_l.gif" width="20" height="96" /></td>
        <td background="images/t_b.png"><img src="images/t_t.png" width="364" height="76" /></td>
        <td width="17"><img src="images/t_r.png" width="17" height="96" /></td>
      </tr>
    </table><br>
  	<table border="0" width="80%" align="center">
  		<tr>
  			<td width= 50%>
  	<logic:notEqual name="UserLev" value="0">
  		<input type="button" value="编辑" onclick="window.location='ModUserInfo.jsp?id=<bean:write name="CurDataInfo" property="id" />'">
    </logic:notEqual>
    		</td>
    		<td width= 50% align="right"><input type="button" value="返回" onclick="window.history.back()"></td>
    	</tr>
    </table>
    <table height="150px" border="1" bordercolor="gray" align="center" width="80%" cellspacing="0" style="border-collapse:collapse;border:2 solid black;font-size:9pt;">
       <colgroup width="20%" align="center" style="background-color:#F090FF;"></colgroup>
       <colgroup width="80%" align="left" style="background-color:#F5F5F5"></colgroup>
       <tr>
        <td>教师编号<br>或<br>学生学号：</td>
          <td><bean:write name="CurDataInfo" property="code" /></td>
        </tr>
        <tr>
          <td>登 录 名：</td>
          <td><bean:write name="CurDataInfo" property="loginId" /></td>
        </tr>
        <tr>
          <td>用户姓名：</td>
          <td><bean:write name="CurDataInfo" property="name" /></td>
        </tr>
        <tr>
          <td>用户头像：</td>
          <td><logic:notEqual name="CurDataInfo" property="icon" value=""><img src="<bean:write name="CurDataInfo" property="icon" />"></logic:notEqual></td>
        </tr>
        <tr>
          <td>活动标识：</td>
          <td><%= DataInfo.getActive().intValue() == 1 ? "<img src=\"images/right.gif\">" : "" %></td>
        </tr>
        <tr>
          <td>教师标识：</td>
          <td><%= DataInfo.getTeacher().intValue() == 1 ? "<img src=\"images/right.gif\">" : "" %></td>
        </tr>
    </table>
  </body>
</html:html>
