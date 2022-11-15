package com.example.demo.admin;

import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.hibernate.boot.InvalidMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@Controller
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private OrderService orderService;
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login() {
		return "/admin/login";
	}
	
	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model) {
		model.addAttribute("totalProduct", productService.findAll().size());
		model.addAttribute("totalCategory", categoryService.findAll().size());
		return "/admin/dashboard";
	}
	
	
	
	// product manager
	@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
	public String getListProducts(Model model) {
		model.addAttribute("listProducts", productService.findAll());
		return "/admin/listProducts";
	}
	
	@RequestMapping(value = "/admin/newProduct", method = RequestMethod.GET)
	public String showNewProductForm(Model model) {
		model.addAttribute("newProduct", new Product());
		model.addAttribute("listCategories", categoryService.findAll());
		return "/admin/newProductForm";
	}
	
	@RequestMapping(value = "/admin/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("quantity") int quantity,
			@RequestParam("price") int price,
			@RequestParam("category") Category category,
			@RequestParam("image") MultipartFile file, Model model) {
		if(productService.isDuplicatedName(name)) {
			model.addAttribute("failed", "Ten san pham da ton tai");
			model.addAttribute("name", new Product());
			return "admin/newProductForm";
		}
		productService.saveProduct(name, description, quantity, price, category, file);
		System.out.println(file.getOriginalFilename());
		return "redirect:/admin/products";
	}
	
	@RequestMapping(value = "/admin/update/{id}")
	public String showUpdateProductForm(@PathVariable("id") int id, Model model) {
		Product product = productRepository.findById(id).orElse(null);
		System.out.println(product);
		model.addAttribute("foundProduct", product);
		model.addAttribute("listCategories", categoryService.findAll());
		return "/admin/updateProductForm";
	}
	
	@RequestMapping(value = "/admin/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		return "redirect:/admin/products";
	}
	
	
	// category
	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public String getListCategories(Model model) {
		List<CategoryDTO> list = categoryService.findAll();
		model.addAttribute("listCategories", list);
		return "/admin/listCategories";
	}
	
	@RequestMapping(value = "/admin/newCategory", method = RequestMethod.GET)
	public String showNewCategoryForm(Model model) {
		model.addAttribute("newCategory", new CategoryDTO());
		return "/admin/newCategoryForm";
	}
	
	@RequestMapping(value = "/admin/saveCategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute("newCategory") CategoryDTO categoryDTO, Model model) {
		if(categoryService.isDuplicatedName(categoryDTO.getName())) {
			model.addAttribute("failed","Ten da ton tai");
			return "admin/newCategoryForm";
		}
		else {
			categoryService.save(categoryService.convertFromCategoryDTO(categoryDTO));
			return "redirect:/admin/categories";
		}
	}
	
	@RequestMapping(value = "/admin/category/update/{id}")
	public String showUpdateCategoryForm(@PathVariable("id") int id, Model model) {
		CategoryDTO categoryDTO = categoryService.getById(id);
		model.addAttribute("foundCategory", categoryDTO);
		return "/admin/updateCategoryForm";
	}
	
	@RequestMapping(value = "/admin/updateCategory", method = RequestMethod.POST)
	public String updateCategory(@ModelAttribute("foundCategory") CategoryDTO categoryDTO) {
		categoryService.save(categoryService.update(categoryDTO));
		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value = "/admin/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		categoryService.deleteById(id);
		return "redirect:/admin/categories";
	}
	
	
	// users
	@RequestMapping(value = "/admin/users")
	public String getListUsers(Model model) {
		List<User> listUsers = userRepository.findAll();
		model.addAttribute("listUsers", listUsers);
		return "admin/listUsers";
	}
}
