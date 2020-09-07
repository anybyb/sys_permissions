package com.managesys.permissions.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.basic.common.utils.BasicResult;
import com.basic.common.utils.ResultCommons;
import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * 生成验证码
 *
 */
@RestController
@RequestMapping(value = "/authCode")
public class AuthCosdeController {
	private static final String AUTH_CODE = "authCode";
	@Autowired
	DefaultKaptcha defaultKaptcha;
    
	
	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
	public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");
		ServletOutputStream outputStream  = null;
		try {
			byte[] captchaChallengeAsJpeg = null;
			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			String createText = defaultKaptcha.createText();// 生成验证码字符串
			request.getSession().setAttribute(AUTH_CODE, createText);//存入到session
			// 字符验证码转为图片
			BufferedImage image = defaultKaptcha.createImage(createText);
			ImageIO.write(image, "jpg", jpegOutputStream);
			captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
			outputStream = response.getOutputStream();
			outputStream.write(captchaChallengeAsJpeg);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
   
	
	/**
	 * 校对验证码
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/vrifyAuthCode", method = RequestMethod.GET)
	public BasicResult vrifyAuthCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		String rightCode = (String) httpServletRequest.getSession().getAttribute(AUTH_CODE);
		String tryCode = httpServletRequest.getParameter("authCode");
		if (!rightCode.equals(tryCode)) {	
              return ResultCommons.setSuccessMessage();  
		} else {
			   return ResultCommons.setErrorMessage("验证码错误！");
		}

	}

}
