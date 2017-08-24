package cn.project.wheel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.wheel.dao.WheelPrizeDao;
import com.jeeplus.modules.wheel.entity.WheelPrize;

@Service
@Transactional
public class WheelPrizeService extends CrudService<WheelPrizeDao,WheelPrize>{

	@Autowired
	private WheelPrizeDao wheelPrizeDao;
	
	public List<WheelPrize> findList(WheelPrize prize) {
		return wheelPrizeDao.findList(prize);
	}

	public WheelPrize findPrizeById(int prizeId) {
		return wheelPrizeDao.findPrizeById(prizeId);
	}
}
