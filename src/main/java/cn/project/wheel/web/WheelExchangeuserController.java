package cn.project.wheel.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelExchangeUser;
import com.jeeplus.modules.wheel.service.WheelExchangeuserService;

@Controller
@RequestMapping(value = "{adminPath}/wheel/user")
public class WheelExchangeuserController extends BaseController{

	@Autowired
	private WheelExchangeuserService  wheelExchangeuserService;
	
	@RequestMapping(value = "/exchangeUserList")
	public String exchangeUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int pageNo = 1;
		int pageSize = 10;
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		String nickName = request.getParameter("nickName");
		Page<WheelExchangeUser> page = new Page<WheelExchangeUser>(request,response);
		WheelExchangeUser exchangeUser = new WheelExchangeUser();
		exchangeUser.setUnicalId(unicalId);
		exchangeUser.setInfoId(infoId);
		exchangeUser.setNickName(nickName);;
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = wheelExchangeuserService.findPage(page, exchangeUser);
		request.setAttribute("page2", page.toString());
		request.setAttribute("exchangeUser", page.getList());
		request.setAttribute("count2",page.getCount());
		return "modules/wheel/exchangeUserList";
	}
	
	@RequestMapping(value = "/addExchangeUser")
	public String addExchangeUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		return "modules/wheel/addExchangeUser";
	}
	
	@RequestMapping(value = "/addWhiteUser")
	public String addWhiteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		return "modules/wheel/addWhiteUser";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addExUser")
	public boolean addExUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String fansId = request.getParameter("fansId");
		String headImgUrl = request.getParameter("headImgUrl");
		String nickName = request.getParameter("nickName");
		String infoId = request.getParameter("infoId");
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		WheelExchangeUser user = new WheelExchangeUser();
		user.setFansId(fansId);
		user.setInfoId(Integer.parseInt(infoId));
		user.setUnicalId(unicalId);
		if(0 == wheelExchangeuserService.findList(user).size()){
			user.setHeadImgUrl(headImgUrl);
			user.setNickName(nickName);
			user.setCreateTime(new Date());
			int count = wheelExchangeuserService.saveExUser(user);
			if(1==count){
				return true;
			}
		}	
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteExchangeUser")
	public boolean deleteExchangeUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String exchangeuserId = request.getParameter("exchangeuserId");
		int count = wheelExchangeuserService.deleteById(Integer.parseInt(exchangeuserId));
		if (1 == count)	{
			return true;
		}
		return false;
	}
}
