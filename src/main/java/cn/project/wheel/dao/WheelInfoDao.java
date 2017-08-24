package cn.project.wheel.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelInfo;

@MyBatisDao
public interface WheelInfoDao extends CrudDao<WheelInfo>{

	public List<WheelInfo> findList(WheelInfo wheelInfo);

	public WheelInfo findInfoById(int infoId);
}
