package cn.project.wheel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.wheel.dao.WheelInfoDao;
import com.jeeplus.modules.wheel.entity.WheelInfo;

@Service
@Transactional
public class WheelInfoService extends CrudService<WheelInfoDao,WheelInfo>{

	@Autowired
	protected WheelInfoDao wheelInfoDao;
	
	public List<WheelInfo> findList(int unicalId,String activityTitle){
		WheelInfo wheelInfo = new WheelInfo();
		wheelInfo.setUnicalId(unicalId);
		wheelInfo.setActivityTitle(activityTitle);
		List<WheelInfo> list = wheelInfoDao.findList(wheelInfo);
		return list;
	}
}
