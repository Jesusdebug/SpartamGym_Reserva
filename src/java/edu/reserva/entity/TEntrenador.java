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
@Table(name = "t_entrenador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEntrenador.findAll", query = "SELECT t FROM TEntrenador t")
    , @NamedQuery(name = "TEntrenador.findByIdentrenador", query = "SELECT t FROM TEntrenador t WHERE t.identrenador = :identrenador")})
public class TEntrenador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_entrenador")
    private Integer identrenador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tentrenadorIdentrenador", fetch = FetchType.LAZY)
    private Collection<TEvento> tEventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tentrenadorIdentrenador", fetch = FetchType.LAZY)
    private Collection<TRutina> tRutinaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tentrenadorIdentrenador1", fetch = FetchType.LAZY)
    private Collection<TRutina> tRutinaCollection1;
    @JoinColumn(name = "t_usuario_Id_Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUsuario tusuarioIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identrenador", fetch = FetchType.LAZY)
    private Collection<TAgendaEvento> tAgendaEventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identrenador", fetch = FetchType.LAZY)
    private Collection<TAgendaRutina> tAgendaRutinaCollection;

    public TEntrenador() {
    }

    public TEntrenador(Integer identrenador) {
        this.identrenador = identrenador;
    }

    public Integer getIdentrenador() {
        return identrenador;
    }

    public void setIdentrenador(Integer identrenador) {
        this.identrenador = identrenador;
    }

    @XmlTransient
    public Collection<TEvento> getTEventoCollection() {
        return tEventoCollection;
    }

    public void setTEventoCollection(Collection<TEvento> tEventoCollection) {
        this.tEventoCollection = tEventoCollection;
    }

    @XmlTransient
    public Collection<TRutina> getTRutinaCollection() {
        return tRutinaCollection;
    }

    public void setTRutinaCollection(Collection<TRutina> tRutinaCollection) {
        this.tRutinaCollection = tRutinaCollection;
    }

    @XmlTransient
    public Collection<TRutina> getTRutinaCollection1() {
        return tRutinaCollection1;
    }

    public void setTRutinaCollection1(Collection<TRutina> tRutinaCollection1) {
        this.tRutinaCollection1 = tRutinaCollection1;
    }

    public TUsuario getTusuarioIdUsuario() {
        return tusuarioIdUsuario;
    }

    public void setTusuarioIdUsuario(TUsuario tusuarioIdUsuario) {
        this.tusuarioIdUsuario = tusuarioIdUsuario;
    }

    @XmlTransient
    public Collection<TAgendaEvento> getTAgendaEventoCollection() {
        return tAgendaEventoCollection;
    }

    public void setTAgendaEventoCollection(Collection<TAgendaEvento> tAgendaEventoCollection) {
        this.tAgendaEventoCollection = tAgendaEventoCollection;
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
        hash += (identrenador != null ? identrenador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEntrenador)) {
            return false;
        }
        TEntrenador other = (TEntrenador) object;
        if ((this.identrenador == null && other.identrenador != null) || (this.identrenador != null && !this.identrenador.equals(other.identrenador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TEntrenador[ identrenador=" + identrenador + " ]";
    }
    
}
