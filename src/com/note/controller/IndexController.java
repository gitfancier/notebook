package com.note.controller;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.note.maper.LoginMaper;
import com.note.maper.NoteMaper;
import com.note.model.Note;

@Controller
public class IndexController {
	@RequestMapping(value="local",method=RequestMethod.GET)
	public String Local(HttpSession httpSession) {
		String username= (String) httpSession.getAttribute("userid");
		String password= (String) httpSession.getAttribute("userpw");	
		System.out.println("session_username:"+username);
		System.out.println("session_password"+password);
		if(!(username=="")||!(username==null)) {
			String getBySQL=GetPwd(username);System.out.println(GetPwd(username));
			if(!(getBySQL=="")||!(getBySQL==null)) {
				if(getBySQL.equals(password)) {
					System.out.println(password);
					
					return "index/local";
				}	
				else
					return "login/index";
			}
			else
				return "login/index";
		} 
		else
			return "login/index";		
	}
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String Index(HttpSession httpSession) {
		String username= (String) httpSession.getAttribute("userid");
		String password= (String) httpSession.getAttribute("userpw");	
		System.out.println("session_username:"+username);
		System.out.println("session_password"+password);
		if(!(username=="")||!(username==null)) {
			String getBySQL=GetPwd(username);System.out.println(GetPwd(username));
			if(!(getBySQL=="")||!(getBySQL==null)) {
				if(getBySQL.equals(password))
					System.out.println(password);
			}
		}
		return "index/index";

			
	}
	@RequestMapping(value="write",method=RequestMethod.GET)
	public String Write(HttpSession httpSession) {
		/*
		//String name=(String) httpSession.getAttribute("userid");
		//System.out.println(name);
		try {
			String username= (String) httpSession.getAttribute("userid");
			String password= (String) httpSession.getAttribute("userpw");		
		}catch(NullPointerException e){
			e.printStackTrace();
			return "login/index";
		}*/
		String username= (String) httpSession.getAttribute("userid");
		String password= (String) httpSession.getAttribute("userpw");	
		System.out.println("session_username:"+username);
		System.out.println("session_password"+password);
		if(username==""||username==null) {
			return "login/index";
		}
		else {
			String getBySQL=GetPwd(username);System.out.println(GetPwd(username));
			if(!(getBySQL=="")||!(getBySQL==null)) {
				if(getBySQL.equals(password))
					return "index/write";
				else
					return "login/index";
			}
			else
				return "login/index";
		}
		

	}
	@RequestMapping(value="dowrite",method=RequestMethod.POST)
	public String Dowrite(@RequestParam(value="write",required=true) String write,
			@RequestParam(value="encrypt",required=false) String encrypt,
			@RequestParam(value="secret",required=false) String secret,
			HttpSession httpSession) {
		Note note = new Note();
		System.out.println("write:"+write+"encrypt:"+encrypt+"secret:"+secret);
		
		String username= (String) httpSession.getAttribute("userid");
		String password= (String) httpSession.getAttribute("userpw");	
		System.out.println("session_username:"+username);
		System.out.println("session_password"+password);
		if(username==""||username==null) {
			return "login/index";
		}
		else {
			String getBySQL=GetPwd(username);System.out.println(GetPwd(username));
			if(!(getBySQL=="")||!(getBySQL==null)) {
				if(getBySQL.equals(password))
					note.setUid(username);
				else
					return "login/index";
			}
			else
				return "login/index";	
		}
		note.setNote(write);
		if(encrypt.equals("on")) {
			if(!secret.equals("")) {
				note.setPsw(secret);
			}	
		}
		note.setCreate_time(Long.toString(System.currentTimeMillis()));
		note.setUpdate_time(Long.toString(System.currentTimeMillis()));
		SqlSession session1=SessionFactory();
		NoteMaper usermaper=session1.getMapper(NoteMaper.class);
		usermaper.insertNote(note);
		//session.selectOne( "com.note.maper.UserMaper",user);
	    session1.commit();
	    System.out.println("Insert Note...");
	    System.out.println(
	    "uid:"+note.getUid()
		+"psw:"+note.getPsw()
		+"note:"+note.getNote()
		+"create_time:"+note.getCreate_time()
		+"update_time:"+note.getUpdate_time());
	        
	    return "index/result";	        
	}
	public static SqlSession SessionFactory() {
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
		return session;

	}
	public static String GetPwd(String username) {
		SqlSession session=SessionFactory();
		LoginMaper loginmaper=session.getMapper(LoginMaper.class);
		String password=loginmaper.getPasswordByUsername(username);
		//session.selectOne( "com.note.maper.UserMaper",user);
        //session1.commit();
        System.out.println("Select Pwd...");
		return password;
	}
	 
}
