package cn.project.wheel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelGameUser 
* @Description: 参加用户
* @author Looper 
* @date 2017年8月14日 下午8:39:25 
*  
*/
public class WheelGameUser extends DataEntity<WheelGameUser>{

	private static final long serialVersionUID = 1L;
	private int gameuserId;
	private int fansId; //用户唯一标识
	private String activityTitle;//活动Id
	private String nickName;//昵称
	private	String headImgUrl;//头像URL
	private Date createTime;//创建时间
	private int unicalId;//商户ID
	private int integral;//积分
	private int balance;//余额
	private Date addTime;//添加时间
	private int remainingCount;//当日剩余抽奖次数
	private int dayCountLimit;//每日抽奖上限
	private int prizeLimit;//中奖上限
	private int infoId;//与基础信息表infoId关联
	private int isWhiteUser;//是否白名单用户（0：否 1：是）
	
	public int getDayCountLimit() {
		return dayCountLimit;
	}
	public void setDayCountLimit(int dayCountLimit) {
		this.dayCountLimit = dayCountLimit;
	}
	public int getPrizeLimit() {
		return prizeLimit;
	}
	public void setPrizeLimit(int prizeLimit) {
		this.prizeLimit = prizeLimit;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public int getIsWhiteUser() {
		return isWhiteUser;
	}
	public void setIsWhiteUser(int isWhiteUser) {
		this.isWhiteUser = isWhiteUser;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public int getGameuserId() {
		return gameuserId;
	}
	public void setGameuserId(int gameuserId) {
		this.gameuserId = gameuserId;
	}

	public int getFansId() {
		return fansId;
	}
	public void setFansId(int fansId) {
		this.fansId = fansId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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
	public int getUnicalId() {
		return unicalId;
	}
	public void setUnicalId(int unicalId) {
		this.unicalId = unicalId;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAddTime() {
		String time = "";
		if(addTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(addTime);
		}
		return time;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getRemainingCount() {
		return remainingCount;
	}
	public void setRemainingCount(int remainingCount) {
		this.remainingCount = remainingCount;
	}
	
}
