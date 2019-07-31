package com.mt.console.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/upload/*")
public class UploadController {

	@RequestMapping(value = "ueditor")
	public void ueditor(HttpServletRequest request, HttpServletResponse response, String action) {
		log.info("upload action: " + action);
		response.setContentType("application/json");
		String rootPath = request.getSession().getServletContext().getRealPath("/");

		try {
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
