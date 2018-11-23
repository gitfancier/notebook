package com.note.controller;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.note.maper.LoginMaper;
import com.note.maper.UserMaper;
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Login() {
		return "login/index";
	}
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String Dologin(@RequestParam(value="username",required=true)String username,
			@RequestParam(value="password")String password,
			HttpSession httpSession) {
		SqlSessionFactory sqlSessionFactory;
	    Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("conf.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		LoginMaper loginmaper=session.getMapper(LoginMaper.class);
		//session.selectOne( "com.note.maper.UserMaper",user);
		String sqlPassword=loginmaper.getPasswordByUsername(username);
		System.out.println("Select psassword...");
		System.out.println("sqlPassword"+sqlPassword);
        String cryptPassword=DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("cryptPassword:"+cryptPassword);
        if(cryptPassword.equals(sqlPassword)) {
        	httpSession.setAttribute("userid", username);
        	System.out.println("set_session");
        	httpSession.setAttribute("userpw", cryptPassword);
        }
        
		return "login/test2";
	}
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public void Loginout(HttpSession httpSession) {
		httpSession.setAttribute("userid", "");
    	httpSession.setAttribute("userpw", "");
    	System.out.println("log out");
		return;
	}
	@RequestMapping(value="islogin",method=RequestMethod.GET)
	public void isLogin() {
		HttpSession session = getSession();
		String username=(String) session.getAttribute("userid");
		String password=(String) session.getAttribute("userpw");
		System.out.println("session_username:"+username);
		System.out.println("session_password"+password);
		return;
	}
	public static HttpSession getSession() { 
	    HttpSession session = null; 
	    try { 
	        session = getRequest().getSession(); 
	    } catch (Exception e) {} 
	        return session; 
	} 
	public static HttpServletRequest getRequest() { 
	    ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
	    return attrs.getRequest(); 
	} 
	    
}
