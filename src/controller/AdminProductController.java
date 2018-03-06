package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.CatDAO;
import dao.ContactDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Product;
import entity.User;

@Controller
@RequestMapping(value = "/admin/product")
public class AdminProductController {
	public static final String DIR_FILES = "files";
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private CatDAO catDAO;

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
		if (page == null) {
			page = 1;
		}
		int sumProduct = productDAO.countSumproduct();
		int row_count = 12;
		int sumPage = (int) Math.ceil((float) sumProduct / row_count);
		model.addAttribute("sumPage", sumPage);
		model.addAttribute("page", page);
		int offset = (page - 1) * row_count;
		model.addAttribute("listProduct", productDAO.getItems(offset, row_count));
		return "admin.product_admin";
	}

	@SuppressWarnings("unused")
	@RequestMapping({ "/search/{page}/{name}/{id_cat }/{priceHight }", "/search" })
	public String search(ModelMap model, @PathVariable(value = "page", required = false) Integer page,
			@PathVariable(value = "name", required = false) String name,
			@PathVariable(value = "id_cat", required = false) Integer id_cat,
			@PathVariable(value = "priceHight", required = false) Integer price,
			@RequestParam(value = "name", required = false) String namePr,
			@RequestParam(value = "category", required = false) int category,
			@RequestParam(value = "price", required = false) int priceHight) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (page == null) {
				page = 1;
			}
			if (namePr == null) {
				namePr = "";
			}
			if (name == null) {
				name = namePr;
			}
				id_cat = category;
			if (price == null) {
				price = priceHight;
			}
			int sumUser =0;
			if(id_cat == 0) {
				sumUser = productDAO.countSumSearchNotCat(name, price);
			} else {
				sumUser = productDAO.countSumSearch(name,id_cat, price);
			}
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumUser / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			if(id_cat == 0) {
				model.addAttribute("listProduct", productDAO.getItemsSearchNotCat(name, price, offset, row_count));
			} else {
				model.addAttribute("listProduct", productDAO.getItemsSearch(name, id_cat, price, offset, row_count));
			}
			model.addAttribute("name", name);
			model.addAttribute("id_cat", id_cat);
			model.addAttribute("priceHight", price);
			return "admin.search_product";
		}
	}

	@RequestMapping({ "/add" })
	public String add(ModelMap model) {
		String username = AuthController.getUserName();
		model.addAttribute("listCat", catDAO.getItems());
		return "admin.add_product";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objProduct") Product objProduct, BindingResult rs, ModelMap model,
			RedirectAttributes ra, HttpServletRequest request, @RequestParam("image") CommonsMultipartFile CMF) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			/*
			 * if (rs.hasErrors()) { return "admin.user.add"; }
			 */
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_FILES;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			try {
				CMF.transferTo(new File(dirPath + File.separator + CMF.getOriginalFilename()));
				System.out.println("thành công");
			} catch (IOException e) {
				System.out.println("có lỗi");
			}
			objProduct.setImage(CMF.getOriginalFilename());
			if (productDAO.addItem(objProduct) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/admin/product/index";
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			model.addAttribute("objProduct", productDAO.getItem(id));
			model.addAttribute("listCat", catDAO.getItems());
			return "admin.edit_product";
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, RedirectAttributes ra,
			@Valid @ModelAttribute("objProduct") Product objProduct, BindingResult rs, HttpServletRequest request,
			@RequestParam("image") CommonsMultipartFile CMF, ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			/*
			 * if (rs.hasErrors()) { return "admin.new.edit"; }
			 */
			Product objOld = productDAO.getItem(id);
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_FILES;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			if (!CMF.getOriginalFilename().isEmpty()) {
				if (!"".equals(objOld.getName())) {
					String urlFileDel = dirPath + File.separator + objOld.getName();
					File delFile = new File(urlFileDel);
					delFile.delete();
				}
				try {
					CMF.transferTo(new File(dirPath + File.separator + CMF.getOriginalFilename()));
					objProduct.setImage(CMF.getOriginalFilename());
					System.out.println("thành công");
				} catch (IOException e) {
					System.out.println("có lỗi");
				}
			} else {
				objProduct.setImage(objOld.getImage());
			}
			objProduct.setCreate_at(objOld.getCreate_at());
			if (productDAO.editItem(objProduct) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/admin/product/index";
		}

	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (userDAO.getItem(username).getId() == 1 || userDAO.getItem(username).getId() == 4) {
				if (productDAO.delItem(id) > 0) {
					ra.addFlashAttribute("msg", 1);
				} else {
					ra.addFlashAttribute("msg", 0);
				}
			}
			return "redirect:/admin/product/index";
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
				productDAO.block(productDAO.getItem(id).getCreate_at(), id);
				return "<a   onclick=\"active(" + id
						+ ",0)\"  class=\"inline-btn btn btn-info\" href=\"javascript:void(0)\" >Trend</a>";
			} else {
				productDAO.active(productDAO.getItem(id).getCreate_at(), id);
				return "<a   onclick='active(" + id
						+ ",1)'  class='inline-btn btn btn-warning' href='javascript:void(0)' >None</a>";
			}

		}
	}

}
