package webshop.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import webshop.repository.ProductRepository;

@Controller
public class HomeController {
	
	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/")
	public String home(Map<String, Object> model){
		model.put("productCount", productRepository.findAll().size());
		return "index";
	}
	
}
