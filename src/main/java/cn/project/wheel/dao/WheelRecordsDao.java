package cn.project.wheel.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelRecords;

@MyBatisDao
public interface WheelRecordsDao extends CrudDao<WheelRecords> {

	public int exchange(WheelRecords records);

	public List<WheelRecords> findList(WheelRecords records);

	public int addWheelRecord(WheelRecords wheelRecords);

	public int deleteWhiteRecords(WheelRecords wr);

	public int updateWhiteUserNum(WheelRecords wheelRecord);

	public WheelRecords findRecordById(int recordId);
}
