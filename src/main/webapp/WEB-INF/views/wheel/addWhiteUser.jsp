<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>

<div class="breadcrumb we7-breadcrumb">
	<a href="javascript:void(0)" onclick="javascript:window.history.go(-1)"><i class="wi wi-back-circle"></i> </a>
	<li><a href="javascript:void(0)" onclick="javascript:window.history.go(-1)">返回上级</a></li>
</div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">添加白名单人员</a></li>
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
								<input class="form-control userList" name="nickName"
									id="addWhnickName" type="text" value="" placeholder="输入昵称点击搜索后才有人员列表">
							</div>
							<div class=" col-xs-12 col-sm-2 col-lg-2">
								<button class="btn btn-default" type="button"
									onclick="searchFans();">
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
					 <span>参加用户</span>
				</div>
				<div class="table-responsive panel-body">

					<div style="padding: 15px;">

						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th style="width: 70px">昵称</th>
									<th style="width: 70px">头像</th>
									<th style="width: 70px">操作</th>
								</tr>
								
								<c:forEach items="${list }" var="list">
									<tr>
										<td class="display-center" style="display: none">${list.fansId }<input type="hidden" value="${list.headImgUrl}"></td>
										<td class="display-center">${list.nickName }</td>
										<td><img style="width: 33px; height: 33px;"
											src="${list.headImgUrl }"></td>
									<td><a href="javascript:void(0)"
											onclick="chooseWhiteUser(this);pushState(6)"
											class="btn  btn-default inUser" rel="tooltip"
											title="确认发货"> <i class="glyphicon glyphicon-user"></i>选为白名单人员
									</a></td>
									</tr>
								</c:forEach>	
							</thead>
							<tbody>
								<tr>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		<div>${page }</div>
		</p>
	</div>
</div>
<script type="text/javascript">
/* 搜索fans表 */
function searchFans(){
	var nickName = $('#addWhnickName').val();
	$.ajax({
		url : '${ctx}/wheel/user/searchFansToaddWh',
		data : {nickName:nickName},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#addWhnickName').val(nickName);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 选为白名单人员 */
function chooseWhiteUser(obj) {
		var fansId = $(obj).parent().prevAll().eq(2).text();
		var headImgUrl = $(obj).parent().prevAll().eq(2).children().val();
		var nickName = $(obj).parent().prevAll().eq(1).text();
		$.cookie('fansId', fansId,{ path: "/"});
		$.cookie('headImgUrl', headImgUrl,{ path: "/"});
		$.cookie('nickName', nickName,{ path: "/"});
		var infoId = $.cookie('infoId');
		$.ajax({
			url : '${ctx}/wheel/user/checkWhUser',
			data : {
				fansId : fansId,
				infoId : infoId
			},
			type : 'post',
			timeout : 0,
			success : function(result) {
				if(result){
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
				}else{
					layer.msg('该用户已被选为白名单人员')
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请刷新页面重试')
			}
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
