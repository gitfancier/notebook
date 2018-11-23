package com.note.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.note.maper.UserMaper;
import com.note.model.User;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView Register() {
		User user = new User();      
		user.setSex(0);
		ModelAndView modelAndView = new ModelAndView("admin/register", "command", user);
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

		
		return "admin/doregister";
		
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String Delete(int id,ModelMap model) {
		//int id=Integer.valueOf(request.getParameter("id"));
		if(id!=0) {
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
		usermaper.deleteUserById(id);
		//session.selectOne( "com.note.maper.UserMaper",user);
        session.commit();
        System.out.println("Delete User...");
        session.close();
        model.addAttribute("message", "删除成功");
        	return "admin/delete";
		}else {
			model.addAttribute("message", "删除失败");
			return "admin/delete";
		}
        }
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String Update(int id,ModelMap map) {
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
		return "admin/update";
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

		
		return "admin/doupdate";
	}
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public String Select(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,
            Map<String,Object> map) {
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
		//session.selectOne( "com.note.maper.UserMaper",user);
		PageHelper.startPage(pn,5);
		List<User> users=usermaper.getUserAll();
		System.out.println("users");//test
		for(int i=0;i<users.size();i++){
            User u = users.get(i);//获取每一个Example对象
            System.out.print("id:"+u.getId());
        }
		//System.out.println(users);
        PageInfo<User> pageInfo = new PageInfo<User>(users,5);
        System.out.println("pageinfo");//test
        List<User> us=pageInfo.getList();
        for(int i=0;i<us.size();i++){
            User u = us.get(i);//获取每一个Example对象
            System.out.print("id:"+u.getId());
        }
        //pageINfo封装了分页的详细信息，也可以指定连续显示的页数
        map.put("pageInfo", pageInfo);
        System.out.println("Select UserAll...");
		return "admin/userlist";
	}
	
}
