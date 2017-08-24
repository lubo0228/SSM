package cn.project.wheel.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelExchangeUser;

@MyBatisDao
public interface WheelExchangeuserDao extends CrudDao<WheelExchangeUser>{
	
	public List<WheelExchangeUser> findList(WheelExchangeUser wheelGameUser);

	public int saveExUser(WheelExchangeUser user);

	public int deleteById(int exchangeuserId);

}
