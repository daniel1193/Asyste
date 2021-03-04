/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class AsistenciaFacade extends AbstractFacade<Asistencia> {

    @PersistenceContext(unitName = "Asyste3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
    
    //se crea el metodo declarado en la consulta del modelo
    public List<Asistencia> getAsistenciasAprendizByIdUsuario(Integer IdUsuario){
        List<Asistencia> asistencias = new ArrayList<>();
        asistencias = getEntityManager().createNamedQuery("Asistencia.getAsistenciasAprendizByIdUsuario", Asistencia.class).setParameter("IdUsuario",IdUsuario).getResultList();       
        return asistencias;
    }
    
}
