<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>
<div class="breadcrumb we7-breadcrumb">
	<a href="javascript:void(0)" onclick="javascript:window.history.go(-1)"><i class="wi wi-back-circle"></i> </a>
	<li><a href="javascript:void(0)" onclick="javascript:window.history.go(-1)">返回上级</a></li>
</div>
<div class="main">
	<div class="main">
		<ul id="myTab" class="nav nav-tabs">
			<li class="user active" id="id-1"><a  id="a-1" href="#tab1" data-toggle="tab" onclick="changeButtonStatus1();">参加用户</a></li>
			<li class="user" id="id-2"><a id="a-2" href="#tab2"  data-toggle="tab" onclick="changeButtonStatus2();">核销人员</a></li>
			<li class="user" id="id-3"><a id="a-3" href="#tab3" data-toggle="tab" onclick="changeButtonStatus3();">白名单人员</a></li>
			<li class="user" id="id-4"><a id="a-4" href="#tab4" data-toggle="tab" onclick="changeButtonStatus4();">收货人信息</a></li>
			<div><input id="addExchangeUser" type="hidden"  value="添加核销人员" class="btn btn-primary" style = "float:right;" onclick="skipAddExchangeUser();pushState(4)"/></div>
			<div><input id="addWhiteUser" type="hidden"  value="添加白名单人员" class="btn btn-primary" style = "float:right;" onclick="skipAddWhiteUser();pushState(5)"/></div>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active" id="tab1">
				<p>
				<div class="panel panel-info">
					<div class="panel-heading">筛选</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form"
							onsubmit="return false">
							<div class="form-group">
								<label
									class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">昵称</label>
								<div class="col-sm-8 col-lg-9">
									<input class="form-control" name="nickName"
										id="gameUserNickname" type="text" value="" placeholder="请输入昵称">
								</div>
								<div class=" col-xs-12 col-sm-2 col-lg-2">
									<button class="btn btn-default" type="button"
										onclick="searchGameUser()">
										<i class="fa fa-search"></i> 搜索
									</button>
								</div>
							</div>
							<div class="form-group"></div>
						</form>
					</div>
				</div>
				<div class=" panel panel-default">
					<div class="panel-heading">
						<span>共${count1}人</span><span
							style="margin-left: 50px;">普通用户</span>
					</div>
					<div class="table-responsive panel-body">

						<div style="padding: 15px;">

							<table class="table table-hover">
								<thead class="navbar-inner">
									<tr>
										<th style="width: 70px">昵称</th>
										<th style="width: 70px">头像</th>
										<th style="width: 70px">参加时间</th>
									</tr>
									<c:forEach items="${gameUser}" var="gameUser">
									<tr>
										<td class="display-center" style="display: none"></td>
										<td class="display-center">${gameUser.nickName }</td>
										<td><img style="width: 33px; height: 33px;"
											src="${gameUser.headImgUrl }"></td>
										<td class="display-center">${gameUser.createTime }</td>
									</tr>
									</c:forEach>
								</thead>
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
							<div style="padding: 0 15px 0 15px;">
								<div class="row-fluid">
									<div class="span8 control-group">
										<button class="btn btn-default" type="button" onclick="outUserExcel()"><i class="icon-download-alt"></i>导出用户信息</button>
									</div>
								</div>

							</div>
						</div>
						<c:if test="${count1!=0}">
								<div>${page1 }  </div>
						</c:if>
					</div>
				</div>
		</p>
		</div>
		<div class="tab-pane" id="tab2">
		</div>
		<div class="tab-pane" id="tab3">
		</div>
		<div class="tab-pane" id="tab4">
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function changeButtonStatus1(){
	$('#addExchangeUser').attr("type","hidden");
	$('#addWhiteUser').attr("type","hidden");
	ajaxGameUser();
}

