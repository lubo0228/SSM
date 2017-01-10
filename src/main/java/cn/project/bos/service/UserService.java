package cn.project.bos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.project.bos.mapper.UserMapper;
import cn.project.bos.pojo.Staff;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	public Boolean save(@RequestBody()Staff user) {
		return userMapper.save(user);
	}

	public List<Staff> selectAll(String name, String dept, Integer currentpage,
			Integer pageSize) {
		PageHelper.startPage(currentpage, pageSize);
		List<Staff> list = userMapper.selectAll(name,dept);
		return new PageInfo<Staff>(list).getList();
	}

	public Boolean delete(Integer ids) {
		return userMapper.delete(ids);
	}

	public Boolean update(Staff staff) {
		return userMapper.update(staff);
	}
	
}
