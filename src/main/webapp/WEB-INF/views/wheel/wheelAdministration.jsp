<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<div class="breadcrumb we7-breadcrumb">
		<a href="javascript:void(0)" onclick="javascript:window.history.go(-1)"><i class="wi wi-back-circle"></i> </a>
		<li><a href="javascript:void(0)" onclick="javascript:window.history.go(-1)">返回上级</a></li>
</div>
<div class="main">
	<div class="pull-right" style="margin-right: 20px">
		<a href="javascript:void(0)"
			onclick="ajaxSkip('addGame');pushState(5);"
			class="btn btn-primary">+添加新活动</a>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">大转盘活动管理</a></li>
	</ul>


	<div class="panel panel-info">
		<div class="panel-heading">筛选</div>
		<div class="panel-body">
			<form class="form-horizontal form-search-Info" role="form"
				onsubmit="return false">
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">活动名称</label>
					<div class="col-sm-8 col-lg-9">
						<input class="form-control" name="activityTitle"
							id="activityTitle" type="text" placeholder="请输入活动名称" value="">
					</div>
					<div class=" col-xs-12 col-sm-2 col-lg-2">
						<button class="btn btn-default" type="button"
							onclick="searchInfo()">
							<i class="fa fa-search"></i> 搜索
						</button>
					</div>
				</div>
				<div class="form-group"></div>
			</form>
		</div>

	</div>


	<div class="panel panel-default">
		<div class="panel-heading panel-heading-count">共${count}条活动</div>
		<div class="table-responsive panel-body">

			<div style="padding: 15px;" class="layui-form">
				<form class="choseMuch">
					<table class="table table-hover">
						<thead class="navbar-inner">
							<input type="button" class="btn btn-danger" value="批量删除"	onclick="deleteMuch();" />
							<tr>
								<th style="width: 50px;">
									 <input type="checkbox" class="choseAll" lay-skin="primary">
									<div class="layui-unselect layui-form-checkbox"
										lay-skin="primary" onclick="chosesAll()">
										<i class="layui-icon"></i>
									</div>
								</th>

								<th style="width: 100px;">活动名称</th>
								<th style="width: 100px;">开始时间</th>
								<th style="width: 100px;">活动状态</th>

								<th style="width: 300px">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${info}" var="Info">
								<tr class="info-${Info.infoId}">
									<td class="with-checkbox" style="padding-top: 14px;">
									   <input
										type="checkbox" value="${Info.infoId}"
										lay-skin="primary"> <input type="hidden"
										value="${Info.unicalId}" lay-skin="primary"> <input
										type="hidden" value="${Info.keyWord}" lay-skin="primary">
										<input type="hidden" value="${Info.activityTitle}"
										lay-skin="primary">
									</td>
									<td class="display-center"
										style="width: 136px !important;">${Info.activityTitle}</td>
									<td class="display-center" style="width: 136px;">${Info.startTime}</td>
									<c:if test="${Info.status==0}">
									<td class="display-center" style="width: 136px;">未开始</td>
									</c:if>
									<c:if test="${Info.status==1}">
									<td class="display-center" style="width: 136px;">进行中</td>
									</c:if>
									<c:if test="${Info.status==2}">
									<td class="display-center" style="width: 136px;">已结束</td>
									</c:if>
									<td style="width: 318px; padding-right: 0px;"><a
										href="javascript:void(0)"
										onclick="ajaxUserAdmin('${Info.infoId}');pushState(3)"
										class="btn btn-default inUser" rel="tooltip"
										title="参加用户"> <i class="glyphicon glyphicon-user"></i>参加用户
									</a> <a href="javascript:void(0)"
										onclick="ajaxWheelRecords('${Info.infoId}');"
										class="btn  btn-default" rel="tooltip" title="记录查看"><i
											class="glyphicon glyphicon-list"></i>记录查看</a> <a
										class="btn btn-default" rel="tooltip"
										href="javascript:void(0)" title="编辑"
										onclick="editRuleInfo('${Info.keyWord}');pushState(7);"><i
											class="glyphicon glyphicon-edit"></i></a> <a
										class="btn  btn-default" rel="tooltip"
										href="javascript:void(0)"
										onclick="deleteInfo('${Info.infoId}','${Info.unicalId}','${Info.keyWord}','${Info.activityTitle}')"
										title="删除"><i class="glyphicon glyphicon-remove"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
					<c:if test="${count!=0}">
						<div>${page }  </div>
					</c:if>
			</div>

		</div>


	</div>
</div>
					
