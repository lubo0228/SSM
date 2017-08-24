package cn.project.wheel.entity;

import com.jeeplus.common.persistence.DataEntity;

/** 
* @ClassName: WheelConsignee 
* @Description: 收货人信息 
* @author Looper 
* @date 2017年8月14日 下午8:38:34 
*  
*/
public class WheelConsignee extends DataEntity<WheelConsignee>{

	private static final long serialVersionUID = 1L;
	public int consigneeId;//收货人姓名
	public String name;//收货人姓名
	public String telphone;//收货人手机
	public String address;//收货人地址
	public String nickName;//昵称
	public String headImgUrl;//头像URL
	public String prizeInfo;//奖品信息
	public int isShip;//是否发货（0：否 1：是）
	public int selectStatus;//默认地址选中状态（0：否 1：是）
	public int infoId;//与基础信息表infoId关联
	public int unicalId;//商户ID
	public int fansId;//与fans表 fansId关联
	public String qrCode;//二维码
	public int prizeId;//奖品Id
	public int isReceiptAddr;//是否为收货地址（0：否 1：是）
	
	public String getPrizeInfo() {
		return prizeInfo;
	}
	public void setPrizeInfo(String prizeInfo) {
		this.prizeInfo = prizeInfo;
	}
	public int getConsigneeId() {
		return consigneeId;
	}
	public int getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}
	public int getIsReceiptAddr() {
		return isReceiptAddr;
	}
	public void setIsReceiptAddr(int isReceiptAddr) {
		this.isReceiptAddr = isReceiptAddr;
	}
	public void setConsigneeId(int consigneeId) {
		this.consigneeId = consigneeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public int getIsShip() {
		return isShip;
	}
	public void setIsShip(int isShip) {
		this.isShip = isShip;
	}
	public int getSelectStatus() {
		return selectStatus;
	}
	public void setSelectStatus(int selectStatus) {
		this.selectStatus = selectStatus;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public int getUnicalId() {
		return unicalId;
	}
	public void setUnicalId(int unicalId) {
		this.unicalId = unicalId;
	}
	public int getFansId() {
		return fansId;
	}
	public void setFansId(int fansId) {
		this.fansId = fansId;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
}
