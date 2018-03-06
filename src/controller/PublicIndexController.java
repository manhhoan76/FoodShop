package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import dao.CommentDAO;
import dao.ContactDAO;
import dao.DiscountDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.SlideDAO;
import dao.SuggestDAO;
import dao.UserDAO;
import entity.Cart;
import entity.Cat;
import entity.Comment;
import entity.Contact;
import entity.Discount;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.Slide;
import entity.Suggest;
import entity.User;

@Controller
public class PublicIndexController {
	public static final String DIR_FILES = "files";
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private SuggestDAO suggestDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	@Autowired
	private SlideDAO slideDAO;
	@Autowired
	private DiscountDAO discountDAO;
	
	@ModelAttribute
	public void addCommon(ModelMap model) {
		String username = AuthController.getUserName();
		@SuppressWarnings("unchecked")
		ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");
		boolean check = true;
		if (cart == null) {
			cart = new ArrayList<>();
		}
		session.setAttribute("cart", cart);
		User objUserInfro = userDAO.getItem(username);
		model.addAttribute("objLogin", objUserInfro);
		model.addAttribute("numberContact", contactDAO.countSumContactRead());
		model.addAttribute("numberCart", cart.size());
	}

	@RequestMapping(value = "/lien-he")
	public String contact() {

		return "public.contact";
	}

