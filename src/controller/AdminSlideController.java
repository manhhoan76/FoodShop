package controller;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.ContactDAO;
import dao.SlideDAO;
import dao.UserDAO;
import entity.Slide;
import entity.User;

@Controller
@RequestMapping(value="/admin/slide")
public class AdminSlideController {
	public static final String DIR_FILES = "files";
	@Autowired
	private UserDAO uerDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private SlideDAO slideDAO;
	
	@ModelAttribute
	public void addCommon(ModelMap model) {
		String username = AuthController.getUserName();
		User objUserInfro = uerDAO.getItem(username);
		model.addAttribute("objLogin", objUserInfro);
		model.addAttribute("numberContact", contactDAO.countSumContactRead());
	}
	@RequestMapping(value = "/add")
	public String add() {
		
		return "admin.add_slide";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objSlide") Slide objSlide, BindingResult rs, ModelMap model,
			RedirectAttributes ra, HttpServletRequest request, @RequestParam("name") CommonsMultipartFile CMF) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			/*if (rs.hasErrors()) {
				return "admin.user.add";
			}*/
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
			objSlide.setName(CMF.getOriginalFilename());
			if (slideDAO.addItem(objSlide) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/admin/slide/index";
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
			int sumSlide  = slideDAO.countSumslide();
			int row_count = Defines.ROW_COUNT_PUBLIC_INDEX;
			int sumPage = (int) Math.ceil((float) sumSlide / row_count);
			model.addAttribute("sumPage", sumPage);
			model.addAttribute("page", page);
			int offset = (page - 1) * row_count;
			model.addAttribute("listSlide", slideDAO.getItems(offset, row_count));
			return "admin.manager_slide";
		}
	 } 
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			if (slideDAO.delItem(id) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/admin/slide/index";
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
				slideDAO.block(slideDAO.getItem(id).getCreate_at(), id);
				return "<a  style=\"width: 72px;\"  class=\"btn btn-warning\" onclick=\"active(" + id
						+ ",0)\"  class=\"btn btn-warning\" href=\"javascript:void(0)\" >Slide</a>";
			} else {
				slideDAO.active(slideDAO.getItem(id).getCreate_at(), id);
				return "<a   style=\"width: 72px;\" class=\"btn btn-warning\" onclick='active(" + id
						+ ",1)'  class='btn btn-success' href='javascript:void(0)' >None</a>";
			}

		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			model.addAttribute("objSlide", slideDAO.getItem(id));
			return "admin.edit_slide";
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, RedirectAttributes ra,
			@Valid @ModelAttribute("objSlide") Slide objSlide, BindingResult rs, HttpServletRequest request,
			@RequestParam("name") CommonsMultipartFile CMF, ModelMap model) {
		String username = AuthController.getUserName();
		if (username == "") {
			return "redirect:/login";
		} else {
			/*if (rs.hasErrors()) {
				return "admin.new.edit";
			}*/
			Slide objOld = slideDAO.getItem(id);
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
					objSlide.setName(CMF.getOriginalFilename());
					System.out.println("thành công");
				} catch (IOException e) {
					System.out.println("có lỗi");
				}
			} else {
				objSlide.setName(objOld.getName());
			}
			objSlide.setCreate_at(objOld.getCreate_at());
			if (slideDAO.editItem(objSlide) > 0) {
				ra.addFlashAttribute("msg", 1);
			} else {
				ra.addFlashAttribute("msg", 0);
			}
			return "redirect:/admin/slide/index";
		}

	}

	
}
