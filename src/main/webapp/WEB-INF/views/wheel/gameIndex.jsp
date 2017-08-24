
<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>活动管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="${ctxStatic}/images/vicmob_logo.ico" />
<link rel="stylesheet" href="${ctxStatic}/page/css/layui.css">
<link rel="stylesheet"
	href="${ctxStatic}/page/layer/skin/default/layer.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/page/fontAli/iconfont.css">


<link href="${ctxStatic}/bootstrap/css/bootstrap.min.css?v=20170218"
	rel="stylesheet">
<link href="${ctxStatic}/bootstrap/css/timepicker.css"
	rel="stylesheet">
<link href="${ctxStatic}/page/css/common.css?v=20170218"
	rel="stylesheet">

<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap/js/bootstrap.min.js?v=20170218"></script>
	<script type="text/javascript"
	src="${ctxStatic}/bootstrap/js/timepicker.js"></script>
<script src="${ctxStatic}/page/layui.js"></script>
<script src="${ctxStatic}/page/layer/layer.js"></script>
<script src="${ctxStatic}/page/lay/modules/element.js"></script>

</head>
<style>
	.right-content{
	     width: 1010px; 
   		 margin-left: 200px;
   	 	 margin-top: -200px;
	}
	.footer{
		text-align: center;
	}
.display-center {
 	padding-top: 15px !important;
}

</style>
<body>
	<div class="loader" style="display: none">
		<div class="la-ball-clip-rotate">
			<div></div>
		</div>
	</div>
	<div class="head">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand"> <img
						src="${ctxStatic}/images/vicmob.png" class="pull-left"
						width="170px" height="50px"> <!-- <span class="version">1.2.6</span> -->
					</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-left">
						<!-- <li class="active"><a href="" >公众号</a></li>
				<li><a href="" >系统管理</a></li> -->
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="javascript:;"
							class="dropdown-toggle" data-toggle="dropdown"><i
								class="wi wi-user color-gray"></i>${loginname} <span
								class="caret"></span></a>
							<ul class="dropdown-menu color-gray" >
								<!-- <li><a href=""><i class="wi wi-account color-gray"></i> 我的账号</a></li>
						<li class="divider"></li> -->
								<li><a href="${webUrl}/a/resetPassword"><i
										class="wi wi-update color-gray"></i> 修改密码</a></li>
								<li class="divider"></li>
								<li><a href="${webUrl}/a/logout"><i
										class="fa fa-sign-out color-gray"></i> 退出系统</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="main">
		<div class="container">
			<div class="panel panel-content" style="min-height: 825px">
				<div class="content-head panel-heading">
					<%-- <img src="${headImg }" class="head-logo"> <span
						class="font-lg">${nickname}</span> --%>
						<ol class="breadcrumb we7-breadcrumb" >
						<%-- ${webUrl}/a/sys/mess/index --%>
			<a href="javascript:document.formHomeIndex.submit();"><i class="wi wi-back-circle"></i> </a>
			<li><a href="javascript:document.formHomeIndex.submit();" onclick="">返回管理平台</a></li>
		</ol>
				</div>
				<div class="container-body">
					<div class="left-menu">
						<div class="panel panel-menu">
							<ul class="list-group">
								<li class="list-group-item showgame"><a
									href="javascript:void(0)"
									onclick="pushState(1);"
									class="text-over"> <i class="wi wi-reply"></i>应用入口
								</a></li>
							</ul>
						</div>
					
						<div class="panel panel-menu">
							<div class="panel-heading">
								活动管理<span class="wi wi-appsetting pull-right setting"></span>
							</div>
							<ul class="list-group">
								<li class="list-group-item eggAdministration"><a
									href="javascript:void(0)"
									onclick="pushState(2); " class="text-over"> <i
										class="wi wi-parameter-stting"></i>砸金蛋活动管理
								</a></li>
							</ul>
							<ul class="list-group">
								<li class="list-group-item wheelAdministration"><a
									href="javascript:void(0)"
									onclick="ajaxWheel();pushState(2)" class="text-over"> <i
										class="wi wi-parameter-stting"></i>大转盘活动管理
								</a></li>
							</ul>
						</div>
					</div>
					<div class="right-content"></div>
				</div>
			</div>
		</div>
	</div>
<div class="footer">
	<br> <span>www.vicmob.com</span>
</div>
<form name="formHomeIndex" action="${path }/VicmobWeb/a/sys/authorization/management" method="post">
     <input type='hidden' name="acid"  value="${unicalId}"/>  
     <input type='hidden' name="nickname"  value="${nickname}"/>  
     <input type='hidden' name="superAuthority"  value="1"/>  
   </form>
</body>
<script type="text/javascript">
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	} else {
		return null;
	}
}

var wheelpage = getUrlParam('wheelpage');
if(wheelpage == null || wheelpage == "null"){
	wheelpage = 1;
}

