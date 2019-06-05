<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>注册用户</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="javascript">
	function CheckFieldValue(FieldName) {
		try {
			var sFieldValue = document.getElementById(FieldName).value;
			var iCharCode = 0;
			
			for (var i = 0; i < sFieldValue.length; i ++) {
				iCharCode = sFieldValue.charCodeAt(i);
				
				if ((iCharCode >= 48 && iCharCode <= 57) || (iCharCode >= 65 && iCharCode <= 90) || (iCharCode >= 97 && iCharCode <= 122) || iCharCode >= 19968) {
					continue;
				} else {
					return false;
				}
			}
		} catch (e) {
			return false;
		}
		
		return true;
	}
	
	function CheckSub() {
		if (document.getElementById('code').value == '') {
			alert('“编号”不能为空，请重新输入！');
			document.getElementById('code').focus();
			return false;
		} else {
			if (!CheckFieldValue('code')) {
				alert('“编号”中只可输入字母、数字和汉字，请重新输入！');
				document.getElementById('code').focus();
				return false;
			}
		}
		
		if (document.getElementById('loginId').value == '') {
			alert('“登录名”不能为空，请重新输入！');
			document.getElementById('loginId').focus();
			return false;
		} else {
			if (!CheckFieldValue('loginId')) {
				alert('“登录名”中只可输入字母、数字和汉字，请重新输入！');
				document.getElementById('loginId').focus();
				return false;
			}
		}
		
		if (document.getElementById('loginPw').value != document.getElementById('conPsd').value) {
			alert('确认口令不一致，请重新输入！');
			document.getElementById('loginPw').focus();
			return false;
		}
		
		if (document.getElementById('loginPw').value == '') {
			alert('口令不能为空，请重新输入！');
			document.getElementById('loginPw').focus();
			return false;
		}
		
		return true;
	}
	</script>
  </head>
<% 
	com.chinaedustar.common.PageVerifyOpe.DoRegNewUser(pageContext);
	com.chinaedustar.hbm.TUser DataInfo = (com.chinaedustar.hbm.TUser) pageContext.getAttribute("CurDataInfo");
 %>  
  <body onload="document.getElementById('code').focus()" style="margin:0">
  	<table align="center" width="85%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20"><img src="images/t_l.gif" width="20" height="96" /></td>
        <td background="images/t_b.png"><img src="images/t_t.png" width="364" height="76" /></td>
        <td width="17"><img src="images/t_r.png" width="17" height="96" /></td>
      </tr>
    </table><br>
  	<html:form action="regNewUser.do" method="post" enctype="multipart/form-data" onsubmit="return CheckSub();">
    <table border="0" width="80%" align="center">
  		<tr>
  			<td width= 50%><input type="submit" value="保存"></td>
    		<td width= 50% align="right"><input type="button" value="返回" onclick="window.history.back()"></td>
    	</tr>
    </table>
    <table height="450px" border="1" bordercolor="gray" align="center" width="80%" cellspacing="0" style="border-collapse:collapse;border:2 solid black;font-size:9pt;">
       <colgroup width="20%" align="center" style="background-color:#F090FF;"></colgroup>
       <colgroup width="80%" align="left" style="background-color:#F5F5F5"></colgroup>
       <tr>
        <td>教师编号<br>或<br>学生学号：</td>
          <td><html:text property="code" value="<%= DataInfo.getCode() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>登 录 名：</td>
          <td><html:text property="loginId" value="<%= DataInfo.getLoginId() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>用户姓名：</td>
          <td><html:text property="name" value="<%= DataInfo.getName() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>登录口令：</td>
          <td><html:password property="loginPw"  value="<%= DataInfo.getLoginPw() %>" style="width:200;height:20;" />
          <br><input type="password" name="conPsd" style="width:200;height:20;" /> *确认口令*</td>
        </tr>
        <tr>
          <td>密码问题：</td>
          <td><html:text property="question" value="<%= DataInfo.getQuestion() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>密码答案：</td>
          <td><html:text property="answer" value="<%= DataInfo.getAnswer() %>" style="width:200;height:20;" /></td>
        </tr>
        <tr>
          <td>用户头像：</td>
          <td>
          	<html:file property="uploads" />
          </td>
        </tr>
        <tr>
          <td>活动标识：</td>
          <td><input type="checkbox" name="active" <%= DataInfo.getActive() == 1 ? "checked" : "" %>></td>
        </tr>
        <tr>
          <td>教师标识：</td>
          <td><input type="checkbox" name="teacher" <%= DataInfo.getTeacher() == 1 ? "checked" : "" %>></td>
        </tr>
    </table>
    </html:form>
   </body>
</html:html>
