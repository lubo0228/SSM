/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.project.wheel.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.wheel.entity.WheelMaterial;
/**
 * MaterialDao
 * @version 2017年6月16日
 */
@MyBatisDao
public interface WheelMaterialDao extends CrudDao<WheelMaterial> {
	
	public List<WheelMaterial> findList(WheelMaterial material);
//	public int deleteById(int infoId);
	public void addMaterial(WheelMaterial material);
	
}