function pushState(num) {
	var thispage = getUrlParam('wheelpage');
	if(thispage == num){
		return;
	}
	if (num == '1') {
		history.pushState({
			page : 1
		}, null, '?wheelpage=1');
	} else if (num == '2') {
		history.pushState({
			page : 2
		}, null, '?wheelpage=2');	
	} else if (num == '3') {
		history.pushState({
			page : 3
		}, null, '?wheelpage=3');
	} else if (num == '4') {
		history.pushState({
			page : 4
		}, null, '?wheelpage=4');
	} else if (num == '5') {
		history.pushState({
			page : 5
		}, null, '?wheelpage=5');
	} else if (num == '6') {
		history.pushState({
			page : 6
		}, null, '?wheelpage=6');
	}
}
//页面刷新的时候操作
pushState(wheelpage);
if(wheelpage == 2){
	ajaxWheel();
}else if(wheelpage == 3){
	ajaxGameUser();
}else if(wheelpage == 4){
	skipAddExchangeUser();
}else if(wheelpage == 5){
	skipAddWhiteUser();
}else if(wheelpage == 6){
	skipWhiteUser();
}else if(wheelpage == 7){
	
}else if(wheelpage == 8){
	
}

//页面刷新的时候操作
//浏览器前进后退操作
window.addEventListener('popstate', function(event) {
	var wheelpage = getUrlParam('wheelpage');
	if (wheelpage == null || wheelpage == "null") {
		window.history.back();
	} else {
		pushState(wheelpage);
		/* if(page == 1 || page == 2){
			$('.list-group-item').eq(page-1).trigger("click");
			window.location.reload(); */
		if(wheelpage == 2){
			ajaxWheel();
		}else if(wheelpage == 2){
			ajaxWheel();
		}else if(wheelpage == 3){
			ajaxGameUser();
		}else if(wheelpage == 4){
			skipAddExchangeUser();
		}else if(wheelpage == 5){
			skipAddWhiteUser();
		}else if(wheelpage == 6){
			skipWhiteUser();
		}
	}
});
//浏览器前进后退操作
/* 跳转大转盘活动页面 */
function ajaxWheel(){
	$.cookie('search', '',{ path: "/"});
	$.cookie('infoId', '',{ path: "/"});
	$.ajax({
		url : '${ctx}/wheel/info/wheelAdministration',
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转普通人员  */
function ajaxGameUser(){
	$.cookie('search', '',{ path: "/"});
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/gameUserList',
		data : {infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转添加核销人员页面 */
function skipAddExchangeUser(){
	$.ajax({
		url : '${ctx}/wheel/user/addExchangeUser',
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转添加白名单人员页面 */
function skipAddWhiteUser(){
	$.ajax({
		url : '${ctx}/wheel/user/addWhiteUser',
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 选为白名单人员 */
function skipWhiteUser() {
		var infoId = $.cookie('infoId');
		var headImgUrl = $.cookie('headImgUrl');
		var nickName = $.cookie('nickName');
		var fansId = $.cookie('fansId');
		$.ajax({
			url : '${ctx}/wheel/user/addWhUser',
			data : {
				fansId : fansId,
				headImgUrl : headImgUrl,
				nickName : nickName,
				infoId : infoId
			},
			type : 'post',
			timeout : 0,
			success : function(result) {
				$('.right-content').html(result);
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请刷新页面重试')
			}
		});
}


</script>
<!-- <script>

layui.use('element', function() {
	var element = layui.element();
});

$('.list-group-item').click(function(){
	$('.list-group-item').removeClass('active');
	$(this).addClass('active');
	var name = $(this)[0].className;
	var name = name.substring(name.indexOf(" ")+1,name.indexOf("active")-1);
	if(name.trim() != ""){
		ajaxSkip(name);
	}
});

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	} else {
		return null;
	}
}

var page = getUrlParam('page');
if(page == null || page == "null"){
	page = 1;
}

//页面刷新的时候操作

pushState(page);
if(page < 3){
	$('.list-group-item').eq(page-1).trigger("click");
}else{
	$('.list-group-item').eq(1).addClass('active')
	var title = $.cookie('title')
	if(page == 3){
		ajaxSkip('userList',title);
	}else if(page == 4){
		ajaxSkip('recordList',title);
	}else if(page == 1){
		ajaxSkip('showgame',title);
	}else if(page == 5){
		ajaxSkip('addGame');
	}else if(page == 6){
		ajaxSkip('editGame');
	}else if(page == 7){
		ajaxSkip('editGamekey');
	}
}
//页面刷新的时候操作


function pushState(num) {
	var thispage = getUrlParam('page');
	if(thispage == num){
		return;
	}
	if (num == '1') {
		history.pushState({
			page : 1
		}, null, '?page=1');
	
		
	} else if (num == '2') {
		history.pushState({
			page : 1
		}, null, '?page=2');
	
	}  else if (num == '3') {
		history.pushState({
			page : 1
		}, null, '?page=3');
	} else if (num == '4') {
		history.pushState({
			page : 1
		}, null, '?page=4');
	} else if (num == '5') {
		history.pushState({
			page : 1
		}, null, '?page=5');
	} else if (num == '6') {
		history.pushState({
			page : 1
		}, null, '?page=6');
	} else if (num == '7') {
		history.pushState({
			page : 1
		}, null, '?page=7');
	} 
}

//浏览器前进后退操作
window.addEventListener('popstate', function(event) {
	var page = getUrlParam('page');
	if (page == null || page == "null") {
		window.history.back();
	} else {
		pushState(page);
		if(page == 1 || page == 2){
			$('.list-group-item').eq(page-1).trigger("click");
		}else {
			$('.list-group-item').eq(1).addClass('active')
			var title = $.cookie('title')
			if(page == 3){
				ajaxSkip('userList',title);
			}else if(page == 4){
				ajaxSkip('recordList',title);
			}else if(page == 1){
				ajaxSkip('showgame',title);
			}else if(page == 5){
				ajaxSkip('addGame',title);
			}else if(page == 6){
				ajaxSkip('editGame',title);
			}else if(page == 7){
				ajaxSkip('editGamekey',title);
			}
		}
	}
});
//浏览器前进后退操作


function ajaxSkip(name,title,pageNo,pageSize){
	$.cookie('title', title,{ path: "/"});
	var search = $('#search-input').val();
	$.cookie('search', '',{ path: "/"});
	$.cookie('keyword', '',{ path: "/"});
	if( null!=search && ""!=search ){
		$.cookie('search', search,{ path: "/"});
	}
	var messageReplyId= $.cookie('messageReplyId');
	var keyWork= $.cookie('keyWork');
	var url = '${ctx}/game/'+name;
	$.ajax(url, {
		data : {
			title : title,
			search : search,
			PageNo : pageNo,
			PageSize : pageSize,
			messageReplyId : messageReplyId,
			keyWork : keyWork
			},
		type : 'post',
		timeout : 0, 
		success : function(result) {
/* 			console.log(result.substring(14,33).indexOf(str));
			console.log(result.substring(14,33).trim()) */
			var str = "<!DOCTYPE html>";
			if(result.substring(10,35).trim().indexOf(str) > -1){
				window.location.reload();
			}else{
				$('.right-content').html(result);	
				if(name=='userList'){
						$('#search-input,.form-control userList').val(search);
				}
				if(name=='recordList'){
						$('#search-input,.form-control recordList').val(search);
				}
			}
		},
		error : function(xhr, type, errorThrown) {
			layer.alert('网络异常，请检查网络...',function(){window.location.reload();})			
		}
	});
}
function ajaxSkip2(name,title,pageNo,pageSize){
	var search= $.cookie('search');
	var url = '${ctx}/game/'+name;
	$.ajax(url, {
		data : {
			title : title,
			search : search,
			PageNo : pageNo,
			PageSize : pageSize
			},
		type : 'post',
		timeout : 0, 
		success : function(result) {
/* 			console.log(result.substring(14,33).indexOf(str));
			console.log(result.substring(14,33).trim()) */
			var str = "<!DOCTYPE html>";
			if(result.substring(10,35).trim().indexOf(str) > -1){
				window.location.reload();
			}else{
				$('.right-content').html(result);
				if(name=='userList'){
					$('#search-input,.form-control userList').val(search);
				}
				if(name=='recordList'){
					$('#search-input,.form-control recordList').val(search);
				}
			}
		},
		error : function(xhr, type, errorThrown) {
			layer.alert('网络异常，请检查网络...',function(){window.location.reload();})			
		}
	});
}
function ajaxSkip3(name,title,pageNo,pageSize){
	var search= '';
	$.cookie('title', title,{ path: "/"});
	var url = '${ctx}/game/'+name;
	$.ajax(url, {
		data : {
			title : title,
			search : search,
			PageNo : pageNo,
			PageSize : pageSize
			},
		type : 'post',
		timeout : 0, 
		success : function(result) {
/* 			console.log(result.substring(14,33).indexOf(str));
			console.log(result.substring(14,33).trim()) */
			var str = "<!DOCTYPE html>";
			if(result.substring(10,35).trim().indexOf(str) > -1){
				window.location.reload();
			}else{
				$('.right-content').html(result);
				if(name=='userList'){
					$('#search-input,.form-control userList').val(search);
				}
				if(name=='recordList'){
					$('#search-input,.form-control recordList').val(search);
				}
			}
		},
		error : function(xhr, type, errorThrown) {
			layer.alert('网络异常，请检查网络...',function(){window.location.reload();})			
		}
	});
}
</script>
 --></html>