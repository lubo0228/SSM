<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>
<p> <div class="panel panel-info">
	<div class="panel-heading">筛选</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form"
			onsubmit="return false">
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">昵称</label>
				<div class="col-sm-8 col-lg-9">
					<input class="form-control userList" name="nickName"
						id="whiteUserNickname" type="text" value="" placeholder="请输入昵称">
				</div>
				<div class=" col-xs-12 col-sm-2 col-lg-2">
					<button class="btn btn-default" type="button"
						onclick="searchWhiteUser()">
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
		<span>共${count3}人</span> <span
			style="margin-left: 50px;">白名单人员</span>
	</div>
	<div class="table-responsive panel-body">

		<div style="padding: 15px;">

			<table class="table table-hover">
				<thead class="navbar-inner">
					<tr>
						<th style="width: 110px">昵称</th>
						<th style="width: 110px">头像</th>
						<th style="width: 110px">参加时间</th>
						<th style="width: 330px">操作</th>
					</tr>
						<c:forEach items="${whiteUser}" var="whiteUser">
						<tr>
							<td class="display-center" style="display: none">${whiteUser.gameuserId }</td>
							<td class="display-center" style="display: none">${whiteUser.fansId }</td>
							<td class="display-center">${whiteUser.nickName }</td>
							<td><img style="width: 33px; height: 33px;"
								src="${whiteUser.headImgUrl }"></td>
							<td class="display-center">${whiteUser.createTime }</td>
							<td>
								<a href="javascript:void(0)"
								onclick="editWhiteUser(this)"
								class="btn  btn-default inUser" rel="tooltip"
									title="编辑"> <i class="glyphicon glyphicon-user"></i>编辑</a>
								<a href="javascript:void(0)"
								onclick="deleteWhiteUser(this)"
								class="btn  btn-default inUser" rel="tooltip"
								title="取消白名单"> <i class="glyphicon glyphicon-user"></i>取消白名单</a>
								</td>
						</tr>
						</c:forEach>
					</thead>
					<tbody>
						<tr>
						</tr>
					</tbody>
				</table>
			</div>
			<c:if test="${count3!=0}">
				<div>${page3 }  </div>
			</c:if>
		</div>
	</div></p>

<script type="text/javascript">
/* 搜索白名单 */	
function searchWhiteUser() {
	var nickName = $('#whiteUserNickname').val();
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/whiteUserList',
		data : {nickName:nickName,
				infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('#tab3').html(result);
			$('#whiteUserNickname').val(nickName);
			$.cookie('search', nickName,{ path: "/"});
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 分页搜索白名单   */
function page(pageNo,pageSize,obj){
	var nickName = $.cookie('search');
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/whiteUserList',
		data : {
				infoId:infoId,
				nickName:nickName,
				pageNo:pageNo,
				pageSize:pageSize},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('#tab3').html(result);
			$('#whiteUserNickname').val(nickName);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 取消白名单 */
function deleteWhiteUser(obj){
	var gameuserId = $(obj).parent().prevAll().eq(4).text();
	var fansId = $(obj).parent().prevAll().eq(3).text();
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/deleteWhiteUser',
		data : {gameuserId:gameuserId,
				fansId:fansId,
				infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			if(result){
				layer.msg('取消白名单人员成功');
				searchWhiteUser();
			}else{
				layer.msg('取消白名单人员失败');
			}
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 编辑白名单 */
function editWhiteUser(obj){
	var nickName = $(obj).parent().prevAll().eq(2).text();
	var fansId = $(obj).parent().prevAll().eq(3).text();
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/user/editWhiteUser',
		data : {fansId:fansId,
				infoId:infoId,
				nickName:nickName},
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
</script>
<!-- <script type="text/javascript">
function changeButtonStatus1(){
	$('#addExchangeUser').attr("type","hidden");
	$('#addWhiteUser').attr("type","hidden");
}

function changeButtonStatus2(){
	$('#addExchangeUser').attr("type","button");
	$('#addWhiteUser').attr("type","hidden");
}
function changeButtonStatus3(){
	$('#addExchangeUser').attr("type","hidden");
	$('#addWhiteUser').attr("type","button");
}
function changeButtonStatus4(){
	$('#addExchangeUser').attr("type","hidden");
	$('#addWhiteUser').attr("type","hidden");
}
/* 搜索普通用户 */	
function searchGameUser(pageNo,pageSize) {
	var nickName = $('#gameUserNickname').val();
	var infoId = $.cookie('infoId');
	if(infoId==""||infoId==null){
		infoId = $.cookie('infoId');
	}
	$.ajax({
		url : '${ctx}/wheel/gameUserList',
		data : {nickName:nickName,
				infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#gameUserNickname').val(nickName);
			$.cookie('search', nickName,{ path: "/"});
			$.cookie('infoId', infoId,{ path: "/"});
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
		url : '${ctx}/wheel/gameUserList',
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
</script> -->
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
