package cn.project.wheel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;


/** 
* @ClassName: WheelRecords 
* @Description: 中奖纪录
* @author Looper 
* @date 2017年8月14日 下午8:42:28 
*  
*/
public class WheelRecords extends  DataEntity<WheelRecords>{

	private static final long serialVersionUID = 1L;
	private int recordsId;
	private String prizeName;//奖品名称
	private int status;//状态(0：未核销，1：已核销)
	private Date createTime;//创建时间
	private Date receiveTime;//领取时间
	private int unicalId;//商户ID
	private int prizeId;//奖品Id
	private String prizeLevel;//奖品级别
	private int prizeType;//奖品类型(1:实物,2:积分,3:余额)
	private String qrCode;//二维码
	private int balance;//余额
	private int points;//积分
	private String nickName;//中奖用户昵称
	private String activityTitle;//活动标题
	private int infoId;//与基础信息表infoId关联
	private int fansId;//与fans表 fansId关联
	private int whiteUserNum;//白名单奖品数量
	
	
	public int getFansId() {
		return fansId;
	}
	public void setFansId(int fansId) {
		this.fansId = fansId;
	}
	public int getWhiteUserNum() {
		return whiteUserNum;
	}
	public void setWhiteUserNum(int whiteUserNum) {
		this.whiteUserNum = whiteUserNum;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getRecordsId() {
		return recordsId;
	}
	public void setRecordsId(int recordsId) {
		this.recordsId = recordsId;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateTime() {
		String time = "";
		if(createTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(createTime);
		}
		return time;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getReceiveTime() {
		String time = "";
		if(receiveTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(receiveTime);
		}
		return time;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public int getUnicalId() {
		return unicalId;
	}
	public void setUnicalId(int unicalId) {
		this.unicalId = unicalId;
	}
	public int getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}
	public String getPrizeLevel() {
		return prizeLevel;
	}
	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	public int getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(int prizeType) {
		this.prizeType = prizeType;
	}

	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
}
