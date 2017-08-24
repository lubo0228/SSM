package cn.project.wheel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelPrize 
* @Description: 奖品 
* @author Looper 
* @date 2017年8月14日 下午8:42:11 
*  
*/
public class WheelPrize extends DataEntity<WheelPrize>{


	private static final long serialVersionUID = 1L;
	private int prizeId;
	private String prizeLevel;//奖品级别
	private String prizeName;//奖品名称
	private String prizeImgUrl;//奖品图片路径
	private int prizeType;//奖品类型(1:实物,2:积分)
	private int prizeProbability;//中奖概率
	private int points;//积分
	private int prizeCount;//奖品数量
	private int displayOrder;//排序
	private Date createTime;//创建时间
	private String qrCode;//二维码
	private String whiteOpenId;//白名单OpenId
	private int balance;
	private int status;//奖品状态(0:未中奖,1:已中奖)
	private int unicalId;//商户ID
	private String activityTitle;//活动名称
	private String prizeATitle;//活动名称 别名
	private int infoId;//与基础信息表infoId关联
	private int showNum;//九宫格可显示数量
	private String prizeNickName;//级别昵称
	private int remainingCount;//奖品剩余数量（减去白名单）
	
	
	public int getShowNum() {
		return showNum;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	public String getPrizeNickName() {
		return prizeNickName;
	}
	public void setPrizeNickName(String prizeNickName) {
		this.prizeNickName = prizeNickName;
	}
	public int getRemainingCount() {
		return remainingCount;
	}
	public void setRemainingCount(int remainingCount) {
		this.remainingCount = remainingCount;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getPrizeATitle() {
		return prizeATitle;
	}
	public void setPrizeATitle(String prizeATitle) {
		this.prizeATitle = prizeATitle;
	}
	public int getUnicalId() {
		return unicalId;
	}
	public void setUnicalId(int unicalId) {
		this.unicalId = unicalId;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getWhiteOpenId() {
		return whiteOpenId;
	}
	public void setWhiteOpenId(String whiteOpenId) {
		this.whiteOpenId = whiteOpenId;
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
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public String getPrizeImgUrl() {
		return prizeImgUrl;
	}
	public void setPrizeImgUrl(String prizeImgUrl) {
		this.prizeImgUrl = prizeImgUrl;
	}
	public int getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(int prizeType) {
		this.prizeType = prizeType;
	}
	public int getPrizeProbability() {
		return prizeProbability;
	}
	public void setPrizeProbability(int prizeProbability) {
		this.prizeProbability = prizeProbability;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPrizeCount() {
		return prizeCount;
	}
	public void setPrizeCount(int prizeCount) {
		this.prizeCount = prizeCount;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
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
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}
