package jp.co.internous.rainbow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.rainbow.model.domain.MstUser;
import jp.co.internous.rainbow.model.mapper.MstUserMapper;
import jp.co.internous.rainbow.model.session.LoginSession;
/**
 * my_page.htmlで行われる処理を定義するクラス
 * @author 
 */
@Controller
@RequestMapping("/rainbow/mypage")
public class MyPageController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * ユーザーとパスワードの情報を取得する関数
	 * @param m ユーザー、ログインセッション
	 * @return マイページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		m.addAttribute("user",user);
	    m.addAttribute("loginSession", loginSession);	
		return "my_page";
	}
}