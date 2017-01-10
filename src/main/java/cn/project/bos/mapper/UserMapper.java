package cn.project.bos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.project.bos.pojo.Staff;

public interface UserMapper {

	Boolean save(Staff user);

	List<Staff> selectAll(@Param("name")String name,@Param("dept") String dept);

	Boolean delete(Integer ids);

	Boolean update(Staff staff);

}
