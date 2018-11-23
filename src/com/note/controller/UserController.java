package com.note.controller;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.note.maper.UserMaper;
import com.note.model.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView Register() {
		User user = new User();      
		user.setSex(0);
		ModelAndView modelAndView = new ModelAndView("user/register", "command", user);
		return modelAndView;
	}
	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public String Doregister(@ModelAttribute("SpringWeb")User user, 
		      ModelMap model) {
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password",DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		model.addAttribute("truename", user.getTruename());
		model.addAttribute("sex", user.getSex());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("create_time",System.currentTimeMillis());
		model.addAttribute("update_time",System.currentTimeMillis());
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		user.setCreate_time(Long.toString(System.currentTimeMillis()));
		user.setUpdate_time(Long.toString(System.currentTimeMillis()));
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
		UserMaper usermaper=session.getMapper(UserMaper.class);
		usermaper.insertUser(user);
		//session.selectOne( "com.note.maper.UserMaper",user);
        session.commit();
        System.out.println("Insert User...");

		
		return "user/doregister";
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String Update(@RequestParam(value="id",required=true)int id,ModelMap map) {
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
		UserMaper usermaper=session.getMapper(UserMaper.class);
		User user=usermaper.getUserById(id);
		map.put("user",user);
		return "user/update";
	}
	@RequestMapping(value = "/doupdate", method = RequestMethod.POST)
	public String Doupdate(@ModelAttribute("Spring")User user, 
		      ModelMap model) {
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password",DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		model.addAttribute("truename", user.getTruename());
		model.addAttribute("sex", user.getSex());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("create_time",user.getCreate_time());
		model.addAttribute("update_time",System.currentTimeMillis());
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		user.setUpdate_time(Long.toString(System.currentTimeMillis()));
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
		UserMaper usermaper=session.getMapper(UserMaper.class);
		usermaper.updateUser(user);
		//session.selectOne( "com.note.maper.UserMaper",user);
        session.commit();
        System.out.println("Update User...");

		
		return "user/doupdate";
	}
	
}
