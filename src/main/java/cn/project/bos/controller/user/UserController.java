package cn.project.bos.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.project.bos.pojo.Staff;
import cn.project.bos.service.UserService;


@Controller
@RequestMapping("StaffInfo")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Staff user){
		return userService.save(user)?"1":"0";
	}
	@ResponseBody
	@RequestMapping(value="get",method=RequestMethod.POST)
	public List<Staff> get(String name,String dept,@RequestParam(defaultValue="1")Integer currentpage,@RequestParam(defaultValue="5")Integer pagesize){
		return userService.selectAll(name,dept,currentpage,pagesize);
	}
	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam()Integer ids){
		return userService.delete(ids)?"1":"0";
	}
	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Staff staff){
		return userService.update(staff)?"1":"0";
	}
}
