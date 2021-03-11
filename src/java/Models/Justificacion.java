/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "justificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Justificacion.findAll", query = "SELECT j FROM Justificacion j")
    , @NamedQuery(name = "Justificacion.findByIdJustifcaion", query = "SELECT j FROM Justificacion j WHERE j.idJustifcaion = :idJustifcaion")
    , @NamedQuery(name = "Justificacion.findByFecha", query = "SELECT j FROM Justificacion j WHERE j.fecha = :fecha")
    , @NamedQuery(name = "Justificacion.findByDescripcion", query = "SELECT j FROM Justificacion j WHERE j.descripcion = :descripcion")
    , @NamedQuery(name = "Justificacion.findByEvidencia", query = "SELECT j FROM Justificacion j WHERE j.evidencia = :evidencia")
        //consulta para obtener las justificaciones
    , @NamedQuery(name = "Justificacion.getJustificacionByIdUsuario", query="SELECT j FROM Justificacion j WHERE j.asistenciaidAsistencia.personaIdPersona.usuarioidUsuario.idUsuario = :IdUsuario")})
public class Justificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idJustifcaion")
    private Integer idJustifcaion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Evidencia")
    private String evidencia;
    @JoinColumn(name = "Asistencia_idAsistencia", referencedColumnName = "idAsistencia")
    @ManyToOne(optional = false)
    private Asistencia asistenciaidAsistencia;
    @JoinColumn(name = "Evento_idEvento", referencedColumnName = "idEvento")
    @ManyToOne(optional = false)
    private Evento eventoidEvento;

    public Justificacion() {
    }

    public Justificacion(Integer idJustifcaion) {
        this.idJustifcaion = idJustifcaion;
    }

    public Justificacion(Integer idJustifcaion, Date fecha, String descripcion, String evidencia) {
        this.idJustifcaion = idJustifcaion;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.evidencia = evidencia;
    }

    public Integer getIdJustifcaion() {
        return idJustifcaion;
    }

    public void setIdJustifcaion(Integer idJustifcaion) {
        this.idJustifcaion = idJustifcaion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public Asistencia getAsistenciaidAsistencia() {
        return asistenciaidAsistencia;
    }

    public void setAsistenciaidAsistencia(Asistencia asistenciaidAsistencia) {
        this.asistenciaidAsistencia = asistenciaidAsistencia;
    }

    public Evento getEventoidEvento() {
        return eventoidEvento;
    }

    public void setEventoidEvento(Evento eventoidEvento) {
        this.eventoidEvento = eventoidEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJustifcaion != null ? idJustifcaion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Justificacion)) {
            return false;
        }
        Justificacion other = (Justificacion) object;
        if ((this.idJustifcaion == null && other.idJustifcaion != null) || (this.idJustifcaion != null && !this.idJustifcaion.equals(other.idJustifcaion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + fecha;
    }
    
}
