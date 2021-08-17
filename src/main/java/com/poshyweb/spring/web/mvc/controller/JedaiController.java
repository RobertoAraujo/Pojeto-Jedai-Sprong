package com.poshyweb.spring.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.poshyweb.spring.web.mvc.nodel.Jedai;
import com.poshyweb.spring.web.mvc.repository.JedaiRepository;

@Controller
public class JedaiController {
	
	@Autowired
	private JedaiRepository jedaiRepository;
	
	//lista com modealandview
	@RequestMapping (path = "/jedai", method = RequestMethod.GET)
	public ModelAndView Index() {
		// lsiat todos os jedai
		List<Jedai> lista = jedaiRepository.findAll(); 
	    ModelAndView modelAndView = new ModelAndView("jedai");
	    modelAndView.addObject("aljedai", lista);
	     
	    return modelAndView;
	}
	
	// method de salvar posta no banco
	@RequestMapping(path = "/new-jedai", method = RequestMethod.POST)
	public ModelAndView CadastroJedaiPost(Jedai jedai) {
		// salva no banco
		jedaiRepository.save(jedai);
		return new ModelAndView("redirect:/jedai");
	}
	
	// chama a pagina de cadastro de jedai
	@RequestMapping (path = "/new-jedai", method = RequestMethod.GET)
	public String novoJedai() {
		return "new-jedai";
	}
	
	@PostMapping("/update-jedai/{id}")
	public String updateContato(@PathVariable("id") long id, @Validated Jedai jedai, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        //jedaiRepository.setId(id);
	        return "atualizar-Jedai";
	    }
	        
	    jedaiRepository.save(jedai);
	    return "redirect:/jedai";
	}

	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Jedai jedai = jedaiRepository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("Id Não Encotrado:" + id));
		jedaiRepository.delete(jedai);
	    return "redirect:/jedai";
	}
}