<script type="text/javascript">
/* 搜索活动信息 */	
function searchInfo() {
	var activityTitle = $('#activityTitle').val();
	$.ajax({
		url : '${ctx}/wheel/info/wheelAdministration',
		data : {activityTitle:activityTitle},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#activityTitle').val(activityTitle);
			$.cookie('search', activityTitle,{ path: "/"});
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 分页搜索  */
function page(pageNo,pageSize,obj){
	var activityTitle = $.cookie('search');
	$.ajax({
		url : '${ctx}/wheel/info/wheelAdministration',
		data : {activityTitle:activityTitle,
				pageNo:pageNo,
				pageSize:pageSize},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#activityTitle').val(activityTitle);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}	
/* 跳转用户界面  */
function ajaxUserAdmin(infoId){
	$.cookie('search', '',{ path: "/"});
	$.cookie('infoId', '',{ path: "/"});
	$.cookie('thisTab', '');
	$.ajax({
		url : '${ctx}/wheel/user/gameUserList',
		data : {infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$.cookie('infoId', infoId,{ path: "/"});
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 跳转中奖纪录 */
function ajaxWheelRecords(infoId){
	$.cookie('search', '',{ path: "/"});
	$.cookie('infoId', '',{ path: "/"});
	$.ajax({
		url : '${ctx}/wheel/record/wheelRecordsList',
		data : {infoId:infoId},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$.cookie('infoId', infoId,{ path: "/"});
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
</script>
<script type="text/javascript">
	layui.use('form', function() {
		var form = layui.form();
		form.render();
		$('.layui-form-checkbox').eq(0).attr('onclick', 'choseAll()');
	});

	function choseAll() {
		if ($('.layui-form-checkbox').eq(0).hasClass('layui-form-checked')) {
			for (var i = 0; i < $('.layui-form-checkbox').size(); i++) {
				if (i != 0) {
					if (!$('.layui-form-checkbox').eq(i).hasClass(
							'layui-form-checked')) {
						$('.layui-form-checkbox').eq(i).trigger('click');
					}
				}
			}
		} else {
			for (var i = 0; i < $('.layui-form-checkbox').size(); i++) {
				if (i != 0) {
					if ($('.layui-form-checkbox').eq(i).hasClass(
							'layui-form-checked')) {
						$('.layui-form-checkbox').eq(i).trigger('click');
					}
				}
			}
		}
	}

	function deleteMuch() {
		if ($('.layui-form-checked').size() < 1) {
			layer.alert("请选择需要删除的活动");
			return;
		} else if ($('.layui-form-checked').size() == 1) {
			if ($('.layui-form-checked').eq(0).next().val() == "undefined"
					|| $('.layui-form-checked').eq(0).next().val() == undefined) {
				layer.alert("请选择需要删除的活动");
				return;
			}
		}
		layer.confirm('确认删除？', {
			btn : [ '确定', '取消' ]
		// 按钮
		},
				function() {
					layer.closeAll('dialog');
					for (var i = 0; i < $('.layui-form-checked').size(); i++) {
						ajaxDeleteInfo($('.layui-form-checked').eq(i).prev()
								.val(), $('.layui-form-checked').eq(i).next()
								.val(), $('.layui-form-checked').eq(i).next()
								.next().val(), $('.layui-form-checked').eq(i)
								.next().next().next().val());
					}
				});
	}
</script>
<!-- <script>
	layui.use('form', function() {
		var form = layui.form();
		form.render();
		$('.layui-form-checkbox').eq(0).attr('onclick', 'choseAll()');
	});

	var count = $
	{
		count
	};
	countS(0);

	function countS(num) {
		count -= num;
		$('.panel-heading-count').text("共" + count + "条活动");
	}

	function deleteInfo(infoId, unicalId, keyWord, activityTitle) {
		layer.confirm('确认删除？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function() {
			layer.closeAll('dialog');
			ajaxDeleteInfo(infoId, unicalId, keyWord, activityTitle);
		});
	}

	function ajaxDeleteInfo(infoId, unicalId, keyWord, activityTitle) {
		// console.log(unicalId+"--------------------"+keyWord);

		$.ajax({
			url : '${ctx}/game/delete',
			data : {
				infoId : infoId,
				activityTitle : activityTitle
			},
			type : 'post',
			timeout : 0,
			success : function(result) {
				//console.log(result)
				if (result.substring(14, 30).trim() == "<!DOCTYPE html>") {
					window.location.reload();
				}
				if (result == 1) {
					$('.info-' + infoId).remove();
					countS(1);
				} else if (result == 0) {
					layer.msg('活动删除失败')
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请检查网络...')
			}
		});

		$.ajax({
			url : '${ctx}/game/deleteKeyWord',
			data : {
				unicalId : unicalId,
				keyWord : keyWord
			},
			type : 'post',
			timeout : 0,
			success : function(result) {
				//console.log(result)
				if (result == 1) {
				} else if (result == 0) {
					layer.msg('关键字删除失败')
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请检查网络...')
			}
		});
	}

	function editRuleInfo(keyWork) {
		$.cookie('keyWork', keyWork, {
			path : "/"
		});
		$.ajax({
			url : '${ctx}/game/gamepage/editGameByKeyWord',
			data : {
				keyWork : keyWork
			},
			type : 'post',
			timeout : 0,
			success : function(result) {
				var str = "<!DOCTYPE html>";
				if (result.substring(10, 35).trim().indexOf(str) > -1) {
					window.location.reload();
				} else {
					$('.right-content').html(result);
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.alert('网络异常，请检查网络...')
			}
		});
	}

	function searchInfo() {
		var search = $('#search-input,.form-control searchInfo').val();
		$.ajax({
			url : '${ctx}/game/searchInfo',
			data : $('.form-search-Info').serialize(),
			type : 'post',
			timeout : 0,
			success : function(result) {
				if (result.substring(14, 30).trim() == "<!DOCTYPE html>") {
					window.location.reload();
				} else {
					$('.right-content').html(result)
					$('#search-input,.form-control searchInfo').val(search);
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请检查网络...')
			}
		});
	}

	$('#search-input').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			//回车执行查询
			searchInfo();
		}
	});
</script> -->

</html>