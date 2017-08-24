package cn.project.wheel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.wheel.dao.WheelMaterialDao;
import com.jeeplus.modules.wheel.entity.WheelMaterial;


/**
 * MaterialService
 * @author Oliver
 * @version 2017年6月16日
 */
@Service
@Transactional(readOnly = true)
public class WheelMaterialService extends CrudService<WheelMaterialDao,WheelMaterial> {

	@Autowired
	protected WheelMaterialDao materialDao;
		
	public List<WheelMaterial> findList(WheelMaterial material){
		List<WheelMaterial> list = materialDao.findList(material);
		return list;
	}
	
	@Transactional(readOnly = false)
	public void addMaterial(WheelMaterial material){
		materialDao.addMaterial(material);
	}
}
