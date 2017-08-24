<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>
<div class="breadcrumb we7-breadcrumb">
	<a href="javascript:void(0)" onclick="javascript:window.history.go(-1)"><i class="wi wi-back-circle"></i> </a>
	<li><a href="javascript:void(0)" onclick="javascript:window.history.go(-1)">返回上级</a></li>
</div>
<div class="main">
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">选为白名单</a></li>
	</ul>

	<div id="myTabContent" class="tab-content">
		<div class="tab-pane active" id="tab1">
			<p>
			<div class="panel panel-info">
				<div class="panel-heading">被选人员：${nickName}</div>
				<div class="form-group">
				<div class="panel-body">
					奖品名称:
					<select id="selectedPrize">
					<c:forEach items="${list }" var="list">
						<option value="${list.prizeId }">${list.prizeName }</option>
					</c:forEach>
					</select>
					<!-- 数量:
					<input type="text" value=""  class="form-control"  maxlength="8" style="width:120px;position: relative;top:-34px;left:220px"/> -->
					<input type="button"  value="添加" onclick="addPrize(this);" class="btn btn-primary" style="margin-left: 200px">
				</div>
				</div>
			</div>
			<div class=" panel panel-default">
				<div class="panel-heading">
					<span>白名单奖品</span>
				</div>
				<form id="whiteUser">
				<div class="table-responsive panel-body">

					<div style="padding: 15px;">

						<table class="table table-hover">
							<thead class="navbar-inner">
								<tr>
									<th width="100px">奖品级别</th>
	                                <th width="100px">奖品名称</th>
	                                <th width="100px">奖品数量</th>
	                                <th width="100px">类型</th>
	                                <th width="100px">积分</th>
	                                <th width="80px">操作</th>
								</tr>
							</thead>
							<tbody class="prize-items">
							</tbody>
						</table>
					</div>
				</div>
				<div>
					<input type="hidden" name="fansId" value="${fansId}"/>
					<input type="hidden" name="headImgUrl" value="${headImgUrl}"/>
					<input type="hidden" name="nickName" value="${nickName}"/>
					<input type="hidden" name="infoId" value="${infoId}"/>
				</div>
				<div class="form-group">
					<input	type="button"  value="提交"	class="btn btn-primary btn-submit" onclick="summitWhiteUser();" style="margin-left: 50px"/>
						</div>
						</form>
					</div>
			</p>
		</div>
	</div>
</div>
<script type="text/javascript">
/*添加奖品*/
var index = 0;
function addPrize() {
	var prizeId = $('#selectedPrize').val();	
	$.ajax({
		url : '${ctx}/wheel/prize/findPrizeById',
		data : {
			prizeId : prizeId
		},
		type : 'post',
		timeout : 0,
		success : function(result) {
			var prizeTypeName = '';
			if(1==result.prizeType){
				prizeTypeName='实物';
			}else if(2==result.prizeType){
				prizeTypeName='积分';
			}
			var html = "<tr>";
			html += '<td hidden="hidden"><input type="text" class="form-control" name="prizeList['
				 + index + '].prizeId"  value="' + result.prizeId + '"/></td>';
			html += '<td hidden="hidden"><input type="text" class="form-control" name="prizeList['
				 + index + '].prizeType"  value="' + result.prizeType + '"/></td>';
			html += '<td style="text-align:center;vertical-align:middle;"><input id="prizeLevel" name="prizeList['
			     +index
			     +'].prizeLevel"  class="form-control" style="padding-left:9px;" value='+result.prizeLevel+' readOnly="readOnly"/></td>';
			html += '<td style="text-align:center;vertical-align:middle;"><input type="text" class="form-control" name="prizeList['
				 +index
				 +'].prizeName"  value='+result.prizeName+' id="priName" maxlength="7"  readOnly="readOnly"/></td>';
			html += '<td style="text-align:center;vertical-align:middle;"><input type="text" class="form-control" name="prizeList['
				 +index
				 +'].prizeCount"  value="1" id="prizeCount" maxlength="8" onchange="checkPrizeCount(this,'+result.remainingCount+')"/></td>';
			html += '<td style="text-align:center;vertical-align:middle;"><select name="prizeList['
		         +index
	        	 +'].prizeType"  class="form-control" id="priType" disabled="disabled"><option value='+result.prizeType+'>'+prizeTypeName+'</option></select></td>';
			html += '<td style="text-align:center;vertical-align:middle;"><input type="text" class="form-control" name="prizeList['
				+index
				+'].points" value='+result.points+'  id="prizePoint"  maxlength="8" readOnly="readOnly"/></td>';
				html += '<td><a class="btn  btn-default" rel="tooltip" href="javascript:void(0)" onclick="removePrize(this)" title="删除"><i class="glyphicon glyphicon-remove"></i></a></td>';		
			html += "</tr>";
			$(".prize-items").append(html);
			index++;
		},
		error : function(xhr, type, errorThrown) {
			layer.msg('网络异常，请刷新页面重试')
		}
	});	
}	
/* 取消奖品 */
function removePrize(obj) {
	$(obj).parent().parent().remove();
}
/* 白名单奖品数量不能大于奖品总数 */
function checkPrizeCount(obj,remainingCount){
	 var reg =/^[1-9]\d*$/;    
     var num =$(obj).val();
     if(!reg.test(num)){
        layer.alert("奖品数必须正整数！");
        $(obj).val("1");
     }
     if(num>remainingCount){
        layer.alert("奖品数不能大于活动奖品总数！");
        $(obj).val("1");
     }
} 
/* 提交白名单 */
function summitWhiteUser(){
	/* 奖品级别不能重复 */
	var flag = true;
	var arr = new Array();
	$(".prize-items tr input[id='prizeLevel']").each(function(i,n) {
		arr.push(n.value);			
	});
	var sort = arr.sort();
	console.log(sort)
	for(var i=0;i<sort.length;i++){
		 if(arr[i]==arr[i+1]){
			 layer.alert("奖品级别不能重复！");
			 flag = false;
		 }
	} 
	/* 奖品不能为空  */
	var x=$('.prize-items').html();
	if(x.trim() == ""){
		layer.alert("请添加奖品", { icon: 0 , closeBtn: 0 } )
		flag = false;;
	}
	/* 提交 */
	if(flag){
		$.ajax({
			url:'${ctx}/wheel/user/sumbitWhiteUser',
			data :$('#whiteUser').serialize(),
			type : 'post',
			timeout : 0, 
			success : function(result) {
				if(result){
					layer.alert("添加白名单成功",{closeBtn:0,icon:1},function(){window.history.go(-1);layer.close(layer.index);});
				}else{
					layer.alert("添加白名单失败");
				}
			},
			error : function(xhr, type, errorThrown) {
				layer.msg('网络异常，请刷新页面重试')				
			}
		});
	}
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

