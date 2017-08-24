package cn.project.wheel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelInfo;
import com.jeeplus.modules.wheel.service.WheelInfoService;

/** 
* @ClassName: WheelInfoController 
* @Description: 活动信息 Controller
* @author Looper 
* @date 2017年8月15日 上午9:41:44 
*  
*/
@Controller
@RequestMapping(value = "{adminPath}/wheel/info")
public class WheelInfoController extends BaseController{

	@Autowired
	private WheelInfoService wheelInfoService;
	
	@RequestMapping(value = "/wheelAdministration")
	public String wheelAdministration(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int pageNo = 1;
		int pageSize = 10;
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		String activityTitle = request.getParameter("activityTitle");
		Page<WheelInfo> page = new Page<WheelInfo>(request,response);
		WheelInfo wheelInfo = new WheelInfo();
		wheelInfo.setUnicalId(unicalId);
		wheelInfo.setActivityTitle(activityTitle);
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = wheelInfoService.findPage(page, wheelInfo);
		request.setAttribute("page", page.toString());
		request.setAttribute("info", page.getList());
		request.setAttribute("count",page.getCount());
		return "modules/wheel/wheelAdministration";
	}
		
	
}
