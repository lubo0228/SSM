package cn.project.wheel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelPrize;

@MyBatisDao
public interface WheelPrizeDao extends CrudDao<WheelPrize>{

	public List<WheelPrize> findList(WheelPrize prize);

	public WheelPrize findPrizeById(int prizeId);

	public int updateRemainingCount(@Param("remainingCount")int remainingCount, @Param("prizeId")int prizeId);
}
