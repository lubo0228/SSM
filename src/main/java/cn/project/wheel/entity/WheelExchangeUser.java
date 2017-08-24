package cn.project.wheel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelExchangeUser 
* @Description: 核销人员 
* @author Looper 
* @date 2017年8月14日 下午8:39:02 
*  
*/
public class WheelExchangeUser extends DataEntity<WheelExchangeUser>{

	private static final long serialVersionUID = 1L;
	private int exchangeuserId;
	private String fansId;//与fans表 fansId关联
	private int unicalId;//商户ID
	private String nickName;//昵称
	private String headImgUrl;//头像URL
	private Date createTime;//创建时间
	private String activityTitle;//活动标题
	private int infoId;//与基础信息表infoId关联
	
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
	public String getFansId() {
		return fansId;
	}
	public void setFansId(String fansId) {
		this.fansId = fansId;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	
	public int getExchangeuserId() {
		return exchangeuserId;
	}
	public void setExchangeuserId(int exchangeuserId) {
		this.exchangeuserId = exchangeuserId;
	}
	public int getUnicalId() {
		return unicalId;
	}
	public void setUnicalId(int unicalId) {
		this.unicalId = unicalId;
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
	@Override
	public String toString() {
		return "ExchangeUser [exchangeuserId=" + exchangeuserId + ", fansId=" + fansId + ", unicalId=" + unicalId
				+ ", nickName=" + nickName + ", headImgUrl=" + headImgUrl + ", activityTitle=" + activityTitle
				+ ", infoId=" + infoId + "]";
	}
	
}
