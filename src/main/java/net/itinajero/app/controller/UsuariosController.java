package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Perfil;
import net.itinajero.app.model.Usuario;
import net.itinajero.app.service.IPerfilesService;
import net.itinajero.app.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private IUsuariosService serviceUsuario;

	@Autowired
	private IPerfilesService servicePerfiles;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping(value = "/index")
	public String index() {

		return "usuarios/index";
	}

	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Usuario usuario) {

		return "usuarios/formUsuario";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute Usuario usuario, @RequestParam("perfil") String perfil) {

		System.out.println("Usuario: " + usuario);
		System.out.println("Perfil: " + perfil);

		String tmpPwd = usuario.getPwd();
		String encriptado = encoder.encode(tmpPwd);
		usuario.setPwd(encriptado);
		usuario.setActivo(1);
		serviceUsuario.guardar(usuario);

		Perfil perfilTmp = new Perfil();
		perfilTmp.setCuenta(usuario.getCuenta());
		perfilTmp.setPerfil(perfil);

		servicePerfiles.guardar(perfilTmp);

		return "redirect:/usuario/index";
	}

	@GetMapping("/demo-bcrypt")
	public String pruebaBcrypt() {
		String password = "mari123";
		String encriptado = encoder.encode(password);
		System.out.println("Password encriptado: " + encriptado);
		return "usuarios/demo";
	}

}
