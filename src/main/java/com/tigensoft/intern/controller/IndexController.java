package com.tigensoft.intern.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;

@Controller
public class IndexController {

	@RequestMapping(value="/index.do")
	public String index(final HttpServletResponse response, final HttpServletRequest request, final HttpSession session) throws Exception {

		//RSA알고리즘 사용하여 키 쌍 생성하는 객체 생성 크기 2048비트
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);

		//키 쌍 생성, RSA 키 처리가능한 KeyFactory 객체 생성
		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		//공개키, 개인키 가져오기
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		//세션에 개인키 저장
		session.setAttribute("_RSA_WEB_Key", privateKey);

		//공개키 사양 가져오기
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

		//공개키 modulus, exponent 16진수로 설정 후 클라이언트로 보낼 요청으로 저장
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		request.setAttribute("publicKeyModulus", publicKeyModulus);
		request.setAttribute("publicKeyExponent", publicKeyExponent);

		return "/login-form";
	}

}
