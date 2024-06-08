package pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.service;

import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario registrarNuevoUsuario(Usuario usuario);

    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
}
