package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestPassController {
	@Autowired
	BCryptPasswordEncoder encoder;
	@RequestMapping(value="/create-pass")
	public @ResponseBody  String createPass() {
		return encoder.encode("1234567");
	}
}
