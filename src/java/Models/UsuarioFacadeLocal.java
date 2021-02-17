/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.ejb.Local;

/**
 *
 * @author Daniel
 */
@Local
public interface UsuarioFacadeLocal {
    //Especifico el metodo que esta en el controllador de los usuarios
    Usuario iniciarSesion(Usuario us) throws Exception;
}
