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
import dao.CommentDAO;
import dao.ContactDAO;
import dao.UserDAO;
import entity.User;

@Controller
@RequestMapping(value="/admin/comment")
public class AdminCommentController {
	
	@Autowired 
	private CommentDAO commentDAO;
	@Autowired
	private UserDAO uerDAO;
	@Autowired
	private ContactDAO contactDAO;
	
	@ModelAttribute
	public void addCommon(ModelMap model) {
		String username = AuthController.getUserName();
		User objUserInfro = uerDAO.getItem(username);
		model.addAttribute("objLogin", objUserInfro);
		model.addAttribute("numberContact", contactDAO.countSumContactRead());
	}
	@RequestMapping(value = "/active", method = RequestMethod.POST)
	public @ResponseBody String active(@RequestParam("uid") int id, @RequestParam("uactive") int active, ModelMap model,
			RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			
			if (active == 1) {
				commentDAO.block(commentDAO.getItem(id).getCreate_at(), id);
				return "<a  class=\"text-danger\" onclick=\"active(" + id
						+ ",0)\"  class=\"btn btn-warning\" href=\"javascript:void(0)\" >Slide</a>";
			} else {
				commentDAO.active(commentDAO.getItem(id).getCreate_at(), id);
				return "<a  class=\"text-danger\" onclick='active(" + id
						+ ",1)'  class='btn btn-success' href='javascript:void(0)' >None</a>";
			}


		}
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
			int sumComment = commentDAO.countSumComment();
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumComment / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listComment", commentDAO.getItems(offset, row_count));
			return "admin.manager_comment";
		}
	 } 
	@RequestMapping(value = "/del/{id_comment}", method = RequestMethod.GET)
	public String del(@PathVariable("id_comment") int id_comment, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (commentDAO.delItem(id_comment) > 0) {
				ra.addFlashAttribute("msg", 3);
			} else {
				ra.addFlashAttribute("msg", 7);
			}
			return "redirect:/admin/comment/index";
		}
	}
}
