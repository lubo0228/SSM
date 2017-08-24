package cn.project.wheel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelConsignee;
import com.jeeplus.modules.wheel.service.WheelConsigneeService;

@Controller
@RequestMapping(value = "{adminPath}/wheel/user")
public class WheelConsigneeController extends BaseController{

	@Autowired
	private WheelConsigneeService  wheelConsigneeService;
	
	@RequestMapping(value = "/consigneeList")
	public String wheelConsigneeList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int pageNo = 1;
		int pageSize = 10;
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		String nickName = request.getParameter("nickName");
		Page<WheelConsignee> page = new Page<WheelConsignee>(request,response);
		WheelConsignee consignee = new WheelConsignee();
		consignee.setUnicalId(unicalId);
		consignee.setInfoId(infoId);
		consignee.setNickName(nickName);;
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = wheelConsigneeService.findPage(page, consignee);
		request.setAttribute("page4", page.toString());
		request.setAttribute("consignee", page.getList());
		request.setAttribute("count4",page.getCount());
		return "modules/wheel/consigneeList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/confirmShip")
	public boolean confirmShip(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		int fansId = Integer.parseInt(request.getParameter("fansId"));
		int prizeId = Integer.parseInt(request.getParameter("prizeId"));
		int consigneeId = Integer.parseInt(request.getParameter("consigneeId"));
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		return wheelConsigneeService.confirmShip(infoId,fansId,prizeId,consigneeId,unicalId);
	}
}
