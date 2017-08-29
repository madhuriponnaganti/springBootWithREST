package com.fileupload.controller;

import static com.fileupload.constants.MasterConstants.UPLOAD_PATH;
import static com.fileupload.constants.MasterConstants.UPLOAD_REDIRECT_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fileupload.service.FileUpload;

@Controller
public class FileUploadController {

	@Autowired
	FileUpload fileUpload;

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping(UPLOAD_PATH) // new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") final MultipartFile file, final RedirectAttributes redirectAttributes)
			throws Exception {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		redirectAttributes.addFlashAttribute("message", fileUpload.uploadFile(file));

		return "redirect:/uploadStatus";
	}

	@GetMapping(UPLOAD_REDIRECT_PATH)
	public String uploadStatus() {
		return "uploadStatus";
	}

}