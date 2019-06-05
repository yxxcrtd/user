<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language=javascript>
	var sTarUrl = '';
	
	function GotoTarUrl() {
		sTarUrl = document.getElementById('UrlLink').href;
		window.setTimeout('SetLocation()', 2000);
	}
	
	function SetLocation() {
		if (sTarUrl.indexOf(':') == 10 && sTarUrl.substr(0, 10).toLowerCase() == 'javascript') {
			eval(sTarUrl.substr(11));
		} else {
			window.location = sTarUrl;
		}
	}
	</script>

  </head>
  
  <body onload="GotoTarUrl()"><br><br>
      <table border="0" width="400" style="border:1 solid black;border-collapse:collapse;" align="center">
        <tr>
          <td style="font-size:10pt;background-color:green;color:white;font-weight:bold;text-align:left"><bean:write name="MsgInfo" property="title" /></td>
        </tr>
        <tr>
          <td style="font-size:9pt;color:black;text-align:center"><bean:write name="MsgInfo" property="content" /><br><br></td>
        </tr>
        <tr>
          <td style="font-size:9pt;color:black;text-align:center"><a id="UrlLink" href="<bean:write name="MsgInfo" property="linkurl" />"><bean:write name="MsgInfo" property="linktext" /></a></td>
        </tr>
      </table>
  </body>
</html>
