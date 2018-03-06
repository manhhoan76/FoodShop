package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AuthController {
	@Autowired
	public HttpSession session;
	@RequestMapping(value="/login")
	public String login(ModelMap model) {
		session.removeAttribute("cart");
		return "auth.login";
	}
	
	public static String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		try {
			Object principal = auth.getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			}

		} catch (Exception e) {
			 username = "";
		}
		return username;
	}
}
