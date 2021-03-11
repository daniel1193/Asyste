/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Asyste3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    //===========================================MIS CAMBIOS
    //este es el metodo que hace la busqueda de la persona en la base de datos
    public Usuario iniciarSesion(Usuario user){
        Usuario usuario = null;
        String consulta;
        try{
            //usamos signos de interrogacion para declarar el orden de los parametros
            consulta = "From Usuario u WHERE u.usuario= ?1 AND u.contraseña = ?2";
            //usamos un query ara declarar la constulta a al base de datos
            //importamos query
            Query query = em.createQuery(consulta);
            query.setParameter(1, user.getUsuario());
            query.setParameter(2, user.getContraseña());
            //creamos una lista para contener el objeto que nos da la consulta
            //de acuerso al objeto que estamos mapeando
            List <Usuario> lista = query.getResultList();
            //validamos si hay un objeto dentro de la lista
            if(!lista.isEmpty()){
                //capturamos el primer objeto retornado
                usuario = lista.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        //al final el metodo va retornar un objeto de tipo persona
        return usuario;
    }
}
