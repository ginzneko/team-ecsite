package jp.co.internous.rainbow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import jp.co.internous.rainbow.model.domain.MstUser;
import jp.co.internous.rainbow.model.form.UserForm;
import jp.co.internous.rainbow.model.mapper.MstUserMapper;
import jp.co.internous.rainbow.model.session.LoginSession;
/**
 * register_user.htmlで行われる処理を定義するクラス
 * @author
 */
@Controller
@RequestMapping("/rainbow/user")
public class UserController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired 
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession",loginSession);
		
		return "register_user";
	}
	
     /**
      * register_user.htmlにて重複確認ボタンが押下された際
      * に機能する関数
      * @param f UserForm ユーザーフォーム
      * @return 値が0以上のユーザーIDを返却
      */
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		int count = userMapper.findCountByUserName(f.getUserName());
		return count > 0;
	}
	
	/**
	 * register_user.htmlにて登録ボタンが押下された際
	 * に機能する関数
	 * @param f UserForm　ユーザーフォーム
	 * @return 値が0以上のユーザーIDを返却
	 */
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		int count = userMapper.insert(user);
		return count > 0;
	}
};