package com.apirest.apirest.controllers;



import com.apirest.apirest.utils.*;
import com.apirest.apirest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public String login(@RequestBody User usuario) {
    	
    	 String tokenJwt = jwtUtil.create(String.valueOf("1"),"giancarlo.soriafiestas@gmail.com");
    	 return tokenJwt;
        //Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        
        /*if (usuarioLogueado != null) {
            //String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            String tokenJwt = jwtUtil.create(String.valueOf("1"),"giancarlo.soriafiestas@gmail.com");
            return tokenJwt;
        }*/
        //return "FAIL";
    }

}
