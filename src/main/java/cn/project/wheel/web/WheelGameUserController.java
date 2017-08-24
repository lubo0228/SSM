package cn.project.wheel.web;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelGameUser;
import com.jeeplus.modules.wheel.service.WheelGameUserService;

@Controller
@RequestMapping(value = "{adminPath}/wheel/user")
public class WheelGameUserController extends BaseController{

	@Autowired
	private WheelGameUserService  wheelGameUserService;
	
	@RequestMapping(value = "/gameUserList")
	public String gameUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int pageNo = 1;
		int pageSize = 10;
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		String nickName = request.getParameter("nickName");
		Page<WheelGameUser> page = new Page<WheelGameUser>(request,response);
		WheelGameUser gameUser = new WheelGameUser();
		gameUser.setUnicalId(unicalId);
		gameUser.setInfoId(infoId);
		gameUser.setNickName(nickName);
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = wheelGameUserService.findPage(page, gameUser);
		request.setAttribute("page1", page.toString());
		request.setAttribute("gameUser", page.getList());
		request.setAttribute("count1",page.getCount());
		return "modules/wheel/gameUserList";
	}
	
	@RequestMapping(value = "/outExcel")
	public void outExcel(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		int unicalId = Integer.parseInt((String)httpSession.getAttribute("unicalid"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		ExportExcel ee = wheelGameUserService.outExcel(unicalId,infoId);
		if(ee == null){
			return;
		}
		OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();  
			response.setHeader("Content-disposition", "attachment; filename=User.xls");  
			response.setContentType("application/msexcel");    
			ee.write(output); 
			output.flush();    
			output.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	@RequestMapping(value = "/searchFansToaddEx")
	public String searchFansToaddEx(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		String unicalId = (String)httpSession.getAttribute("unicalid");
		String nickName = request.getParameter("nickName");
		List<WheelGameUser>  list = wheelGameUserService.searchFans(unicalId,nickName);
		request.setAttribute("list", list);
		return "modules/wheel/addExchangeUser";
	}
	
	@RequestMapping(value = "/searchFansToaddWh")
	public String searchFansToaddWh(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
		String unicalId = (String)httpSession.getAttribute("unicalid");
		String nickName = request.getParameter("nickName");
		List<WheelGameUser>  list = wheelGameUserService.searchFans(unicalId,nickName);
		request.setAttribute("list", list);
		return "modules/wheel/addWhiteUser";
	}
}
