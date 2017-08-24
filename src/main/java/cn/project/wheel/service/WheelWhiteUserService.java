package cn.project.wheel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.wheel.dao.WheelInfoDao;
import com.jeeplus.modules.wheel.dao.WheelPrizeDao;
import com.jeeplus.modules.wheel.dao.WheelRecordsDao;
import com.jeeplus.modules.wheel.dao.WheelWhiteUserDao;
import com.jeeplus.modules.wheel.entity.WheelCurrencyModel;
import com.jeeplus.modules.wheel.entity.WheelGameUser;
import com.jeeplus.modules.wheel.entity.WheelInfo;
import com.jeeplus.modules.wheel.entity.WheelPrize;
import com.jeeplus.modules.wheel.entity.WheelRecords;

@Service
@Transactional
public class WheelWhiteUserService extends CrudService<WheelWhiteUserDao,WheelGameUser>{

	@Autowired
	private WheelWhiteUserDao wheelWhiteUserDao;
	
	@Autowired
	private WheelInfoDao  wheelInfoDao;
	
	@Autowired
	private WheelRecordsDao wheelRecordsDao;
	
	@Autowired
	private WheelPrizeDao  wheelPrizeDao;
	
	public List<WheelGameUser> findList(int unicalId,int infoId,String nickName){
		WheelGameUser wheelGameUser = new WheelGameUser();
		wheelGameUser.setUnicalId(unicalId);
		wheelGameUser.setInfoId(infoId);
		wheelGameUser.setNickName(nickName);
		return wheelWhiteUserDao.findList(wheelGameUser);
	}


	public int addWhiteUser(int fansId, String headImgUrl, String nickName, int infoId, int unicalId, WheelCurrencyModel wheelCurrencyModel) {
		WheelInfo  wheelInfo = wheelInfoDao.findInfoById(infoId);
		//添加白名单人员
		WheelGameUser wheelGameUser = new WheelGameUser();
		wheelGameUser.setNickName(nickName);
		wheelGameUser.setHeadImgUrl(headImgUrl);
		wheelGameUser.setCreateTime(new Date());
		wheelGameUser.setUnicalId(unicalId);
		wheelGameUser.setRemainingCount(wheelInfo.getDayCount());
		wheelGameUser.setDayCountLimit(wheelInfo.getDayCount());
		wheelGameUser.setPrizeLimit(wheelInfo.getPrizeLimit());
		wheelGameUser.setInfoId(infoId);
		wheelGameUser.setFansId(fansId);
		wheelGameUser.setIsWhiteUser(1);
		int  count = wheelWhiteUserDao.addWhiteUser(wheelGameUser);
		//改变奖品剩余数量
		if(1 == count ){
			for(int n=0;n<wheelCurrencyModel.getPrizeList().size();n++){
				WheelPrize wheelPrize = wheelCurrencyModel.getPrizeList().get(n);
				if(0 != wheelPrize.getPrizeId()){
					WheelPrize nowWheelPrize = wheelPrizeDao.findPrizeById(wheelPrize.getPrizeId());
					int	remainingCount = nowWheelPrize.getRemainingCount() - wheelPrize.getPrizeCount();
					count = wheelPrizeDao.updateRemainingCount(remainingCount,wheelPrize.getPrizeId());				
					//增加中奖纪录
					if(1 == count ){
						WheelRecords wheelRecords = new WheelRecords();
						wheelRecords.setPrizeId(wheelPrize.getPrizeId());
						wheelRecords.setPrizeLevel(wheelPrize.getPrizeLevel());
						wheelRecords.setNickName(nickName);
						wheelRecords.setPrizeName(wheelPrize.getPrizeName());
						wheelRecords.setPrizeType(wheelPrize.getPrizeType());
						wheelRecords.setStatus(0);
						wheelRecords.setCreateTime(new Date());
						wheelRecords.setPoints(wheelPrize.getPoints());
						wheelRecords.setWhiteUserNum(wheelPrize.getPrizeCount());
						wheelRecords.setUnicalId(unicalId);
						wheelRecords.setFansId(fansId);
						wheelRecords.setInfoId(infoId);
						count = wheelRecordsDao.addWheelRecord(wheelRecords);
					}
				}
			}
		}
		return count;
	}

	public int deleteWhiteUser(int gameuserId, int fansId, int infoId, int unicalId) {
		//删除被选人员
		int count = wheelWhiteUserDao.deleteWhiteUserById(gameuserId);
		//改变奖品剩余数量
		if(1 == count){
			WheelRecords wheelRecords = new WheelRecords();
			wheelRecords.setFansId(fansId);
			wheelRecords.setInfoId(infoId);
			wheelRecords.setUnicalId(unicalId);
			List<WheelRecords>  records = wheelRecordsDao.findList(wheelRecords);
			for (WheelRecords w : records) {
				int prizeId = w.getPrizeId();
				int whiteUserNum = w.getWhiteUserNum();
				WheelPrize nowWheelPrize = wheelPrizeDao.findPrizeById(prizeId);
				int	remainingCount = nowWheelPrize.getRemainingCount() + whiteUserNum;
				count = wheelPrizeDao.updateRemainingCount(remainingCount,prizeId);	
			}
			//删除中奖纪录
			if(1 == count){
				WheelRecords wr = new WheelRecords();
				wr.setFansId(fansId);
				wr.setInfoId(infoId);
				wr.setUnicalId(unicalId);
				count = wheelRecordsDao.deleteWhiteRecords(wr);
			}
		}
		return count;
	}
}
