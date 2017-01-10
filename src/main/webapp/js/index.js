$(function(){
	var enterRe = /^\d{4}-\d{1,2}-\d{1,2}$/;
	var phoneRe = /^\d{11}$/;
	
	$(".left-nav").find("li").eq(0).on('click', function(){
		getAll(1, 5);
	});
	
//	条件搜索按钮
	$("#search_btn").on('click', function(){
		var name = $("#search_name").val();
		var dept = $("#search_dept").val();
		var start = $("#search_starttime").val();
		var end = $("#search_endtime").val();
		
		getAll(1, 5, name, dept, start, end);
	});
	
//	添加按钮
	$("#add-form").on('submit', function(){
		var staff = [
		             $('#staff_name').val(),
		             $('#staff_dept').val(),
		             $('#staff_enter').val(),
		             $('#staff_gender').val(),
		             $('#staff_phone').val(),
		             $('#staff_address').val(),
		             $('#staff_graduate').val(),
		             $('#staff_major').val(),
		             $('#staff_degree').val()
		            ];
//		验证是否为空
		for(var i = 0, len = length; i < len; i++){
			if(staff[i] == ""){
				alert("信息不能为空！");
				return false;
			}
		}
//		验证电话
		if(!staff[4].match(phoneRe)){
			alert("电话格式不正确！");
			return false;
		}
//		验证时间
		if(!staff[2].match(enterRe)){
			alert("时间格式不正确！");
			return false;
		}
		return true;
	});
	
//	更新按钮
	$("#update-con").on('click', function(){
		if($(".staff_id").length != 0){
			var data = {
				"id": $('.staff_id').val(),
				"name": $('.staff_name').val(),
				"dept": $('.staff_dept').val(),
				"enter": $('.staff_enter').val(),
				"gender": $('.staff_gender').val(),
				"phone": $('.staff_phone').val(),
				"address": $('.staff_address').val(),
				"graduate": $('.staff_graduate').val(),
				"major": $('.staff_major').val(),
				"degree": $('.staff_degree').val()
			};
			var staff = [
			             data["name"],
			             data["dept"],
			             data["enter"],
			             data["gender"],
			             data["phone"],
			             data["address"],
			             data["graduate"],
			             data["major"],
			             data["degree"]
			            ];
//			验证是否为空
			for(var i = 0, len = length; i < len; i++){
				if(staff[i] == ""){
					alert("信息不能为空！");
					return false;
				}
			}
//			验证电话
			if(!staff[4].match(phoneRe)){
				alert("电话格式不正确！");
				return false;
			}
//			验证时间
			if(!staff[2].match(enterRe)){
				alert("时间格式不正确！");
				return false;
			}
			if(data['id']){
				$.ajax({
					type: 'post',
					url: '/StaffInfo/update',
					data: data,
					dataType: 'json',
					success: function(data){
						if(data){
							alert("更新成功");
							window.location = "index.html";
						}
					},
					error: function(data){
						console.log(data)
					}
				})
			}
			
		}
	});

//	全选按钮
	$("#select-all").on('change', function(){
		if($("#select-all").get(0).checked){
			$.each($("#charge tbody").find("input[type=checkbox]"), function(i){
				this.checked = true;
			});
		}else{
			$.each($("#charge tbody").find("input[type=checkbox]"), function(i){
				this.checked = false;
			});
		}
	});

//	删除按钮
	$("#delete").on('click', function(){
		var ids = "";
		$.each($("#charge tbody").find("input[type=checkbox]"), function(i){
			if(this.checked == true){
				ids += $(this).attr("data-index") + " ";
			}
		});
		$.ajax({
			type: 'post',
			url: '/StaffInfo/delete',
			data: {"ids": ids.trim()},
			dataType: 'json',
			success: function(data){
				if(data){
					alert("删除成功");
					window.location = "index.html";
				}
			},
			error: function(data){
				//console.log(data)
			}
		})
	});

//	修改按钮
	$("#change").on('click', function(){
		var eleLen = $("#charge tbody").find("input[type=checkbox]").length;
		var data;
		for(var i = 0; i < eleLen; i++){
			if($("#charge tbody").find("input[type=checkbox]").get(i).checked){
				data = window.data[i];
				$('#myTab a:last').tab('show');
				$('.staff_name').val(data.name);
				$('.staff_dept').val(data.dept);
				$('.staff_enter').val(data.enter);
				$('.staff_gender').val(data.gender);
				$('.staff_phone').val(data.phone);
				$('.staff_address').val(data.address);
				$('.staff_graduate').val(data.graduate);
				$('.staff_major').val(data.major);
				$('.staff_degree').val(data.degree);
				$('.staff_id').val(data.id);
				break;
			}
		}
	});
})

