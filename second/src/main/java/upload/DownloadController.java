package upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {
	@RequestMapping("/filelist")
	public ModelAndView downloadlist() {
		ModelAndView mv = new ModelAndView();
		File f = new File("c:/upload");
		String[] filelist = f.list();
		mv.addObject("filelist", filelist);
		mv.setViewName("upload/filelist");

		return mv;
	}

	@RequestMapping("/filedownload")
	public void filedownload(String onefile, HttpServletResponse response) throws IOException {
		// onefile 이라는 이름의 파일을 C:/UPLOAD 찾는다
		File f = new File("c:/upload/" + onefile);
		int f_length = (int) f.length(); // byte단위

		// 응답할 컨텐츠 다운로드파일
		response.setContentType("application/download");
		response.setContentLength(f_length);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + onefile + "\"");

		OutputStream out = response.getOutputStream();
		FileInputStream f_in = new FileInputStream(f);
		FileCopyUtils.copy(f_in,out);
		f_in.close();
		out.close();
	}

}
