package cn.project.wheel.entity;

import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelMaterial 
* @Description: 素材 
* @author Looper 
* @date 2017年8月14日 下午8:41:37 
*  
*/
public class WheelMaterial extends DataEntity <WheelMaterial>{

	private static final long serialVersionUID = 1L;

	private Integer materialId;//主键
	private String  mediaId;//上传多媒体文件在微信里,微信返回一个（mediaId)
	private String  filePath;//文件在服务器的路径
	private String serversFilePath;//文件在服务器的绝对路径（用来删除照片）
	private Integer unicalId;//绑定公众号所属ID
	private  String  fileType;//上传文件类型
	private Date createTime;//上传时间
	private Date updateTime;//修改时间
	private Integer status;//状态是永久还是临时
	private String title;//视频素材的标题
	private String introduction;//视频素材的描述 
	private String fileName;//文件名
	private String  wxFliePath;//微信提供的（图片）路径（只能在微信里用） 
    
	
	
	
	
	public String getWxFliePath() {
		return wxFliePath;
	}
	public void setWxFliePath(String wxFliePath) {
		this.wxFliePath = wxFliePath;
	}
	public String getServersFilePath() {
		return serversFilePath;
	}
	public void setServersFilePath(String serversFilePath) {
		this.serversFilePath = serversFilePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getUnicalId() {
		return unicalId;
	}
	public void setUnicalId(Integer unicalId) {
		this.unicalId = unicalId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