// 获取数据
function getAll(currentpage, pagesize, name, dept, start, end){
	$.ajax({
		type: 'post',
		url: '/StaffInfo/get',
		data: {
			"name": name||"",
			"dept": dept||"",
			"start": start||"",
			"end": end||"",
			"currentpage": currentpage,
			"pagesize": pagesize
		},
		dataType: 'json',
		success: function(data){
//			console.log(data);
			if(data.length == 0){
				alert("查询的员工不存在！");
			}
			setData(currentpage, pagesize, data, name, dept, start, end);
		},
		error: function(data){
			console.log(data.responseText)
		}
	})
}

// 添加数据
function setData(currentpage, pagesize, data, name, dept, start, end){
	var num = data.num;
	var str = "";
	var strArray = new Array();
	window.data = data;
	$("#charge").find("tbody").html("");
	for(var i = 0, len = data.length; i < len; i++){
		strArray[0] = '<tr>';
		strArray[1] = '<td><input type="checkbox" data-index="'+ data[i].id +'"></td>';
		strArray[2] = '<td>'+ data[i].name +'</td>';
		strArray[3] = '<td>'+ data[i].dept +'</td>';
		strArray[4] = '<td>'+ data[i].enter +'</td>';
		strArray[5] = '<td>'+ data[i].gender +'</td>';
		strArray[6] = '<td>'+ data[i].phone +'</td>';
		strArray[7] = '<td>'+ data[i].address +'</td>';
		strArray[8] = '<td>'+ data[i].graduate +'</td>';
		strArray[9] = '<td>'+ data[i].major +'</td>';
		strArray[10] = '<td>'+ data[i].degree +'</td>';
		strArray[11] = '</tr>';

		for(var j = 0, strLen = strArray.length; j < strLen; j++){
			str += strArray[j];
		}
	}
	$("#charge").find("tbody").append(str);
	setPager(num, currentpage, pagesize, name, dept, start, end);
}

// 设置分页
function setPager(total, current, pagesize, name, dept, start, end){
	var pagenum = 0;
	var str = '';
	if(total % pagesize == 0){
		pagenum = total / pagesize;
	}else{
		pagenum = parseInt(total / pagesize) + 1;
	}

	if(current == 1){
		str += '<li class="previous disabled"><a href="#">&laquo;</a></li>';
	}else{
		str += '<li class="previous"><a href="#">&laquo;</a></li>';
	}
	
	for(var i = 0; i < pagenum; i++){
		if(current == (i + 1)){
			str += '<li class="active"><a href="#">'+ (i + 1) +'</a></li>';
		}else{
			str += '<li><a href="#">'+ (i + 1) +'</a></li>';
		}
	}
	
	if(current == pagenum){
		str += '<li class="next disabled"><a href="#">&raquo;</a></li>';
	}else{
		str += '<li class="next"><a href="#">&raquo;</a></li>';
	}
	//console.log(str);
	//console.log(current);
	//console.log(pagenum);
	$(".pagination").html("");
	$(".pagination").append(str);
	$.each($(".pagination").find("li"), function(){
		$(this).off('click');
		if($(this).attr('class') != 'active'){
			$(this).on('click', function(){
				var index = +$(this).text();
				if(!isNaN(index)){
					getAll(index, pagesize, name, dept, start, end);
				}
			});
		}
	});
	$(".disabled").off('click');
}