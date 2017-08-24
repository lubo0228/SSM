package cn.project.wheel.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.game.util.ConfigurationFileHelper;
import com.jeeplus.modules.wheel.entity.WheelMaterial;
import com.jeeplus.modules.wheel.service.WheelMaterialService;

@Controller
@RequestMapping(value = "${adminPath}/wheel/uploadController")
public class WheelUploadController extends BaseController {

	@Autowired
	protected WheelMaterialService materialService;

	private static String urlpath = ConfigurationFileHelper.getSavePicUrl();

	@RequestMapping(value = "/uploadImg")
	@ResponseBody
	public String uploadImg(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 1.request.getInputStream();写一个输入流接收照片
		String unicalid = (String) session.getAttribute("unicalid");
		String fileType = "image";
		InputStream ins = request.getInputStream();
		// System.err.println(ins);
		BufferedInputStream bis = new BufferedInputStream(ins);
		byte[] b = new byte[8192];

		// 2.存放到某一个文件下，没有文件夹时新建文件夹，没有文件时新建文件
		// String urlpath=(String)makeFilePath(unicalid,
		// fileType).get("urlpath");
		String filePath = (String) makeFilePath(unicalid, fileType).get("filePath");
		String savePicPath = (String) makeFilePath(unicalid, fileType).get("savePicPath");
		File pakageFile = new File(savePicPath);
		if (!pakageFile.exists() && !pakageFile.isDirectory()) {
			pakageFile.mkdirs();// 创建文件夹
		}

		String fileName = (String) makeFilePath(unicalid, fileType).get("fileName");

		// 2.1接收流
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(savePicPath + "/" + fileName));
		int i;
		while ((i = bis.read(b)) != -1) {
			out.write(b, 0, i);
		}

		// 保存到Material
		WheelMaterial material = new WheelMaterial();
		material.setFileName(fileName);
		material.setFilePath(filePath + "/" + fileName);
		material.setUnicalId(Integer.parseInt(unicalid));
		material.setFileType("image");
		materialService.addMaterial(material);

		out.flush();
		out.close();
		bis.close();

		return filePath + "/" + fileName;

	}

	@RequestMapping(value = "/uploadMusic")
	@ResponseBody
	public String uploadMusic(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 1.request.getInputStream();写一个输入流接收照片
		String unicalid = (String) session.getAttribute("unicalid");
		String fileType = "voice";
		InputStream ins = request.getInputStream();
		// System.err.println(ins);
		BufferedInputStream bis = new BufferedInputStream(ins);
		byte[] b = new byte[8192];

		// 2.存放到某一个文件下，没有文件夹时新建文件夹，没有文件时新建文件
		// String urlpath=(String)makeFilePath(unicalid,
		// fileType).get("urlpath");
		String filePath = (String) makeFilePath(unicalid, fileType).get("filePath");
		String savePicPath = (String) makeFilePath(unicalid, fileType).get("savePicPath");
		File pakageFile = new File(savePicPath);
		if (!pakageFile.exists() && !pakageFile.isDirectory()) {
			pakageFile.mkdirs();// 创建文件夹
		}

		String fileName = (String) makeFilePath(unicalid, fileType).get("fileName");

		// 2.1接收流
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(savePicPath + "/" + fileName));
		int i;
		while ((i = bis.read(b)) != -1) {
			out.write(b, 0, i);
		}

		// 保存到Material
		WheelMaterial material = new WheelMaterial();
		material.setFileName(fileName);
		material.setFilePath(filePath + "/" + fileName);
		material.setUnicalId(Integer.parseInt(unicalid));
		material.setFileType("voice");
		material.setServersFilePath(savePicPath);
		materialService.addMaterial(material);

		out.flush();
		out.close();
		bis.close();

		return "ok";

	}

	public HashMap<String, Object> makeFilePath(String unicalid, String fileType) {

		// String urlpath="/opt/tomcat/webapps";
		String filePath = "/vicmob/WheelGame/unicalid-" + unicalid + "/" + fileType + "/" + makeDateStr();
		String savePicPath = urlpath + filePath;
		String fileName = "";
		if ("voice".equals(fileType)) {
			fileName = String.valueOf(System.currentTimeMillis()) + ".mp3";
		}
		if ("image".equals(fileType)) {
			fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		// map.put("urlpath", urlpath);
		map.put("filePath", filePath);
		map.put("savePicPath", savePicPath);
		map.put("fileName", fileName);
		return map;

	}

	public String makeDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dates = sdf.format(new Date());
		return dates;
	}

}
