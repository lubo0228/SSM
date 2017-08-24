package cn.project.wheel.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.modules.game.util.CommonUtil;
import com.jeeplus.modules.game.util.ConfigurationFileHelper;
import com.jeeplus.modules.wheel.dao.WheelGameUserDao;
import com.jeeplus.modules.wheel.entity.WheelGameUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class WheelGameUserService extends CrudService<WheelGameUserDao,WheelGameUser>{

	private static String path = ConfigurationFileHelper.getWebUrl();
	
	@Autowired
	private WheelGameUserDao wheelGameUserDao;
	
	
	public List<WheelGameUser> findList(int unicalId,int infoId,String nickName){
		WheelGameUser wheelGameUser = new WheelGameUser();
		wheelGameUser.setUnicalId(unicalId);
		wheelGameUser.setInfoId(infoId);
		wheelGameUser.setNickName(nickName);
		return wheelGameUserDao.findList(wheelGameUser);
	}
	
	public ExportExcel outExcel(int unicalId,int infoId){
		WheelGameUser wheelGameUser = new WheelGameUser();
		wheelGameUser.setUnicalId(unicalId);
		wheelGameUser.setInfoId(infoId);
		List<WheelGameUser> list = wheelGameUserDao.findList(wheelGameUser);
		List<String> headerList = Lists.newArrayList();
		headerList.add("fansId");
		headerList.add("用户昵称");
		headerList.add("用户头像路径");
		headerList.add("创建时间");
		headerList.add("本次活动获得积分");
		//headerList.add("本次活动获得余额");
		headerList.add("添加时间");
		
		List<List<String>> dataList = Lists.newArrayList();
		for (WheelGameUser gameUser : list) {
			List<String> dataRowList = Lists.newArrayList();
			dataRowList.add(String.valueOf(gameUser.getFansId()));
			dataRowList.add(gameUser.getNickName());
			dataRowList.add(gameUser.getHeadImgUrl());
			dataRowList.add(gameUser.getCreateTime());
			dataRowList.add(String.valueOf(gameUser.getIntegral()));
			//dataRowList.add(String.valueOf(gameUser.getBalance()));
			dataRowList.add(gameUser.getAddTime());
			dataList.add(dataRowList);
		}
		ExportExcel ee = null;
		if(list.size() > 0){
			
			ee = new ExportExcel(list.get(0).getActivityTitle()+"用户信息", headerList);
			
			for (int i = 0; i < dataList.size(); i++) {
				Row row = ee.addRow();
				for (int j = 0; j < dataList.get(i).size(); j++) {
					ee.addCell(row, j, dataList.get(i).get(j));
				}
			}
			
		}else{
			ee = new ExportExcel("用户信息", headerList);
			
			for (int i = 0; i < dataList.size(); i++) {
				Row row = ee.addRow();
				for (int j = 0; j < dataList.get(i).size(); j++) {
					ee.addCell(row, j, dataList.get(i).get(j));
				}
			}
		}
		
		return ee;
	}

	public List<WheelGameUser> searchFans(String unicalId, String nickName) {
		JSONObject j = new JSONObject();
		j.put("unicalid", unicalId);
		j.put("nickName", nickName);
		String json = CommonUtil.dopost(path+"/a/sys/game/searchFans", j.toString());
		if(StringUtils.isNotBlank(json)){			
			List<WheelGameUser> list = new ArrayList<WheelGameUser>();
			JSONArray jsonArray = JSONArray.fromObject(json);
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jobj =  (JSONObject) jsonArray.get(i);
				WheelGameUser w = new WheelGameUser();
				int fansId = Integer.parseInt(String.valueOf(jobj.get("fansid")));
				w.setFansId(fansId);
				String nickname = String.valueOf(jobj.get("nickname"));
				w.setNickName(nickname);
				String avatar = String.valueOf(jobj.get("avatar"));
				w.setHeadImgUrl(avatar);
				list.add(w);
			}
			return list;
		}
		return null;
	}
}
