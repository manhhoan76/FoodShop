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
import org.springframework.web.bind.annotation.RequestParam;

import constant.Defines;
import dao.ContactDAO;
import dao.OrderDAO;
import dao.OrderStatusDAO;
import dao.UserDAO;
import entity.Order;
import entity.User;

@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderStatusDAO orderStatusDAO;

	@ModelAttribute
	public void addCommon(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
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
			int sumOrder = orderDAO.countSumOrder();
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumOrder / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listStatus", orderStatusDAO.getItems());
			model.addAttribute("listOrder", orderDAO.getItems(offset, row_count));
			return "admin.order_admin";
		}
	}

	@RequestMapping({ "/search/{page}/{id}", "/search" })
	public String search(ModelMap model, @PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "id", required = false) Integer id, @RequestParam(value="status_id"  , required = false) int id_status) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (page == null) {
				page = 1;
			}
			if(id == null ) {
				id = id_status;
			}
			int sumUser = orderDAO.countSumSearch(id);
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumUser / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listOrder", orderDAO.getItemsSearch(id, offset, row_count));
			model.addAttribute("id", id);
			model.addAttribute("listStatus", orderStatusDAO.getItems());
			return "admin.order_search";
		}
	}

	@RequestMapping({ "/change/{page}/{id}" })
	public String change(ModelMap model,  @PathVariable(value = "page", required = false) Integer page,
			@PathVariable(value = "id", required = false) Integer id, @RequestParam("id_order_status") int id_status) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			Order objOld = orderDAO.getItem(id);
			orderDAO.change(objOld.getCreate_at(), id, id_status);
			return "redirect:/admin/order/index/"+page;
		}
	}

}
