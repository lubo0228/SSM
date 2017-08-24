package cn.project.wheel.entity;

import java.util.List;



public class WheelCurrencyModel {
	private List<WheelPrize> prizeList;
	private List<WheelRecords> recordList;
	public List<WheelRecords> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<WheelRecords> recordList) {
		this.recordList = recordList;
	}

	public List<WheelPrize> getPrizeList() {
		return prizeList;
	}

	public void setPrizeList(List<WheelPrize> prizeList) {
		this.prizeList = prizeList;
	}
}
