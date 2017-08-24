package cn.project.wheel.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.wheel.entity.WheelRecords;
import com.jeeplus.modules.wheel.service.WheelRecordsService;

@Controller
@RequestMapping(value = "{adminPath}/wheel/record")
public class WheelRecordsController extends BaseController{

	@Autowired
	private WheelRecordsService  wheelRecordsService;
	
	@RequestMapping(value = "/wheelRecordsList")
	public String wheelRecordsList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int pageNo = 1;
		int pageSize = 10;
		int unicalId = Integer.parseInt((String)session.getAttribute("unicalid"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		String search = request.getParameter("search");
		Page<WheelRecords> page = new Page<WheelRecords>(request,response);
		WheelRecords records = new WheelRecords();
		records.setUnicalId(unicalId);
		records.setInfoId(infoId);
		records.setNickName(search);
		records.setPrizeName(search);
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = wheelRecordsService.findPage(page, records);
		request.setAttribute("page", page.toString());
		request.setAttribute("records", page.getList());
		request.setAttribute("count",page.getCount());
		return "modules/wheel/wheelRecordsQuery";
	}
	
	@RequestMapping(value = "/outExcel")
	public void outExcel(HttpServletRequest request, HttpServletResponse response){
		HttpSession httpSession = request.getSession();
		int unicalId = Integer.parseInt((String)httpSession.getAttribute("unicalid"));
		int infoId = Integer.parseInt(request.getParameter("infoId"));
		ExportExcel ee = wheelRecordsService.outExcel(unicalId,infoId);
		if(ee == null){
			return;
		}
		OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();  
			response.setHeader("Content-disposition", "attachment; filename=Record.xls");  
			response.setContentType("application/msexcel");    
			ee.write(output); 
			output.flush();    
			output.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
