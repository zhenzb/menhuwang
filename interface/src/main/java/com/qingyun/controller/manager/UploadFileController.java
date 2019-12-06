
package com.qingyun.controller.manager;

import com.handongkeji.util.Constants;
import com.handongkeji.util.JsonBean;
import com.handongkeji.util.StringUtil;
import com.handongkeji.util.UploadFileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tool")
public class UploadFileController {
	protected static final Log logger = LogFactory.getLog(UploadFileController.class);

	@RequestMapping(value = "uploadpro")
	@ResponseBody
	public JsonBean uploadpro(JsonBean result, HttpServletRequest request,
							  String getfile) throws Exception {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = null;
		try {
			file = multiRequest.getFile("file");
			if (file == null && !StringUtil.hasNullOrEmpty(getfile)) {
				file = multiRequest.getFile(getfile);
			}
			if (file == null) {
				file = multiRequest.getFile("file_mutil");
				if (file == null) {
					throw new Exception();
				}
			}
		} catch (Exception e1) {
			result.setMessage(Constants.EXECUTION_FAILURE_MESSAGE);
			result.setStatus(Constants.$Failure);
			return result;
		}
		String name = file.getOriginalFilename();
		String extName = name.substring(name.lastIndexOf("."));
		// 格式支持
		int filesupport = 1;
		if (".BMP".equalsIgnoreCase(extName) || ".PNG".equalsIgnoreCase(extName) || ".JPEG".equalsIgnoreCase(extName)
				|| ".JPG".equalsIgnoreCase(extName) || ".MP4".equalsIgnoreCase(extName)
				|| ".3GP".equalsIgnoreCase(extName) || ".GIF".equalsIgnoreCase(extName)

		) {
			filesupport = 1;
		}
		if (filesupport != 1) {
			result.setMessage(Constants.UNSUPPORT_FILEFORMAT_MSG);
			result.setStatus(Constants.UNSUPPORT_FILEFORMAT_CODE);// 407
			return result;
		}
		// 变量
		Map<String, Object> map = new HashMap<>();
		if (!file.isEmpty()) {
			try {
				map = UploadFileUtil.upload(file);
				if (map == null) {
					throw new Exception("返回结果map为null");
				}
			} catch (Exception e1) {
				logger.info("上传文件处理报错：" + e1.getMessage());
				result.setMessage(Constants.EXECUTION_FAILURE_MESSAGE);
				result.setStatus(Constants.$Failure);
				return result;
			}
			String path = map.get("imgName").toString().replaceAll("\\\\", "/");
			result.setData(path);
			result.setMessage(Constants.EXECUTION_SUCCESS_MESSAGE);
			result.setStatus(Constants.$Success);
		} else {
			result.setMessage(Constants.EXECUTION_FAILURE_MESSAGE);
			result.setStatus(Constants.$Failure);

		}

		return result;
	}
}

