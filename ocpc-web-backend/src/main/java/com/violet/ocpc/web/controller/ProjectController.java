package com.violet.ocpc.web.controller;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.violet.ocpc.web.common.contants.ExceptionConstants;
import com.violet.ocpc.web.common.dto.BaseResponseDto;
import com.violet.ocpc.web.facade.request.ProjectRequest;
import com.violet.ocpc.web.facade.response.ProjectFileResponse;
import com.violet.ocpc.web.facade.response.ProjectQueryResponse;
import com.violet.ocpc.web.facade.response.ProjectResponse;
import com.violet.ocpc.web.facade.response.UserSettingResponse;
import com.violet.ocpc.web.holder.ProjectFileHolder;
import com.violet.ocpc.web.service.ImgProcessService;
import com.violet.ocpc.web.service.ProjectFileService;
import com.violet.ocpc.web.service.ProjectService;
import com.violet.ocpc.web.service.UserSettingService;

/**
 * @author HYJ
 *
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	
	@Value("${project.data.file.path}")
	private String baseFilePath;
	
	@Value("${inbound.file.path}")
	private String inboundFile;
	
	@Value("${outbound.file.path}")
	private String outboundFile;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserSettingService userSettingService;
	
	@Autowired
	private ProjectFileService projectFileService;
	
	@Autowired
	private ImgProcessService imgProcessService;
	
	@PostMapping(value="/create/init")
	public BaseResponseDto initCreate(BigDecimal userOid)
	{
		UserSettingResponse userSettingRes = null;
		try {
			userSettingRes = userSettingService
					.queryInitCreateProjectData(userOid);
			
		} catch (Exception e) {
			LOGGER.error("!!==> initCreate: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		return userSettingRes;
	}
	
	@PostMapping(value="verify/name")
	public BaseResponseDto verifyProjectName(String projectName) 
	{
		ProjectResponse projRes = new ProjectResponse();
		try {
			projRes = projectService.verifyProjectName(projectName);
			LOGGER.info("==> projRes : " + JSONObject.toJSONString(projRes));
			
		} catch (Exception e) {
			LOGGER.error("!!==> verifyProjectName: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		
		return projRes;
	}
	
	@PostMapping("/create/save")
	public BaseResponseDto saveProject(ProjectRequest projectReq)
	{
		ProjectResponse projRes = new ProjectResponse();
		try {
			this.printRequest(projectReq);
			projRes = projectService.addSaveProject(projectReq);
			LOGGER.info("==> saveProject res: " + JSONObject.toJSONString(projRes));
			
		} catch (Exception e) {
			LOGGER.error("!!==> saveProject: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		return projRes;
	}
	
	@PostMapping("/create/upload")
	public BaseResponseDto upload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes, 
			HttpServletRequest request) {
		ProjectFileResponse fileRes = new ProjectFileResponse();
		
		String defMark = request.getParameter("defMark");
		String newName = request.getParameter("newFileName");
		String projOidStr = request.getParameter("projectOid");
		
		String tempPath = inboundFile + "/" + defMark + "/grayProcSource/" + projOidStr + "/";
		if(file.isEmpty())
		{
			fileRes.setRetCode(EMPTY_PARAM);
			fileRes.setRetMsg("未选择文件!");
			redirectAttributes.addFlashAttribute("message", "请至少上传一个文件 !");
			return fileRes;
		}
		
		try {
			byte[] bytes = file.getBytes();
			LOGGER.info("-------upload filePath: " + tempPath);
			
			File targetFile = new File(tempPath);
			LOGGER.info(targetFile.getAbsolutePath());
			if(!targetFile.exists())
			{
				targetFile.mkdirs();
			}
			
			LOGGER.info("---------getOriginalFilename-------" + file.getOriginalFilename());
			
			Path path = Paths.get(tempPath + newName);
			Files.write(path, bytes);
			
			redirectAttributes.addFlashAttribute("message", "你成功上传了文件: " 
					+ file.getOriginalFilename());
			
			// 生成灰度图
			String grayscalePath = outboundFile + File.separator + defMark 
					+ File.separator + "grayscale" + File.separator + projOidStr + File.separator;
			File grayFilePath =  new File(grayscalePath);
			if(!grayFilePath.exists())
			{
				grayFilePath.mkdirs();
			}
			
			String grayscaleName = "SG_" + newName;
			String sourceFileStr = tempPath + newName;
			int totalLine = imgProcessService.grayImageNoRtn(4, sourceFileStr, grayscalePath + grayscaleName);

			// 保存数据库
			ProjectFileHolder projectFileHolder = new ProjectFileHolder();
			projectFileHolder.setFilename(file.getOriginalFilename());
			projectFileHolder.setNewFilename(newName);
			projectFileHolder.setFilePath(tempPath+newName);
			projectFileHolder.setProjOid(new BigDecimal(projOidStr));
			projectFileHolder.setGrayscale(grayscalePath + grayscaleName);
			if(totalLine > 0)
			{
				projectFileHolder.setRecommendLine(totalLine/2);
			}
			projectFileService.addProjectFile(projectFileHolder);
			
			fileRes.setFilename(file.getOriginalFilename());
			fileRes.setFilePath(tempPath + newName);
			fileRes.setNewFilename(newName);
			fileRes.setRetCode(SUCCESS);
			fileRes.setRetMsg("成功!");
			
		} catch (Exception e) {
			LOGGER.error("!!==> upload: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		return fileRes;
	}
	
	@PostMapping("/query/list")
	public BaseResponseDto queryProjectList(ProjectRequest proReq)
	{
		ProjectQueryResponse projRes = null;
		try {
			projRes = projectService.queryProjectList(proReq);
			LOGGER.info("==> queryProjectList res: " + JSONObject.toJSONString(projRes));
		} catch (Exception e) {
			LOGGER.error("!!==> queryProjectList: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		
		return projRes;
	}
	
	@PostMapping("/query/info")
	public BaseResponseDto queryProjectInfo(BigDecimal projectOid)
	{
		ProjectQueryResponse projRes = null;
		try {
			projRes = projectService.queryProjectInfo(projectOid);
			LOGGER.info("==> queryProjectInfo res: " + JSONObject.toJSONString(projRes));
		} catch (Exception e) {
			LOGGER.error("!!==> queryProjectInfo: " + e.getMessage());
			return new BaseResponseDto(ExceptionConstants.CALL_SERVICE_FAILED, e.getMessage());
		}
		
		return projRes;
	}
}
