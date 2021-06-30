package com.davidnestor.sprooties.controllers;

import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davidnestor.sprooties.models.Item;
import com.davidnestor.sprooties.models.Post;
import com.davidnestor.sprooties.models.User;
import com.davidnestor.sprooties.services.ItemService;
import com.davidnestor.sprooties.services.PetService;
import com.davidnestor.sprooties.services.PostService;
import com.davidnestor.sprooties.services.UserService;
import com.davidnestor.sprooties.validators.UserValidator;

@Controller
public class SprootiesController {
	@Autowired
	private ItemService iService;
	
	@Autowired
	private PetService pService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private UserValidator validator;
	
	String[] prompts = {"Did you do anything fun today that you are greatful for?", "Did you eat any good food that you are grateful for?",
	"Did you see anyone you are grateful for today?", "You can always be grateful for me!", "Did you see anything beautiful today? A pretty flower? A cute dog?",
	"Did you listen to any good music today? I'm grateful for my favorite songs because they make me happy!", "Did you get any cool stuff today? I love new toys!"	
	};
	
	@GetMapping("/")
	public String baseRoute(Model viewModel, @ModelAttribute("user") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		User newUser = this.uService.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/journal";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("loginEmail") String email, @RequestParam("loginPass") String password, RedirectAttributes redirectAttr, HttpSession session) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttr.addFlashAttribute("loginError", "Invalid Email or Password");
			return "redirect:/";
		}
		User user = this.uService.getByEmail(email);
		session.setAttribute("user_id", user.getId());
		return "redirect:/journal";
	}
	
	@GetMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/journal")
	private String dashboard(@Valid @ModelAttribute("post") Post post, Model viewModel, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("user", this.uService.find(userId));
		viewModel.addAttribute("posts", this.postService.findAllByUser_Id(userId));
		Random generator = new Random();
		int randIndex = generator.nextInt(prompts.length);
		String prompt = prompts[randIndex];
		viewModel.addAttribute("prompt", prompt);
		return "journal.jsp";
	}
	
	@PostMapping("/post/create")
	public String addIdea(@Valid @ModelAttribute("post") Post post, BindingResult result, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("user", this.uService.find(userId));
			return "newidea.jsp";
		}
		Long userId = (Long)session.getAttribute("user_id");
		long gold = 10;
		this.uService.find(userId).setGold(gold);
		this.postService.create(post);
		return "redirect:/journal";
	}
	
	@GetMapping("/shop")
	private String shop(Model viewModel, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("user", this.uService.find(userId));
		viewModel.addAttribute("items", this.iService.getItems());
		return "shop.jsp";
	}
	
	@GetMapping("/shop/{item.id}/buy")
	private String buy(@PathVariable("item.id") Long itemid, Model viewmodel, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		User thisuser = this.uService.find(userId);
		Item thisItem = this.iService.getById(itemid);
		if(thisuser.getItems().contains(thisItem) == true) {
			return "redirect:/shop";
		}
		thisuser.addItem(thisItem);
		this.uService.saveUser(thisuser);
		return "redirect:/shop";
	}
	
	@GetMapping("/home")
	private String home(Model viewModel, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		User thisuser = this.uService.find(userId);
		viewModel.addAttribute("user", this.uService.find(userId));
		viewModel.addAttribute("items", thisuser.getItems());
		return "home.jsp";
	}
}
