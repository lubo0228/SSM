package cn.project.wheel.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.modules.wheel.dao.WheelPrizeDao;
import com.jeeplus.modules.wheel.dao.WheelRecordsDao;
import com.jeeplus.modules.wheel.entity.WheelPrize;
import com.jeeplus.modules.wheel.entity.WheelRecords;

@Service
@Transactional
public class WheelRecordsService extends CrudService<WheelRecordsDao,WheelRecords>{

	@Autowired
	private WheelRecordsDao wheelRecordsDao;

	@Autowired
	private WheelPrizeDao wheelPrizeDao;
	
	public List<WheelRecords> findList(WheelRecords records){
		return wheelRecordsDao.findList(records);
	}
	
	public ExportExcel outExcel(int unicalId, int infoId) {
		WheelRecords wheelRecords = new WheelRecords();
		wheelRecords.setUnicalId(unicalId);
		wheelRecords.setInfoId(infoId);
		List<WheelRecords> list = wheelRecordsDao.findList(wheelRecords);
		List<String> headerList = Lists.newArrayList();
		headerList.add("活动标题");
		headerList.add("中奖用户标识");
		headerList.add("中奖用户昵称");
		headerList.add("奖品编号");
		headerList.add("奖品等级");
		headerList.add("奖品类型");
		headerList.add("奖品名称");
		headerList.add("奖品状态");
		headerList.add("创建时间");
		headerList.add("领取时间");
		headerList.add("奖品积分");
		
		List<List<String>> dataList = Lists.newArrayList();
		String prizeType = null;
		String status = null;
		for (WheelRecords records : list) {
			List<String> dataRowList = Lists.newArrayList();
			if(records.getPrizeType() == 1){
				prizeType = "实物";
			}else if(records.getPrizeType() == 2){
				prizeType = "积分";
			}
			if(records.getStatus() == 0){
				status = "未核销";
			}else if(records.getStatus() == 1){
				status = "已核销";
			}
			dataRowList.add(records.getActivityTitle());
			dataRowList.add(String.valueOf(records.getFansId()));
			dataRowList.add(records.getNickName());
			dataRowList.add(String.valueOf(records.getPrizeId()));
			dataRowList.add(records.getPrizeLevel());
			dataRowList.add(prizeType);
			dataRowList.add(records.getPrizeName());
			dataRowList.add(status);
			dataRowList.add(records.getCreateTime());
			dataRowList.add(records.getReceiveTime());
			dataRowList.add(String.valueOf(records.getPoints()));
			dataList.add(dataRowList);
		}
		
		ExportExcel ee = new ExportExcel("活动中奖信息", headerList);
		
		for (int i = 0; i < dataList.size(); i++) {
			Row row = ee.addRow();
			for (int j = 0; j < dataList.get(i).size(); j++) {
				ee.addCell(row, j, dataList.get(i).get(j));
			}
		}
		
		return ee;
	}

	public int sumbitEditWhiteUser(int fansId, int infoId, int unicalId, String nickName,
			String recordIds, List<WheelRecords> recordList, List<WheelPrize> prizeList) {
		int count = 0;
		//删除
		if(StringUtils.isNotBlank(recordIds)){
			String[] ids = recordIds.split(",");
			for (String id : ids) {
				//更新奖品表remainingCount
				int  recordId = Integer.parseInt(id);
				WheelRecords  wr = wheelRecordsDao.findRecordById(recordId);
				int  prizeId = wr.getPrizeId();
				WheelPrize wp = wheelPrizeDao.findPrizeById(prizeId);
				int  rc = wr.getWhiteUserNum()+wp.getRemainingCount();
				count = wheelPrizeDao.updateRemainingCount(rc, prizeId);
				//删除record
				if(1 == count){
					wheelRecordsDao.deleteWhiteRecords(wr);
				}
			}
		}
		//更新
		if(null !=recordList && 0 != recordList.size()){
			for (WheelRecords wheelRecord : recordList) {
				//更新奖品表remainingCount
				if(0 != wheelRecord.getRecordsId()){
					WheelRecords  wr = wheelRecordsDao.findRecordById(wheelRecord.getRecordsId());
					int  prizeId = wr.getPrizeId();
					WheelPrize wp = wheelPrizeDao.findPrizeById(prizeId);
					int  rc = wr.getWhiteUserNum()-wheelRecord.getWhiteUserNum()+wp.getRemainingCount();
					count = wheelPrizeDao.updateRemainingCount(rc, prizeId);
					//更新record
					if(1 == count){
						wheelRecordsDao.updateWhiteUserNum(wheelRecord);
					}
				}
			}
		}
		//增加
		if(null !=prizeList && 0 != prizeList.size()){
			for (WheelPrize wheelPrize : prizeList) {
				//更新奖品表remainingCount
				if(0 != wheelPrize.getPrizeId()){
					WheelPrize wp = wheelPrizeDao.findPrizeById(wheelPrize.getPrizeId());
					int	remainingCount = wp.getRemainingCount() - wheelPrize.getPrizeCount();
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

}
