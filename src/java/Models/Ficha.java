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
@Table(name = "ficha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f")
    , @NamedQuery(name = "Ficha.findByIdFicha", query = "SELECT f FROM Ficha f WHERE f.idFicha = :idFicha")
    , @NamedQuery(name = "Ficha.findByNumero", query = "SELECT f FROM Ficha f WHERE f.numero = :numero")
    , @NamedQuery(name = "Ficha.findByAmbiente", query = "SELECT f FROM Ficha f WHERE f.ambiente = :ambiente")
    , @NamedQuery(name = "Ficha.findByFechadeinicio", query = "SELECT f FROM Ficha f WHERE f.fechadeinicio = :fechadeinicio")
    , @NamedQuery(name = "Ficha.findByFechafin", query = "SELECT f FROM Ficha f WHERE f.fechafin = :fechafin")
    , @NamedQuery(name = "Ficha.findByHoradeinicio", query = "SELECT f FROM Ficha f WHERE f.horadeinicio = :horadeinicio")
    , @NamedQuery(name = "Ficha.findByHorafin", query = "SELECT f FROM Ficha f WHERE f.horafin = :horafin")})
public class Ficha implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fichaidFicha")
    private Collection<Sesion> sesionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFicha")
    private Integer idFicha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Ambiente")
    private String ambiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fechadeinicio")
    @Temporal(TemporalType.DATE)
    private Date fechadeinicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Horadeinicio")
    private String horadeinicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Horafin")
    private String horafin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fichaidFicha")
    private Collection<Persona> personaCollection;
    @JoinColumn(name = "Jornada_idJornada", referencedColumnName = "idJornada")
    @ManyToOne(optional = false)
    private Jornada jornadaidJornada;
    @JoinColumn(name = "Modalidad_idModalidad", referencedColumnName = "idModalidad")
    @ManyToOne(optional = false)
    private Modalidad modalidadidModalidad;
    @JoinColumn(name = "Programa_idPrograma", referencedColumnName = "idPrograma")
    @ManyToOne(optional = false)
    private Programa programaidPrograma;

    public Ficha() {
    }

    public Ficha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Ficha(Integer idFicha, String numero, String ambiente, Date fechadeinicio, Date fechafin, String horadeinicio, String horafin) {
        this.idFicha = idFicha;
        this.numero = numero;
        this.ambiente = ambiente;
        this.fechadeinicio = fechadeinicio;
        this.fechafin = fechafin;
        this.horadeinicio = horadeinicio;
        this.horafin = horafin;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public Date getFechadeinicio() {
        return fechadeinicio;
    }

    public void setFechadeinicio(Date fechadeinicio) {
        this.fechadeinicio = fechadeinicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getHoradeinicio() {
        return horadeinicio;
    }

    public void setHoradeinicio(String horadeinicio) {
        this.horadeinicio = horadeinicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    public Jornada getJornadaidJornada() {
        return jornadaidJornada;
    }

    public void setJornadaidJornada(Jornada jornadaidJornada) {
        this.jornadaidJornada = jornadaidJornada;
    }

    public Modalidad getModalidadidModalidad() {
        return modalidadidModalidad;
    }

    public void setModalidadidModalidad(Modalidad modalidadidModalidad) {
        this.modalidadidModalidad = modalidadidModalidad;
    }

    public Programa getProgramaidPrograma() {
        return programaidPrograma;
    }

    public void setProgramaidPrograma(Programa programaidPrograma) {
        this.programaidPrograma = programaidPrograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numero;
    }

    @XmlTransient
    public Collection<Sesion> getSesionCollection() {
        return sesionCollection;
    }

    public void setSesionCollection(Collection<Sesion> sesionCollection) {
        this.sesionCollection = sesionCollection;
    }
    
}