	@RequestMapping(value = "/goi-y-san-pham")
	public String sugguest() {

		return "public.sugguest";
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
	
	@RequestMapping({ "/chi-tiet-don-hang/{id}/{page}", "/chi-tiet-don-hang/{id}" })
	public String detaiOrder(ModelMap model, @PathVariable("id") Integer id,
			@PathVariable(value = "page", required = false) Integer page) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (page == null) {
				page = 1;
			}
			int sumDetail = orderDetailDAO.countSumorder_details(id);
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumDetail / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			ArrayList<OrderDetail> obj = (ArrayList<OrderDetail>) orderDetailDAO.getItems(id, offset, row_count);
			model.addAttribute("listDetail", obj);
			model.addAttribute("totalProductDetail", orderDetailDAO.getItems(id).size());
			model.addAttribute("sumPriceDetail", orderDAO.getItem(id).getSumPrice());
			model.addAttribute("idOrder", id);
			return "public.detail_order";
		}

	}

	@RequestMapping(value = "/goi-y-san-pham", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objSugguest") Suggest objSugguest, BindingResult rs, ModelMap model,
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
			objSugguest.setId_user(userDAO.getItem(username).getId());
			objSugguest.setImage(CMF.getOriginalFilename());
			if (suggestDAO.addItem(objSugguest) > 0) {
				ra.addFlashAttribute("msg", 4);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/trang-ca-nhan/" + objSugguest.getId_user();
		}
	}

	@RequestMapping(value = "/cancel/{id}")
	public String cancel(ModelMap model, @PathVariable("id") int id) {
		Order objOld = orderDAO.getItem(id);
		orderDAO.cancelItem(id);
		return "redirect:/trang-ca-nhan/" + objOld.getId_user();
	}

	@RequestMapping(value = "/lien-he", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objContact") Contact objContact, ModelMap model, RedirectAttributes ra,
			HttpServletRequest request) {
		String username = AuthController.getUserName();
		if (username.equals("")) {
			return "redirect:/login";
		} else {
			if (contactDAO.addItem(objContact) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/lien-he";
		}
	}

	@RequestMapping(value = "/gioi-thieu")
	public String introduction() {

		return "public.introduction";
	}

	@RequestMapping(value = "/dang-ky")
	public String signup() {

		return "auth.signup";
	}

	@RequestMapping(value = "/tro-giup")
	public String help() {

		return "public.help";
	}

	@RequestMapping(value = "/checkOut")
	public String checkOut(ModelMap model, @RequestParam("discount") String discount, @RequestParam("pay") int pay,
			@RequestParam("username") String name, @RequestParam("phone") String phone,
			@RequestParam("address") String address) {
		String username = AuthController.getUserName();
		if (username.equals("")) {
			return "redirect:/login";
		} else {
			Discount objDis = null;
			try {
				objDis = discountDAO.getItemName(discount);
			} catch (Exception e) {
				objDis = null;
			}
			String payment = "";
			if (pay == 1) {
				payment = "Tranfer";
			} else {
				payment = "PayPal";
			}
			Order objOrde = new Order(0, userDAO.getItem(username).getId(), 1, null, address, name, phone, null, 0,
					payment);
			orderDAO.addItem(objOrde);
			int sum=0;
			@SuppressWarnings("unchecked")
			ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
			for (Cart item : listcart) {
				sum = sum + item.getPrice() * item.getQuantity();
			}
			if (objDis != null ) {
				if (objDis.getUsed() == 0) {
					sum = sum - (int)(sum * objDis.getPercent()) / 100;
					discountDAO.used(objDis.getCreate_at(), objDis.getId());
					model.addAttribute("msg", 1);
				} else {
					model.addAttribute("msg", 0);
				}
				
			}
			model.addAttribute("sum", sum);

		}
		if (pay == 1) {
			return "public.tranfer";
		} else {
			return "public.paypal";
		}
	}

	@RequestMapping(value = "/pay")
	public String pay() {

		return "public.pay";
	}

	@RequestMapping(value = "/save")
	public String save() {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
			for (Cart item : listcart) {
				OrderDetail obj = new OrderDetail(0, item.getId_product(), orderDAO.getItemByLastID(userDAO.getItem(username).getId()).getId(), item.getQuantity(), item.getPrice(), null, item.getName(), item.getImage());
				orderDetailDAO.addItem(obj);
			}
			session.removeAttribute("cart");
			return "redirect:/";
		}
		
	}

	@RequestMapping(value = "/gio-hang")
	public String cart() {
		@SuppressWarnings("unchecked")
		ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
		float sum = 0;
		for (Cart item : listcart) {
			sum = sum + item.getPrice() * item.getQuantity();
		}
		int numberCart = listcart.size();
		session.setAttribute("sumTotal", sum);
		session.setAttribute("totalProduct", numberCart);
		return "public.cart_product";
	}

	@RequestMapping(value = "/gio-hang", method = RequestMethod.POST)
	public @ResponseBody int cart(HttpSession session, @RequestParam("pid") int id, ModelMap model) {
		String username = AuthController.getUserName();
		Product objProduc = productDAO.getItem(id);
		Cart objcart = new Cart(objProduc.getId(), objProduc.getName(), objProduc.getImage(), 1, objProduc.getPrice());
		@SuppressWarnings("unchecked")
		ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
		boolean check = true;
		if (listcart == null) {
			listcart = new ArrayList<>();
		}
		for (Cart item : listcart) {
			if (item.getId_product() == objProduc.getId()) {
				item.setQuantity(item.getQuantity() + 1);
				check = true;
			} else {
				check = false;
			}
		}
		if (check == false || listcart.size() == 0) {
			listcart.add(objcart);
		}
		int numberCart = listcart.size();
		return numberCart;
	}

	@RequestMapping(value = "/downNumber", method = RequestMethod.POST)
	public @ResponseBody String down(@RequestParam("nnumber") int number, @RequestParam("nid") int id, ModelMap model,
			RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			number = number - 1;
			if (number <= 0) {
				number = 0;
			}
			@SuppressWarnings("unchecked")
			ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
			for (Cart item : listcart) {
				if (id == item.getId_product()) {
					item.setQuantity(number);
				}
			}
			return " <input type=\"text\" class=\"form-control text-center only-number\" id=\"numberQuantity-" + id
					+ "\" value=\"" + number + "\">";
		}
	}

	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public @ResponseBody float total(ModelMap model, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		float sum = 0;
		@SuppressWarnings("unchecked")
		ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
		for (Cart item : listcart) {
			sum = sum + item.getPrice() * item.getQuantity();
		}
		session.setAttribute("dola", sum / 20000);
		session.setAttribute("sumTotal", sum);
		return sum;
	}

	@RequestMapping(value = "/upNumber", method = RequestMethod.POST)
	public @ResponseBody String up(@RequestParam("nnumber") int number, @RequestParam("nid") int id, ModelMap model,
			RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			number = number + 1;
			@SuppressWarnings("unchecked")
			ArrayList<Cart> listcart = (ArrayList<Cart>) session.getAttribute("cart");
			for (Cart item : listcart) {
				if (id == item.getId_product()) {
					item.setQuantity(number);
				}
			}
			return " <input type=\"text\" class=\"form-control text-center only-number\" id=\"numberQuantity-" + id
					+ "\" value=\"" + number + "\">";
		}
	}

	@RequestMapping(value = { "/{page}", "/" })
	public String index(ModelMap model, @PathVariable(value = "page", required = false) Integer page) {
		if (page == null) {
			page = 1;
		}
		int sumProduct = productDAO.countSumproduct();
		int row_count = 16;
		int sumPage = (int) Math.ceil((float) sumProduct / row_count);
		model.addAttribute("sumPage", sumPage);
		model.addAttribute("page", page);
		int offset = (page - 1) * row_count;
		model.addAttribute("listCommentSlide", commentDAO.getItemsSlide());
		model.addAttribute("listSlide", slideDAO.getItemsShow());
		model.addAttribute("listProductSlide", productDAO.getItemsTrend());
		model.addAttribute("listProductNew", productDAO.getItems(offset, row_count));
		return "public.index";
	}

	@RequestMapping(value = "/sua-trang-ca-nhan/{id}")
	public String editProfile(ModelMap model, @PathVariable("id") Integer id) {
		User objUser = userDAO.getItem(id);
		model.addAttribute("objUser", objUser);
		return "public.edit_profile";
	}

	@RequestMapping(value = "/sua-trang-ca-nhan/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, RedirectAttributes ra,
			@Valid @ModelAttribute("objUser") User objUser, BindingResult rs, HttpServletRequest request,
			@RequestParam("image") CommonsMultipartFile CMF, ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			model.addAttribute("title", "EDIT USER");
			/*
			 * if (rs.hasErrors()) { return "admin.ads.edit"; }
			 */
			User objOld = userDAO.getItem(id);
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_FILES;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			if (!CMF.getOriginalFilename().isEmpty()) {
				if (!"".equals(objOld.getImage())) {
					String urlFileDel = dirPath + File.separator + objOld.getImage();
					File delFile = new File(urlFileDel);
					delFile.delete();
				}
				try {
					CMF.transferTo(new File(dirPath + File.separator + CMF.getOriginalFilename()));
					objUser.setImage(CMF.getOriginalFilename());
					System.out.println("thành công");
				} catch (IOException e) {
					System.out.println("có lỗi");
				}
			} else {
				objUser.setImage(objOld.getImage());
			}
			if (objUser.getPassword() == "") {
				objUser.setPassword(objOld.getPassword());
			} else {
				objUser.setPassword(bCryptPasswordEncoder.encode(objUser.getPassword()));
			}
			objUser.setCreate_at(objOld.getCreate_at());
			if (userDAO.editItem(objUser) > 0) {
				ra.addFlashAttribute("msg", 2);
			} else {
				ra.addFlashAttribute("msg", 7);
			}
			return "redirect:/trang-ca-nhan/" + id;
		}

	}

	@RequestMapping(value = "/comment/{idProduct}", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objComment") Comment objComment, @PathVariable("idProduct") Integer id,
			BindingResult rs, ModelMap model, RedirectAttributes ra, HttpServletRequest request) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			objComment.setId_user(userDAO.getItem(username).getId());
			objComment.setId_product(id);
			if (commentDAO.addItem(objComment) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/chi-tiet-san-pham/" + id;
		}
	}

	@RequestMapping(value = { "/chi-tiet-san-pham/{id}/{page}", "/chi-tiet-san-pham/{id}" })
	public String detail(ModelMap model, @PathVariable("id") Integer id,
			@PathVariable(value = "page", required = false) Integer page) {
		String username = AuthController.getUserName();
		if (page == null) {
			page = 1;
		}
		model.addAttribute("objProduct", productDAO.getItem(id));
		int sumComment = commentDAO.countSumCommentByID(id);
		int row_count = 6;
		int sumPage = (int) Math.ceil((float) sumComment / row_count);
		model.addAttribute("sumPage", sumPage);
		model.addAttribute("page", page);
		int offset = (page - 1) * row_count;
		model.addAttribute("idProduct", id);
		model.addAttribute("listCommentByID", commentDAO.getItemsById(id, offset, row_count));
		return "public.product_detail";
	}

	@RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
	public String add(@ModelAttribute("objUser") User objUser, BindingResult rs, ModelMap model,
			RedirectAttributes ra, HttpServletRequest request, @RequestParam("image") CommonsMultipartFile CMF) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
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
			objUser.setImage(CMF.getOriginalFilename());
			objUser.setRole_id(2);
			objUser.setEnable(1);
			objUser.setPassword(bCryptPasswordEncoder.encode(objUser.getPassword()));
			System.out.println(objUser.getAddress()+"--"+objUser.getEmail()+"--"+objUser.getImage()+"--"+objUser.getPassword()+"--"+objUser.getPhone()+"--"+objUser.getUsername());
			if (userDAO.addItem(objUser) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/trang-ca-nhan/{id}")
	public String profile(ModelMap model, @PathVariable("id") Integer id) {
		User objUser = userDAO.getItem(id);
		System.out.println(id);
		model.addAttribute("objUser", objUser);
		model.addAttribute("listOrderByUser", orderDAO.getItems(id));
		model.addAttribute("listSuggestByUser", suggestDAO.getItems(id));
		return "public.profile";
	}

	@RequestMapping({ "/san-pham/{page}", "/san-pham" })
	public String products(ModelMap model, @PathVariable(value = "page", required = false) Integer page) {
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
		return "public.products";
	}

}
