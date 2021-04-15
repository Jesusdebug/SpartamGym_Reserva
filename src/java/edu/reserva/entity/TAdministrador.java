/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "t_administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAdministrador.findAll", query = "SELECT t FROM TAdministrador t")
    , @NamedQuery(name = "TAdministrador.findByIdadministrador", query = "SELECT t FROM TAdministrador t WHERE t.idadministrador = :idadministrador")})
public class TAdministrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_administrador")
    private Integer idadministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tadministradorIdadministrador", fetch = FetchType.LAZY)
    private Collection<TEvento> tEventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idadministrador", fetch = FetchType.LAZY)
    private Collection<TAgendaEvento> tAgendaEventoCollection;
    @JoinColumn(name = "t_usuario_Id_Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUsuario tusuarioIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idadministrador", fetch = FetchType.LAZY)
    private Collection<TAgendaRutina> tAgendaRutinaCollection;

    public TAdministrador() {
    }

    public TAdministrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    public Integer getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    @XmlTransient
    public Collection<TEvento> getTEventoCollection() {
        return tEventoCollection;
    }

    public void setTEventoCollection(Collection<TEvento> tEventoCollection) {
        this.tEventoCollection = tEventoCollection;
    }

    @XmlTransient
    public Collection<TAgendaEvento> getTAgendaEventoCollection() {
        return tAgendaEventoCollection;
    }

    public void setTAgendaEventoCollection(Collection<TAgendaEvento> tAgendaEventoCollection) {
        this.tAgendaEventoCollection = tAgendaEventoCollection;
    }

    public TUsuario getTusuarioIdUsuario() {
        return tusuarioIdUsuario;
    }

    public void setTusuarioIdUsuario(TUsuario tusuarioIdUsuario) {
        this.tusuarioIdUsuario = tusuarioIdUsuario;
    }

    @XmlTransient
    public Collection<TAgendaRutina> getTAgendaRutinaCollection() {
        return tAgendaRutinaCollection;
    }

    public void setTAgendaRutinaCollection(Collection<TAgendaRutina> tAgendaRutinaCollection) {
        this.tAgendaRutinaCollection = tAgendaRutinaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadministrador != null ? idadministrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAdministrador)) {
            return false;
        }
        TAdministrador other = (TAdministrador) object;
        if ((this.idadministrador == null && other.idadministrador != null) || (this.idadministrador != null && !this.idadministrador.equals(other.idadministrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TAdministrador[ idadministrador=" + idadministrador + " ]";
    }
    
}
