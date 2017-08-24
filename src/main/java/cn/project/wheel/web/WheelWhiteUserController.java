package cn.project.wheel.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelCurrencyModel;
import com.jeeplus.modules.wheel.entity.WheelGameUser;
import com.jeeplus.modules.wheel.entity.WheelPrize;
import com.jeeplus.modules.wheel.entity.WheelRecords;
import com.jeeplus.modules.wheel.service.WheelPrizeService;
import com.jeeplus.modules.wheel.service.WheelRecordsService;
import com.jeeplus.modules.wheel.service.WheelWhiteUserService;

@Controller
@RequestMapping(value = "{adminPath}/wheel/user")
public class WheelWhiteUserController extends BaseController{

	@Autowired
	private WheelWhiteUserService  wheelWhiteUserService;
	
	@Autowired
	private WheelPrizeService wheelPrizeService;
	
	@Autowired
	private WheelRecordsService wheelRecordsService;
	
	@RequestMapping(value = "/whiteUserList")
	public String whiteUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
		page = wheelWhiteUserService.findPage(page, gameUser);
		request.setAttribute("page3", page.toString());
		request.setAttribute("whiteUser", page.getList());
		request.setAttribute("count3",page.getCount());
		return "modules/wheel/whiteUserList";
	}
	
	@RequestMapping(value = "/addWhUser")
	public String addWhUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String fansId = request.getParameter("fansId");
		String headImgUrl = request.getParameter("headImgUrl");
		String nickName = request.getParameter("nickName");
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		WheelPrize prize = new WheelPrize();
		prize.setInfoId(infoId);;
		prize.setUnicalId(unicalId);
		List<WheelPrize> list = wheelPrizeService.findList(prize);
		request.setAttribute("infoId", infoId);
		request.setAttribute("fansId", fansId);
		request.setAttribute("headImgUrl", headImgUrl);
		request.setAttribute("nickName",nickName);
		request.setAttribute("list",list);
		return "modules/wheel/chooseWhiteUser";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sumbitWhiteUser")
	public boolean addWhiteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,WheelCurrencyModel wheelCurrencyModel) {
		int fansId = Integer.parseInt(request.getParameter("fansId"));
		String headImgUrl = request.getParameter("headImgUrl");
		String nickName = request.getParameter("nickName");
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		int count = wheelWhiteUserService.addWhiteUser(fansId,headImgUrl,nickName,infoId,unicalId,wheelCurrencyModel);
		if(1==count){
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkWhUser")
	public boolean checkWhUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int fansId = Integer.parseInt(request.getParameter("fansId"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		WheelGameUser gameUser = new WheelGameUser();
		gameUser.setFansId(fansId);
		gameUser.setInfoId(infoId);
		gameUser.setUnicalId(unicalId);
		if(0 == wheelWhiteUserService.findList(gameUser).size()){
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteWhiteUser")
	public boolean deleteWhiteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int gameuserId = Integer.parseInt(request.getParameter("gameuserId"));
		int fansId = Integer.parseInt(request.getParameter("fansId"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		int count = wheelWhiteUserService.deleteWhiteUser(gameuserId,fansId,infoId,unicalId);
		if( 0 != count){
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/editWhiteUser")
	public String editWhiteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String fansId = request.getParameter("fansId");
		String nickName = request.getParameter("nickName");
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		WheelPrize prize = new WheelPrize();
		prize.setInfoId(infoId);
		prize.setUnicalId(unicalId);
		List<WheelPrize> prizes = wheelPrizeService.findList(prize);
		WheelRecords record = new WheelRecords();
		record.setFansId(Integer.parseInt(fansId));
		record.setUnicalId(unicalId);
		record.setInfoId(infoId);
		List<WheelRecords> records = wheelRecordsService.findList(record);
		request.setAttribute("nickName",nickName);
		request.setAttribute("fansId",fansId);
		request.setAttribute("infoId",infoId);
		request.setAttribute("prizes",prizes);
		request.setAttribute("records",records);
		return "modules/wheel/editWhiteUser";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sumbitEditWhiteUser")
	public boolean sumbitEditWhiteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,WheelCurrencyModel wheelCurrencyModel) {
		int fansId = Integer.parseInt(request.getParameter("fansId"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		String nickName = request.getParameter("nickName");
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		String recordIds = request.getParameter("deleteRecord");
		List<WheelRecords> recordList = wheelCurrencyModel.getRecordList();
		List<WheelPrize> prizeList = wheelCurrencyModel.getPrizeList();
		int count = wheelRecordsService.sumbitEditWhiteUser(fansId,infoId,unicalId,nickName,recordIds,recordList,prizeList);
		if( 0 != count){
			return true;
		}
		return false;
	}
}
