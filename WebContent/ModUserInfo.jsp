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
    
    <title>修改用户信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="javascript">
	function CheckSub() {
		if (document.getElementById('loginPw').value != document.getElementById('conPsd').value) {
			alert('确认口令不一致，请重新输入！');
			document.getElementById('loginPw').focus();
			return false;
		}
		
		return true;
	}
	</script>
  </head>
  
  <body onload="document.getElementById('name').focus()" style="margin:0">
  	<table align="center" width="85%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20"><img src="images/t_l.gif" width="20" height="96" /></td>
        <td background="images/t_b.png"><img src="images/t_t.png" width="364" height="76" /></td>
        <td width="17"><img src="images/t_r.png" width="17" height="96" /></td>
      </tr>
    </table><br>
    <html:form action="modifyUserInfo.do" method="post" enctype="multipart/form-data" onsubmit="return CheckSub();">
    <table border="0" width="80%" align="center">
  		<tr>
  			<td width= 50%>
  	<logic:notEqual name="UserLev" value="0">
  		<input type="submit" value="保存">
    </logic:notEqual>
    		</td>
    		<td width= 50% align="right"><input type="button" value="返回" onclick="window.history.back()"></td>
    	</tr>
    </table>
    <table height="360px" border="1" bordercolor="gray" align="center" width="80%" cellspacing="0" style="border-collapse:collapse;border:2 solid black;font-size:9pt;">
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
          <td><html:text property="name" value="<%= DataInfo.getName() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>登录口令：</td>
          <td><html:password property="loginPw"  value="" style="width:200;height:20;" />
          <br><input type="password" name="conPsd" style="width:200;height:20;" /> *确认口令*</td>
        </tr>
        <tr>
          <td>密码问题：</td>
          <td><html:text property="question" value="<%= DataInfo.getQuestion() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>密码答案：</td>
          <td><html:text property="answer" value="" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>用户头像：</td>
          <td>
          	<logic:notEqual name="CurDataInfo" property="icon" value=""><img src="<bean:write name="CurDataInfo" property="icon" />"><input type=checkbox name="DelIcon" value="1">删除头像</logic:notEqual>
          	<logic:equal name="CurDataInfo" property="icon" value=""><html:file property="uploads" /></logic:equal>
          </td>
        </tr>
        <tr>
          <td>活动标识：</td>
          <td><input type="checkbox" name="active" <%= (iUserLev < 2) ? "disabled" : "" %> <%= DataInfo.getActive() == 1 ? "checked" : "" %>></td>
        </tr>
        <tr>
          <td>教师标识：</td>
          <td><input type="checkbox" name="teacher" <%= (iUserLev < 2) ? "disabled" : "" %> <%= DataInfo.getTeacher() == 1 ? "checked" : "" %>></td>
        </tr>
    </table>
       <html:hidden property="id" value="<%= DataInfo.getId().toString() %>" />
       <html:hidden property="_Level" value="<%= request.getAttribute("UserLev").toString() %>"/>
       <html:hidden property="_Byid" value="<%= request.getParameter("id") == null ? "" : request.getParameter("id") %>"/>
    </html:form>
  </body>
</html:html>
