package cn.project.wheel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.wheel.dao.WheelConsigneeDao;
import com.jeeplus.modules.wheel.dao.WheelRecordsDao;
import com.jeeplus.modules.wheel.entity.WheelConsignee;
import com.jeeplus.modules.wheel.entity.WheelRecords;

@Service
@Transactional
public class WheelConsigneeService extends CrudService<WheelConsigneeDao,WheelConsignee>{

	@Autowired
	private WheelConsigneeDao wheelConsigneeDao;
	
	@Autowired
	private WheelRecordsDao wheelRecordsDao;
	
	public List<WheelConsignee> findList(int unicalId,int infoId,String nickName){
		WheelConsignee consignee = new WheelConsignee();
		consignee.setUnicalId(unicalId);
		consignee.setInfoId(infoId);
		consignee.setNickName(nickName);
		return wheelConsigneeDao.findList(consignee);
	}

	public boolean confirmShip(int infoId, int fansId, int prizeId, int consigneeId, int unicalId) {
		//改变收货状态
		WheelConsignee consignee = new WheelConsignee();
		consignee.setInfoId(infoId);
		consignee.setFansId(fansId);
		consignee.setPrizeId(prizeId);
		consignee.setUnicalId(unicalId);
		consignee.setConsigneeId(consigneeId);
		int count = wheelConsigneeDao.changeIsShip(consignee);
		//核销
		if(1 == count){
			WheelRecords records = new WheelRecords();
			records.setInfoId(infoId);
			records.setFansId(fansId);
			records.setPrizeId(prizeId);
			records.setUnicalId(unicalId);
			int exchange = wheelRecordsDao.exchange(records);
			if(0 != exchange){
				return true;
			}
		}
		return false;
	}
}
