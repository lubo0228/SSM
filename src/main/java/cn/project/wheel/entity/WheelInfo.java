package cn.project.wheel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelInfo 
* @Description: 活动信息
* @author Looper 
* @date 2017年8月14日 下午8:39:44 
*  
*/
public class WheelInfo extends DataEntity<WheelInfo>{

	private static final long serialVersionUID = 1L;
	
	private int infoId;
	private String activityTitle;//活动标题
	private Date startTime;//活动开始时间
	private Date endTime;//活动结束时间
	private String activityDescription;//活动说明
	private String bgMusic;//背景音乐
	private String titlePic;//标题图片
	private String bgPic;//背景图片
	private String copyright; //版权
	private int dayCount;//每日砸蛋次数
	private int prizeLimit;//每个用户中奖次数限制
	private String newsIcon;//图文 图标
	private String newsContent;//图文描述
	private String shareTitle;//分享标题
	private String shareIcon;//分享标题
	private String shareContent;//分享描述
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private int pointsAuto;//积分自动兑换
	private int unicalId;//商户ID
	private String keyWord;//关键字
	private int status;//活动状态（0：未开始 1：进行中 2：结束）
	private int messageReplyId;//关键字表Id  与public库的关键字表messageReplyId关联
	private Date awardTime;//领奖截止时间
	
	
	public String getAwardTime() {
		String time = "";
		if(awardTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(awardTime);
		}
		return time;
	}
	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMessageReplyId() {
		return messageReplyId;
	}
	public void setMessageReplyId(int messageReplyId) {
		this.messageReplyId = messageReplyId;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public String getStartTime() {
		String time = "";
		if(startTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(startTime);
		}
		return time;
	
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		String time = "";
		if(endTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(endTime);
		}
		return time;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getBgMusic() {
		return bgMusic;
	}
	public void setBgMusic(String bgMusic) {
		this.bgMusic = bgMusic;
	}
	public String getTitlePic() {
		return titlePic;
	}
	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}
	public String getBgPic() {
		return bgPic;
	}
	public void setBgPic(String bgPic) {
		this.bgPic = bgPic;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	public int getPrizeLimit() {
		return prizeLimit;
	}
	public void setPrizeLimit(int prizeLimit) {
		this.prizeLimit = prizeLimit;
	}
	public String getNewsIcon() {
		return newsIcon;
	}
	public void setNewsIcon(String newsIcon) {
		this.newsIcon = newsIcon;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareIcon() {
		return shareIcon;
	}
	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}
	public String getShareContent() {
		return shareContent;
	}
	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public String getCreateTime() {
		String time = "";
		if(startTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(startTime);
		}
		return time;
	
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		String time = "";
		if(updateTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			time = sdf.format(updateTime);
		}
		return time;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getPointsAuto() {
		return pointsAuto;
	}
	public void setPointsAuto(int pointsAuto) {
		this.pointsAuto = pointsAuto;
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
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
}
