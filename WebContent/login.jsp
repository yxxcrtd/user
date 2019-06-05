<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>登录系统</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body onload="document.getElementById('username').focus()" style="margin: 0px">
    <html:form action="login.do"  method="post">
<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20"><img src="images/t_l.gif" width="20" height="96" /></td>
        <td background="images/t_b.png"><img src="images/t_t.png" width="364" height="76" /></td>
        <td width="17"><img src="images/t_r.png" width="17" height="96" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="padding-left:120px">&nbsp;</td>
  </tr>
  <tr>
    <td><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
        <td><img src="images/b_t_t_13.gif" width="184" height="78" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="24"><img src="images/b_lt.png" width="24" height="24" /></td>
        <td background="images/b_t_b.png">&nbsp;</td>
        <td width="24"><img src="images/b_rt.png" width="24" height="24" /></td>
      </tr>
      <tr>
        <td background="images/b_l_b.png">&nbsp;</td>
        <td ><table width="60%" height="180px" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td>&nbsp;</td>
            </tr>
          <tr>
            <td style="font-size:9pt">用户名： <html:text property="username" value="" style="width:200px;height:18px;border-style:solid;border-width:1px;border-color:#CCC;"/></td>
            </tr>
          <tr>
            <td style="font-size:9pt">密　码： <html:password property="userpsd" value="" style="width:200px;height:18px;border-style:solid;border-width:1px;border-color:#CCC;"/> <a href="ForgetPsd.jsp" onclick="this.href='ForgetPsd.jsp?user=' + document.getElementById('username').value" target="_blank">忘记您的密码了？</a></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            </tr>
          <tr>
            <td><div align="center">
              <html:submit value="登录" style="border-style:none;background-image:url(images/l_s.gif);width:68px;height:20px"/>
            <html:hidden property="redUrl" value="<%= request.getParameter("RedUrl") %>" /></div></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            </tr>
        </table></td>
        <td background="images/b_r_b.gif">&nbsp;</td>
      </tr>
      <tr>
        <td><img src="images/b_lb.png" width="24" height="24" /></td>
        <td background="images/b_b_b.png">&nbsp;</td>
        <td><img src="images/b_rb.png" width="24" height="24" /></td>
      </tr>
      <tr>
        <td height="100">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10"><img src="images/b_l.png" width="10" height="50" /></td>
        <td background="images/b_b.png">&nbsp;</td>
        <td width="10"><img src="images/b_r.png" width="10" height="50" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</html:form>
  </body>
</html:html>
