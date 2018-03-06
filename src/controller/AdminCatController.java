package controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.CatDAO;
import dao.ContactDAO;
import dao.UserDAO;
import entity.Cat;
import entity.User;

@Controller
@RequestMapping(value = "/admin/cat")
public class AdminCatController {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CatDAO catDAO;
	@Autowired
	private ContactDAO contactDAO;
	
	@ModelAttribute
	public void addCommon(ModelMap model) {
		String username = AuthController.getUserName();
		User objUserInfro = userDAO.getItem(username);
		model.addAttribute("objLogin", objUserInfro);
		model.addAttribute("numberContact", contactDAO.countSumContactRead());
	}

	@RequestMapping("/index")
	public String index(ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			model.addAttribute("listCat", catDAO.getItems());
			return "admin.cat_admin";
		}
	}
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(@PathVariable("id") int id_cat, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (catDAO.delItem(id_cat) > 0) {
				ra.addFlashAttribute("msg", 3);
			} else {
				ra.addFlashAttribute("msg", 7);
			}
			return "redirect:/admin/cat/index";
		}
	}
	@RequestMapping(value = "/show",produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String show( ModelMap model,
			RedirectAttributes ra, HttpServletRequest request) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			
				return " <h2 id=\"title\">Add Category</h2>\r\n" + 
						"    <form class=\"edit_category\" id=\"edit_category_5\" action=\""+request.getContextPath() +"/admin/cat/add\" accept-charset=\"UTF-8\" method=\"post\"><input name=\"utf8\" type=\"hidden\" value=\"✓\"><input type=\"hidden\" name=\"_method\" value=\"patch\"><input type=\"hidden\" name=\"authenticity_token\" value=\"q9yXbtwgUNgYGsL3v5ITqW9xF9hOhvxA8lkIACbwV99em5kszSP/fC3WJPmlaB+2muuOx/zjoUEWNPf55CWxzA==\">\r\n" + 
						"  \r\n" + 
						"  <div class=\"form-group\">\r\n" + 
						"    <label for=\"category_name\">Name</label>\r\n" + 
						"    <input class=\"form-control\" type=\"text\"  name=\"name\" id=\"category_name\">\r\n" + 
						"  </div>\r\n" + 
						"\r\n" + 
						"  <div class=\"form-group\">\r\n" + 
						"    <label for=\"category_description\">Description</label>\r\n" + 
						"    <textarea class=\"form-control\" name=\"description\" id=\"description\"></textarea>\r\n" + 
						"  </div>\r\n" + 
						"\r\n" + 
						"  <input type=\"submit\" name=\"commit\" value=\"Add Category\" class=\"btn btn-primary\" >\r\n" + 
						"</form>";

		}
	}
	@RequestMapping(value = "/showEdit", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String showEdit( ModelMap model,@RequestParam("cid") int cid,
			RedirectAttributes ra, HttpServletRequest request) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
				Cat objCat = catDAO.getItem(cid);
				return " <h2 id=\"title\">Category Edit</h2>\r\n" + 
						"    <form class=\"edit_category\" action=\""+request.getContextPath() +"/admin/cat/edit/"+objCat.getId()+"\" accept-charset=\"UTF-8\" method=\"post\"><input name=\"utf8\" type=\"hidden\" value=\"✓\"><input type=\"hidden\" name=\"_method\" value=\"patch\"><input type=\"hidden\" name=\"authenticity_token\" value=\"q9yXbtwgUNgYGsL3v5ITqW9xF9hOhvxA8lkIACbwV99em5kszSP/fC3WJPmlaB+2muuOx/zjoUEWNPf55CWxzA==\">\r\n" + 
						"  \r\n" + 
						"  <div class=\"form-group\">\r\n" + 
						"    <label for=\"category_name\">Name</label>\r\n" + 
						"    <input class=\"form-control\" type=\"text\" value=\""+objCat.getName()+"\"  id=\"category_name\" name=\"name\" >\r\n" + 
						"  </div>\r\n" + 
						"\r\n" + 
						"  <div class=\"form-group\">\r\n" + 
						"    <label for=\"category_description\">Description</label>\r\n" + 
						"    <textarea class=\"form-control\" name=\"description\" id=\"description\">"+objCat.getDescription()+"</textarea>\r\n" + 
						"  </div>\r\n" + 
						"\r\n" + 
						"  <input type=\"submit\" name=\"commit\" value=\"Edit Category\" class=\"btn btn-primary\" >\r\n" + 
						"</form>";

		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objCat") Cat objCat, BindingResult rs, ModelMap model,
			RedirectAttributes ra, HttpServletRequest request) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			/*
			 * if (rs.hasErrors()) { return "admin.cat.add"; }
			 */
			System.out.println(objCat.getName());
			/*Cat objCatCheck = catDAO.getItem(objCat.getName());
			System.out.println(objCatCheck.getName());
			if (objCatCheck != null) {
				ra.addFlashAttribute("msg", 8);
				return "redirect:/admin/cat/index";
			} else {*/
				if (catDAO.addItem(objCat) > 0) {
					ra.addFlashAttribute("msg", 1);
				} else {
					ra.addFlashAttribute("msg", 0);
				}
				return "redirect:/admin/cat/index";
			/*}*/
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id_cat, RedirectAttributes ra,
			@Valid @ModelAttribute("objCat") Cat objCat, BindingResult rs, HttpServletRequest request, ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			/*
			 * if (rs.hasErrors()) { return "admin.network.edit"; }
			 */
			Cat objOld = catDAO.getItem(id_cat);
			objCat.setCreate_at(objOld.getCreate_at());
			/*Cat objCheck = catDAO.getItem(objCat.getName());*/
			/*if (objCheck.getName() == "" || objOld.getName().equals(objCat.getName())
					|| objCheck.getName().equals(objOld.getName())) {*/
				if (catDAO.editItem(objCat) > 0) {
					ra.addFlashAttribute("msg", 2);
				} else {
					ra.addFlashAttribute("msg", 7);
				}
				return "redirect:/admin/cat/index";
			/*} else {
				ra.addFlashAttribute("msg", 8);
				return "redirect:/admin/cat/index";
			}*/
		}

	}
}
