<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>

<div class="breadcrumb we7-breadcrumb">
		<a href="javascript:void(0)" onclick="javascript:window.history.go(-1)"><i class="wi wi-back-circle"></i> </a>
		<li><a href="javascript:void(0)" onclick="javascript:window.history.go(-1)">返回上级</a></li>
</div>

	<div class="main">
						
	<ul class="nav nav-tabs">
		<!-- <li><a href="javascript:void(0)"
			onclick="eggAdministrationClick();pushState(2);">大转盘活动管理</a></li> -->
		<li class="active"><a href="javascript:void(0)">中奖记录</a></li>
	</ul>


	<div class="panel panel-info">
		<div class="panel-heading">筛选</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" onsubmit="return false">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 control-label">奖品名称/昵称</label>
					<div class="col-sm-8 col-lg-8">
					<input class="form-control recordList" name="search" id="search"
							type="text" value="" placeholder="请输入奖品名称或昵称" >
					</div>
					<div class=" col-xs-12 col-sm-2 col-lg-1">
						<button class="btn btn-default" type="button"
							onclick="searchRecords()">
							<i class="fa fa-search"></i> 搜索
						</button>
					</div>
				</div>
				<div class="form-group"></div>
			</form>
		</div>

	</div>


	<div class="panel panel-default">
		<div class="panel-heading">共${count } 条中奖记录</div>
		<div class="table-responsive panel-body">

			<div style="padding: 15px;">
				<table class="table table-hover">
					<thead class="navbar-inner">
						<tr>
							<!-- 	<th width="260px">openId</th> -->
							<th>昵称</th>
							<th>中奖时间</th>
							<th>兑换时间</th>
							<th>奖品名称</th>
							<th>奖品等级</th>
							<th>奖品类型</th>
							<th>奖品状态</th>
							<th>积分</th>
							<!-- <th>余额</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${records }" var="records">
							<tr>
								<td class="display-center" style="display: none">${records.fansId }</td>
								<td class="display-center">${records.nickName }</td>
								<td class="display-center">${records.createTime }</td>
								<td class="display-center">${records.receiveTime }</td>
							
								<td class="display-center">${records.prizeName }</td>
								<td class="display-center">${records.prizeLevel }</td>
								<c:if test="${records.prizeType==1 }">
								<td class="display-center">实物</td>
								</c:if>
								<c:if test="${records.prizeType==2 }">
								<td class="display-center">积分</td>
								</c:if>
								<c:if test="${records.status==0 }">
								<td class="display-center">未核销</td>
								</c:if>
								<c:if test="${records.status==1 }">
								<td class="display-center">已核销</td>
								</c:if>
								<td class="display-center">${records.points }</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				<div style="padding: 0 15px 0 15px;">
					<div class="row-fluid">
						<div class="span8 control-group">
							<button class="btn btn-default" type="button" onclick="outRecordsExcel()"><i class="icon-download-alt"></i>导出中奖记录</button>
						</div>
					</div>

				</div>
			</div>
			<c:if test="${count!=0}">
				<div>${page }  </div>
			</c:if>
		</div>
	</div>
</div>
<script type="text/javascript">
/* 搜索活动信息 */	
function searchRecords() {
	var search = $('#search').val();
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/record/wheelRecordsList',
		data : {infoId:infoId,
				search:search},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#search').val(search);
			$.cookie('search', search,{ path: "/"});
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 分页搜索  */
function page(pageNo,pageSize,obj){
	var search = $.cookie('search');
	var infoId = $.cookie('infoId');
	$.ajax({
		url : '${ctx}/wheel/record/wheelRecordsList',
		data : {infoId:infoId,
				search:search,
				pageNo:pageNo,
				pageSize:pageSize},
		type : 'post',
		timeout : 0,
		success : function(result) {
			$('.right-content').html(result);
			$('#search').val(search);
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请检查网络...')
		}
	});
}
/* 导出Excel */
function outRecordsExcel(){
	var infoId = $.cookie('infoId');
	window.location.href='${ctx}/wheel/record/outExcel?infoId='+infoId;
}
</script>

<!-- <script>
	var title = '${title}';
	function eggAdministrationClick() {
		$('.wheelAdministration').trigger('click');
	}

	$('#search-input').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			//回车执行查询
			ajaxSkip('recordList', '${title}');
			pushState(4);
		}
	});

	function page(pageNo, pageSize, obj) {
		ajaxSkip2('recordList', '${title}', pageNo, pageSize);
		pushState(4);
	}
</script> -->



