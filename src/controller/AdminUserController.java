package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.ContactDAO;
import dao.UserDAO;
import entity.User;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ContactDAO contactDAO;

	@ModelAttribute
	public void addCommon(ModelMap model) {
		String username = AuthController.getUserName();
		User objUserInfro = userDAO.getItem(username);
		model.addAttribute("objLogin", objUserInfro);
		model.addAttribute("numberContact", contactDAO.countSumContactRead());
	}

	@RequestMapping({ "/index/{page}", "/index" })
	public String index(ModelMap model, @PathVariable(value = "page", required = false) Integer page) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (page == null) {
				page = 1;
			}
			int sumUser = userDAO.countSumUser();
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumUser / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listUser", userDAO.getItems(offset, row_count));
			return "admin.manager_user";
		}
	}
	
	@RequestMapping({ "/search/{page}/{key}", "/search" })
	public String search(ModelMap model, @PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "key", required = false) String key, @RequestParam(value="keyword"  , required = false) String keyword) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (page == null) {
				page = 1;
			}
			if (keyword == null) {
				keyword = "";
			}
			if (key == null) {
				key = keyword;
			}
			int sumUser = userDAO.countSumSearch(key);
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumUser / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listUser", userDAO.getItemsSearch(key, offset, row_count));
			model.addAttribute("key", key);
			return "admin.search_user";
		}
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (id == 1 || id == 4) {
				ra.addFlashAttribute("msg", 3);
				return "redirect:/admin/user/index";
			} else {
				if (userDAO.delItem(id) > 0) {
					ra.addFlashAttribute("msg", 1);
				} else {
					ra.addFlashAttribute("msg", 0);
				}
				return "redirect:/admin/user/index";
			}
		}
	}

	@RequestMapping(value = "/active", method = RequestMethod.POST)
	public @ResponseBody String active(@RequestParam("uid") int id, @RequestParam("uactive") int active, ModelMap model,
			RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {

			if (active == 1) {
				userDAO.block(userDAO.getItem(id).getCreate_at(), id);
				return "<a  class=\"text-danger\" onclick=\"active(" + id
						+ ",0)\"  class=\"btn btn-warning\" href=\"javascript:void(0)\" >Active</a>";
			} else {
				userDAO.active(userDAO.getItem(id).getCreate_at(), id);
				return "<a  class=\"text-danger\" onclick='active(" + id
						+ ",1)'  class='btn btn-success' href='javascript:void(0)' >Block</a>";
			}

		}
	}
}
