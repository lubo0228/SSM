package cn.project.wheel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.wheel.dao.WheelExchangeuserDao;
import com.jeeplus.modules.wheel.entity.WheelExchangeUser;

@Service
@Transactional
public class WheelExchangeuserService extends CrudService<WheelExchangeuserDao,WheelExchangeUser>{

	@Autowired
	private WheelExchangeuserDao wheelExchangeuserDao;
	
	
	public List<WheelExchangeUser> findList(int unicalId,int infoId,String nickName){
		WheelExchangeUser exchangeUser = new WheelExchangeUser();
		exchangeUser.setUnicalId(unicalId);
		exchangeUser.setInfoId(infoId);
		exchangeUser.setNickName(nickName);
		return wheelExchangeuserDao.findList(exchangeUser);
	}

	public int saveExUser(WheelExchangeUser user) {
		return wheelExchangeuserDao.saveExUser(user);
	}


	public int deleteById(int exchangeuserId) {
		return wheelExchangeuserDao.deleteById(exchangeuserId);
	}
}
