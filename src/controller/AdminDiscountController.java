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
import dao.DiscountDAO;
import dao.UserDAO;
import entity.Discount;
import entity.User;

@Controller
@RequestMapping(value="/admin/discount")
public class AdminDiscountController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private DiscountDAO discountDAO;
	
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
			int sumDis = discountDAO.countSumDiscount();
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumDis / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listDis", discountDAO.getItems(offset, row_count));
			return "admin.manager_discount";
		}
	 } 
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (discountDAO.delItem(id) > 0) {
				ra.addFlashAttribute("msg", 3);
			} else {
				ra.addFlashAttribute("msg", 7);
			}
			return "redirect:/admin/discount/index";
		}
	}
	@RequestMapping(value = "/add")
	public String help() {
		
		return "admin.add_discount";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("longDis") int longDis,@RequestParam("percent") int percent,@RequestParam("number") int number, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			for (int i = 0; i < number; i++) {
				String name = makeDiscount(longDis);
				Discount obj = new Discount(0, name, 0, percent, null);
				discountDAO.addItem(obj) ;
			}
		}
		return "redirect:/admin/discount/index";
	}
	
	public String makeDiscount(int dodai) {
		String ketqua = "";
        String hoa = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String thuong = hoa.toLowerCase();
        String so = "1234567890";
        String randomchuoi = "";
            randomchuoi = hoa + thuong + so;
        for (int i = 0; i < dodai; i++) {
            int temp = (int) Math.round(Math.random() * randomchuoi.length());
            ketqua += randomchuoi.charAt(temp);
        }
        return ketqua;
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
