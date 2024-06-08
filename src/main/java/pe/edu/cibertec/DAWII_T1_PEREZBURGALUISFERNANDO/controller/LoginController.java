package pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.model.bd.Usuario;
import pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.service.IUsuarioService;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/frmregistro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.guardarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado con éxito. Revise su correo para la contraseña.");
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpSession session, Authentication authentication) {
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.buscarUsuarioXNomUsuario(nombreUsuario);
        session.setAttribute("nombreUsuario", usuario.getNombres() + " " + usuario.getApellidos());
        return "redirect:/auth/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "auth/home";
    }

}
