package cn.project.wheel.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelConsignee;

@MyBatisDao
public interface WheelConsigneeDao extends CrudDao<WheelConsignee>{

	public List<WheelConsignee> findList(WheelConsignee consignee);

	public int changeIsShip(WheelConsignee consignee);

	public int exchange(int infoId, int fansId, int prizeId);

}
