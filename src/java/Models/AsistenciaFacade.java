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
    
    public List<Asistencia> getAsistenciasAprendizByIdUsuario(Integer IdUsuario){
        List<Asistencia> asistencias = new ArrayList<>();
        asistencias = getEntityManager().createNamedQuery("Asistencia.getAsistenciasAprendizByIdUsuario", Asistencia.class).setParameter("IdUsuario",IdUsuario).getResultList();
        System.out.println(asistencias);
        return asistencias;
    }
    
    //lista de asistencias por id sesion
    public List<Asistencia> getAsistenciasByIdSesion(Integer IdSesion){
        List<Asistencia> asistenciasBySesion = new ArrayList<>();
        asistenciasBySesion = getEntityManager().createNamedQuery("Asistencia.getAsistenciasByIdSesion", Asistencia.class).setParameter("IdSesion",IdSesion).getResultList();
        return asistenciasBySesion;
    }
    
}
