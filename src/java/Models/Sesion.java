/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sesion.findAll", query = "SELECT s FROM Sesion s")
    , @NamedQuery(name = "Sesion.findByIdSesion", query = "SELECT s FROM Sesion s WHERE s.idSesion = :idSesion")
    , @NamedQuery(name = "Sesion.findByNombreEvento", query = "SELECT s FROM Sesion s WHERE s.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "Sesion.findByFecha", query = "SELECT s FROM Sesion s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "Sesion.findByHoraInicio", query = "SELECT s FROM Sesion s WHERE s.horaInicio = :horaInicio")
    , @NamedQuery(name = "Sesion.findByHoraFin", query = "SELECT s FROM Sesion s WHERE s.horaFin = :horaFin")})
public class Sesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSesion")
    private Integer idSesion;
    @Size(max = 150)
    @Column(name = "nombreEvento")
    private String nombreEvento;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "horaInicio")
    private String horaInicio;
    @Size(max = 40)
    @Column(name = "horaFin")
    private String horaFin;
    @JoinColumn(name = "Ficha_idFicha", referencedColumnName = "idFicha")
    @ManyToOne(optional = false)
    private Ficha fichaidFicha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesionidSesion")
    private Collection<Asistencia> asistenciaCollection;

    public Sesion() {
    }

    public Sesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Sesion(Integer idSesion, String horaInicio) {
        this.idSesion = idSesion;
        this.horaInicio = horaInicio;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Ficha getFichaidFicha() {
        return fichaidFicha;
    }

    public void setFichaidFicha(Ficha fichaidFicha) {
        this.fichaidFicha = fichaidFicha;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSesion != null ? idSesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesion)) {
            return false;
        }
        Sesion other = (Sesion) object;
        if ((this.idSesion == null && other.idSesion != null) || (this.idSesion != null && !this.idSesion.equals(other.idSesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Sesion[ idSesion=" + idSesion + " ]";
    }
    
}
