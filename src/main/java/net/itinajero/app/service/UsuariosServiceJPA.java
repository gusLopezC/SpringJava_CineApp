package net.itinajero.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import net.itinajero.app.model.Usuario;
import net.itinajero.app.repository.UsuariosRepository;

@Service
public class UsuariosServiceJPA implements IUsuariosService {

	@Autowired
	private UsuariosRepository repoUsuario;

	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		repoUsuario.save(usuario);

	}

}
