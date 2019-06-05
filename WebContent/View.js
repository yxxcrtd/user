// 获取id对应的对象
function $(id) {
	return document.getElementById(id);
}

// 选择所有name=id的对象
function selectAll(name){
	if(!name)name="id";
	var obj=document.getElementsByName(name);
	var i=0;
	for(i=0;i<obj.length;i++){
		if(!obj[i].checked)	break;
	}
	if(i>=obj.length){
		for(i=0;i<obj.length;i++)
			obj[i].checked=false;	
	}else{
		for(i=0;i<obj.length;i++)
			obj[i].checked=true;	
	}
}
//反选
function reverseSelect(name){
	if(!name)name="id";
	var obj=document.getElementsByName(name);
	for(var i=0;i<obj.length;i++)
		obj[i].checked=!obj[i].checked;
}

//确认是否删除
//arguments[1] 提示删除的信息
//表单的id
function doDel(id){
	var msg="确认删除所选择的用户吗?";
	
	if(typeof(id) != 'undefined') {
		window.document.getElementById('ids').value = id;
		msg="确认删除用户 " + window.event.srcElement.parentElement.parentElement.cells[3].innerText + " 吗?";
	} else {
		selectAny();
	}
	
	if(window.document.getElementById('ids').value == ''){
		alert("您没有选择任何选项！");
		return false;
	}
	
	if(confirm(msg)){
		document.forms(0).submit();
	}
}
//有没有选择项目
function selectAny(){
	var obj=document.getElementsByName('id');
	var idlist='';
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked) idlist += obj[i].value + ',';
	}
	window.document.getElementById('ids').value = (idlist == '') ? '' : idlist.substr(0, idlist.length - 1);
}

//格式化页面导航栏
//@curpage 当前所在页数
//@totalpage 总共的页数
//@showpagecount 每页显示的页码个数
function initPageNav(curpage,totalpage,showpagecount){
	var beginPage=new Number((parseInt((curpage-1)/showpagecount))*showpagecount+1);
	var endPage=new Number((beginPage+showpagecount-1));
	if(totalpage<endPage)endPage=totalpage;
	
	var str=window.location.search;
	var url;
	if(!str){
		url=window.location.href + "?PageNum={0}";
	}else{
		/PageNum=(.*)&*/.exec(str);
		if(RegExp.$1){
			url=window.location.href.replace("PageNum=" + RegExp.$1,"PageNum={0}");
		}else{
			url=window.location.href + "&PageNum={0}";
		}
	}
	
	var navstr=new String();
	for(var i=beginPage;i<curpage;i++){
		navstr+="&nbsp;<span class=\"ViewPageText\"><a href=\"" + url.replace("{0}",i) + "\">" + i + "</a></span>";
	}
	navstr+="&nbsp;<span class=\"ViewPageText1\">" +curpage + "</span>";
	for(var i=curpage+1;i<=endPage;i++){
		navstr+="&nbsp;<span class=\"ViewPageText\"><a href=\"" + url.replace("{0}",i) + "\">" + i + "</a></span>";
	}
	navstr+="&nbsp;";
	$("_DivPageNav").innerHTML=navstr;
	$("_PrevPageImg").alt=$("_PrevPageImg").title="上" + showpagecount + "页";
	$("_NextPageImg").alt=$("_NextPageImg").title="下" + showpagecount + "页";

	
	var haveSet=new Array(4);
	for(var i=0;i<haveSet.length;i++){
		haveSet[i]=false;
	}
	if(curpage==1){
		$("_FirstPage").href="javascript:void(0);";
		$("_FirstPageImg").src="images/PageFirstGray.gif";
		$("_FirstPageImg").style.cursor="default";
		$("_PrevPage").href="javascript:void(0);";
		$("_PrevPageImg").src="images/PagePrevGray.gif";
		$("_PrevPageImg").style.cursor="default";
		haveSet[0]=haveSet[1]=true;
	}
	if(curpage!=1 && beginPage==1){
		$("_PrevPage").href="javascript:void(0);";
		$("_PrevPageImg").src="images/PagePrevGray.gif";
		$("_PrevPageImg").style.cursor="default";
		haveSet[1]=true;
	}
	if(curpage==totalpage){
		$("_LastPage").href="javascript:void(0);";
		$("_LastPageImg").src="images/PageLastGray.gif";
		$("_LastPageImg").style.cursor="default";
		$("_NextPage").href="javascript:void(0);";
		$("_NextPageImg").src="images/PageNextGray.gif";
		$("_NextPageImg").style.cursor="default";
		haveSet[2]=haveSet[3]=true;
	}
	
	if(curpage!=totalpage && endPage==totalpage){
		$("_NextPage").href="javascript:void(0);";
		$("_NextPageImg").src="images/PageNextGray.gif";
		$("_NextPageImg").style.cursor="default";
		haveSet[2]=true;
	}
	

	if(!haveSet[0]){
		$("_FirstPage").href=url.replace("{0}",1);
		$("_FirstPageImg").style.cursor="pointer";
	}
	if(!haveSet[1]){
		$("_PrevPage").href=url.replace("{0}",(beginPage-1));
		$("_PrevPage").style.cursor="pointer";
	}
	if(!haveSet[2]){
		$("_NextPage").href=url.replace("{0}",(endPage+1));
		$("_NextPage").style.cursor="pointer";
	}
	if(!haveSet[3]){
		$("_LastPage").href=url.replace("{0}",(totalpage));
		$("_LastPage").style.cursor="pointer";
	}
}
