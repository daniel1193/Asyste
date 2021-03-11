/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class JustificacionFacade extends AbstractFacade<Justificacion> {

    @PersistenceContext(unitName = "Asyste3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JustificacionFacade() {
        super(Justificacion.class);
    }
    
    public List<Justificacion> getJustificacionByIdUsuario(Integer IdUsuario){
        List<Justificacion> justificaciones = new ArrayList<>();
        justificaciones = getEntityManager().createNamedQuery("Justificacion.getJustificacionByIdUsuario", Justificacion.class).setParameter("IdUsuario",IdUsuario).getResultList();
        return justificaciones;
    }
}
