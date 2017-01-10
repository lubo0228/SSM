package cn.project.bos.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class ToPage {

	@RequestMapping("{pageName}")
	public ModelAndView toPage(@PathVariable(value="pageName")String pageName){
		return new ModelAndView(pageName);
	}
}
