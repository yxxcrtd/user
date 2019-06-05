<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="javascript" type="text/javascript" src="View.js"></script>
	<script language="javascript">
	function GoSearchData() {
		if (document.getElementById('SearchKey').value == '') {
			alert('搜索关键字不能为空');
		} else {
			document.location = 'UserList.jsp?sk=' + document.getElementById('SearchKey').value + '&PageNum=1';
		}
	}
	
	function GetAllData() {
		document.location = 'UserList.jsp';
	}
	</script>
  </head>
  <%
  	int iUserLev = 0;
  	
	com.chinaedustar.common.PageVerifyOpe.DoUserListPage(pageContext);
	com.chinaedustar.hbm.TUser oTUser = com.chinaedustar.common.PageVerifyOpe.GetPageUserInfo(pageContext);
	if (oTUser == null) {
		iUserLev = 0;
	} else if(oTUser.getLoginId().equals("admin")) {
		iUserLev = 1;
	} else {
		iUserLev = 0;
	}
	
	if (iUserLev < 1) {
		pageContext.forward("login.jsp");
	}
   %>
  <body onload="initPageNav(<bean:write name="ViewPageInfo" property="curpagenum" /> , <bean:write name="ViewPageInfo" property="allpagecou" /> , 5);" style="margin:0">
  	<table align="center" width="85%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20"><img src="images/t_l.gif" width="20" height="96" /></td>
        <td background="images/t_b.png"><img src="images/t_t.png" width="364" height="76" /></td>
        <td width="17"><img src="images/t_r.png" width="17" height="96" /></td>
      </tr>
    </table><br>
    <table align="center" width="80%" border="0" cellspacing="0">
		<tr>
			<td width="100%" height="24px">
				<% if (iUserLev == 1) { %>
					<input type="button" value="新建" onclick="document.location='RegNewUser.jsp'">
					<input type="button" value="删除" onclick="doDel()">
					<input type="button" value="全选" onclick="selectAll('id')">
					<input type="button" value="反选" onclick="reverseSelect('id');">
				<% } %>
				<input type="text" name="SearchKey" onkeydown="if(event.keyCode == 13) document.getElementById('DoSeaBut').click();" value="<%= request.getParameter("sk") == null ? "" : new String(request.getParameter("sk").getBytes("iso-8859-1"), "gbk") %>" size=20>
				<input type="button" value="搜索" onclick="GoSearchData()" id="DoSeaBut">
				<% if (request.getParameter("sk") != null && !request.getParameter("sk").equals("")) { %>
					<input type="button" value="全部" onclick="GetAllData()">
				<% } %>
			</td>
		</tr>
	</table>
    <table align="center" width="80%" border="1" bordercolor="gray" cellpadding="0" style="font-size:10pt;border:2 solid #494949;border-collapse:collapse;">
    	<% if (iUserLev == 1) { %>
    	<colgroup width="5%" align="center"></colgroup>
    	<% } %>
    	<colgroup width="5%" align="center"></colgroup>
    	<colgroup width="5%" align="center"></colgroup>
    	<colgroup width="25%" align="left"></colgroup>
    	<colgroup width="20%" align="left"></colgroup>
    	<colgroup width="25%" align="center"></colgroup>
    	<colgroup width="15%" align="center"></colgroup>
		<tr style="font-weight:bold;background-color:yellow;">
			<% if (iUserLev == 1) { %>
    		<td></td>
    		<% } %>
			<td>活动</td>
			<td>教师</td>
			<td align="center">用户名称</td>
			<td align="center">登录名称</td>
			<td>头像</td>
			<td>操作</td>
		</tr>
		<logic:iterate id="oRowData" name="ViewDataInfo">
			<tr>
				<% Object[] oDataInfo = (Object[]) oRowData; %>
				<% if (iUserLev == 1) { %>
				<td><% if (!oDataInfo[4].equals("admin")){ %><input type=checkbox name="id" value="<%=oDataInfo[0] %>"><% } %>
				</td>
				<% } %>
				<td>
					<%= oDataInfo[1].toString().equals("1") ? "<img src=\"images/right.gif\">" : "" %>
				</td>
				<td>
					<%= oDataInfo[2].toString().equals("1") ? "<img src=\"images/right.gif\">" : "" %>
				</td>
				<td><%=oDataInfo[3] %></td>
				<td><%=oDataInfo[4] %></td>
				<td>
					<% if (oDataInfo[5] != null && !oDataInfo[5].equals("")){ %><img width="32" height="32" src="<%=oDataInfo[5] %>"><% } %>
				</td>
				<td>
					<a href="ReadUserInfo.jsp?id=<%=oDataInfo[0] %>">查看</a>
					<% if(iUserLev == 1){ %>
						<a href="ModUserInfo.jsp?id=<%=oDataInfo[0] %>">编辑</a>
						<% if (!oDataInfo[4].equals("admin")) { %>
						<a href=# onclick="doDel(<%=oDataInfo[0] %>)">删除</a>
						<% } %>
					<% } else if (oTUser !=null && oTUser.getLoginId().equals(oDataInfo[4])){ %>
						<a href="ModUserInfo.jsp?id=<%=oDataInfo[0] %>">编辑</a>
					<% } %>
				</td>
			</tr>
		</logic:iterate>
	</table><br>
	<table align="center" width="80%" border="0" cellspacing="0" style="font-size:10pt;color:blue;text-align:center">
		<tr>
			<td width="50%">
				页次：
				<bean:write name="ViewPageInfo" property="curpagenum" />
				/
				<bean:write name="ViewPageInfo" property="allpagecou" />
				页&nbsp;每页：
				<bean:write name="ViewPageInfo" property="perpagecou" />
				&nbsp;记录数：
				<bean:write name="ViewPageInfo" property="alldatacou" />
			</td>
			<td width="50%">
				<a href="" id="_FirstPage"><img id="_FirstPageImg" src="images/PageFirst.gif" alt="首页" title="首页" width="12"
					height="8" border="0"></a> 
				<a href="" id="_PrevPage"><img id="_PrevPageImg" src="images/PagePrev.gif" alt="上一页" width="12" height="8"
						border="0"></a> 
				<span id="_DivPageNav">Loading...</span>
				<a href="" id="_NextPage"><img id="_NextPageImg" src="images/PageNext.gif" alt="下一页" width="12" height="8"
						border="0"></a> 
				<a href="" id="_LastPage"><img id="_LastPageImg" src="images/PageLast.gif" alt="尾页" title="尾页" width="12"
						height="8" border="0"></a>
			</td>
		</tr>
	</table>
	<form action="delSelUser.do" method="post">
	<input type="hidden" name="ids" />
	</form>
  </body>
</html:html>
