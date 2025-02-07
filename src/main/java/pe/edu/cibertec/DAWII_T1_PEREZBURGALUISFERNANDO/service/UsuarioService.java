package pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.model.bd.Rol;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.model.bd.Usuario;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.repository.RolRepository;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.repository.UsuarioRepository;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.util.RandomPassword;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private RandomPassword randomPassword;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(passwordEncoder.encode(randomPassword.generar(7)));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario registrarNuevoUsuario(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("USER"); // Asumiendo que tienes un rol "USER"
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        String passwordGenerada = randomPassword.generar(10);
        usuario.setPassword(passwordEncoder.encode(passwordGenerada));
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        // Aquí deberías enviar un email con la contraseña generada
        System.out.println("Contraseña para " + usuario.getEmail() + ": " + passwordGenerada);
        return usuarioGuardado;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(
                usuario.getNombres(),usuario.getApellidos(),
                usuario.getActivo(),usuario.getIdusuario()
        );
    }
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }
}
