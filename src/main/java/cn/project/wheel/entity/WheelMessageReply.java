package cn.project.wheel.entity;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelMessageReply 
* @Description: 关键字信息 
* @author Looper 
* @date 2017年8月14日 下午8:41:54 
*  
*/
public class WheelMessageReply extends DataEntity<WheelMessageReply>{

	private static final long serialVersionUID = 1L;
	private int messageReplyId;//主键ID
	private int unicalid;//绑定公众号所属ID
	private String keyWord;//关键字
	private String msgType;//类型
	private String content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	private String mediaId;//通过素材管理接口上传多媒体文件，得到的id。
	private int articleCount;//图文消息个数，限制为10条以内
	private String title;//消息标题
	private String description;//消息描述
	private String picUrl;//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String url;//点击图文消息跳转链接
	private String musicURL;//音乐链接
	private String HQMusicUrl;//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String thumbMediaId;//缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
	private int status;//（0：打开；1：关闭  2删除；3：回复全部）
	private String ruleName;//规则名
	private String event;//事件类型
	private int onOrOff;//0:开启  1:关闭
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public int getUnicalid() {
		return unicalid;
	}
	public void setUnicalid(int unicalid) {
		this.unicalid = unicalid;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMusicURL() {
		return musicURL;
	}
	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getOnOrOff() {
		return onOrOff;
	}
	public void setOnOrOff(int onOrOff) {
		this.onOrOff = onOrOff;
	}
	@Override
	public String toString() {
		return "MessageReply [keyWord=" + keyWord + ", unicalid=" + unicalid + ", msgType=" + msgType + ", content="
				+ content + ", mediaId=" + mediaId + ", articleCount=" + articleCount + ", title=" + title
				+ ", description=" + description + ", picUrl=" + picUrl + ", url=" + url + ", musicURL=" + musicURL
				+ ", HQMusicUrl=" + HQMusicUrl + ", thumbMediaId=" + thumbMediaId + ", status=" + status + ", ruleName="
				+ ruleName + ", event=" + event + ", onOrOff=" + onOrOff + "]";
	}
}
