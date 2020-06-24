package net.itinajero.app.service;

import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Usuario;

@Repository
public interface IUsuariosService {

	void guardar(Usuario usuario);
}
