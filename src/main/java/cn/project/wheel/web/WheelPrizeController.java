package cn.project.wheel.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelPrize;
import com.jeeplus.modules.wheel.service.WheelPrizeService;


@Controller
@RequestMapping(value = "${adminPath}/wheel/prize")
public class WheelPrizeController extends BaseController{
	
	@Autowired 
	private WheelPrizeService wheelprizeService;
	
	@ResponseBody
	@RequestMapping(value = "/findPrizeById")
	public WheelPrize findPrizeById(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		int prizeId = Integer.parseInt(request.getParameter("prizeId"));
		return wheelprizeService.findPrizeById(prizeId);
	}
}