function changeButtonStatus2(){
	$('#addExchangeUser').attr("type","button");
	$('#addWhiteUser').attr("type","hidden");
	ajaxExchangeUser();
}
function changeButtonStatus3(){
	$('#addExchangeUser').attr("type","hidden");
	$('#addWhiteUser').attr("type","button");
	ajaxWhiteUser();
}
function changeButtonStatus4(){
	$('#addExchangeUser').attr("type","hidden");
	$('#addWhiteUser').attr("type","hidden");
	ajaxConsignee();
}
/* 搜索普通用户 */	
function searchGameUser() {
	var nickName = $('#gameUserNickname').val();
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/gameUserList',
		data : {nickName:nickName,
				infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#gameUserNickname').val(nickName);
			$.cookie('search', nickName,{ path: "/"});
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 分页搜索普通用户  */
function page(pageNo,pageSize,obj){
	var nickName = $.cookie('search');
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/gameUserList',
		data : {
				infoId:infoId,
				nickName:nickName,
				pageNo:pageNo,
				pageSize:pageSize},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#gameUserNickname').val(nickName);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转核销人员界面  */
function ajaxExchangeUser(){
	$.cookie('search', '',{ path: "/"});
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/exchangeUserList',
		data : {infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('#tab2').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转白名单人员界面  */
function ajaxWhiteUser(){
	$.cookie('search', '',{ path: "/"});
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/whiteUserList',
		data : {infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('#tab3').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转收货人信息界面  */
function ajaxConsignee(){
	$.cookie('search', '',{ path: "/"});
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/consigneeList',
		data : {infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('#tab4').html(result);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 导出Excel */
function outUserExcel(){
	var infoId = $.cookie('infoId');
	window.location.href='${ctx}/wheel/user/outExcel?infoId='+infoId;
}
/* 选项卡。。。。。。刷新 */
var tab = $.cookie('thisTab');
console.log(tab)
$('#' + tab).trigger("click");
$('.user').click(function() {
	$.cookie('thisTab', $(this).context.id);
});
if(tab == 'id-2'){
	$('#a-2').trigger("click");
	$('#a-2').click(function() {
		ajaxExchangeUser();
	});
}
if(tab == 'id-3'){
	$('#a-3').trigger("click");
	$('#a-3').click(function() {
		ajaxWhiteUser();
	});
}
if(tab == 'id-4'){
	$('#a-4').trigger("click");
	$('#a-4').click(function() {
		ajaxConsignee();
	});
}
</script>

<!-- <script>
	var gameUserCount = $
	{
		gameUserCount
	};
	var exchangeUserCount = $
	{
		exchangeUserCount
	};
	var title = '${title}';
	countS(0, 0);

	function countS(numG, numE) {
		gameUserCount -= numG;
		exchangeUserCount -= numE;
		$('.gameUserCount').text("共" + gameUserCount + "人");
		$('.exchangeUserCount').text("共" + exchangeUserCount + "人");
	}

	function choseExchangeUser(obj) {
		var openId = $(obj).parent().prevAll().eq(3).text();
		var unicalId = $(obj).parent().prevAll().eq(3).children().val();
		$.ajax({
			url : '${ctx}/game/user/addExUser',
			data : {
				openId : openId,
				activityTitle : title,
				unicalid : unicalId,
			},
			type : 'post',
			dataType : 'json',
			timeout : 0,
			success : function(result) {
				//console.log(result)
				if (result.success) {
					pageAddElement(result.content);
					layer.alert(result.message, function() {
						window.location.reload();
					});
				} else {
					layer.alert(result.message);
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请刷新页面重试')
			}
		});
	}

	function pageAddElement(exchangeUser) {
		//console.log(exchangeUser);
		var str = '<tr>'
				+ '<td class="display-center" >'
				+ exchangeUser.nickName
				+ '</td>'
				+ '<td><img style="width:33px; height: 33px;" src="'+exchangeUser.headImgUrl+'"></td>'
				+ '<td class="display-center" ></td>'
				+ '<td style="width: 300px">'
				+ '	<a href="javascript:void(0)" onclick="deleteExchangeUser(\''
				+ exchangeUser.openId
				+ '\',\''
				+ exchangeUser.unicalId
				+ '\',this)"'
				+ '	class="btn  btn-default inUser" rel="tooltip" title="取消核销资格">'
				+ '	<i class="glyphicon glyphicon-user"></i>取消核销资格</a>'
				+ '</td>' + '</tr>';
		$('.exchangeUser-tr').after(str);
	}

	function deleteExchangeUser(openId, unicalId, obj) {
		$.ajax({
			url : '${ctx}/game/user/deleteExchangeUser',
			data : {
				openId : openId,
				unicalId : unicalId,
				activityTitle : title,
			},
			type : 'post',
			dataType : 'json',
			timeout : 0,
			success : function(result) {
				if (result == 1) {
					$(obj).parent().parent().remove();
					window.location.reload();
				} else {
					layer.alert('删除失败，请刷新页面重试');
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请刷新页面重试')
			}
		});
	}

	var openIdE = null;
	var activityTitleE = null;
	var unicalIdE = null;
	function addWhiteUser(openId, activityTitle, unicalId) {
		openIdE = openId;
		activityTitleE = activityTitle;
		unicalIdE = unicalId;
		layer.open({
			type : 2,
			title : '奖品信息',
			area : [ '800px', '600px' ],
			fixed : false, // 不固定
			maxmin : true,
			content : '../../webpage/modules/game/PrizeList.jsp'
		});
	}

	$('#search-input').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			//回车执行查询
			ajaxSkip('userList', '${title}');
			pushState(3);
		}
	});

	function page(pageNo, pageSize, obj) {
		/* console.log(pageNo)
		console.log(pageSize) */
		ajaxSkip2('userList', '${title}', pageNo, pageSize);
		pushState(3);
	}
</script> -->

</html>
