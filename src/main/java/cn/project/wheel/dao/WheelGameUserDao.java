package cn.project.wheel.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelGameUser;

@MyBatisDao
public interface WheelGameUserDao extends CrudDao<WheelGameUser>{

	public List<WheelGameUser> findList(WheelGameUser wheelGameUser);

}
