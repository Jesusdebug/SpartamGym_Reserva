/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "t_agenda_rutina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAgendaRutina.findAll", query = "SELECT t FROM TAgendaRutina t")
    , @NamedQuery(name = "TAgendaRutina.findByIdagendarutina", query = "SELECT t FROM TAgendaRutina t WHERE t.idagendarutina = :idagendarutina")})
public class TAgendaRutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_agenda_rutina")
    private Integer idagendarutina;
    @JoinColumn(name = "Id_entrenador", referencedColumnName = "Id_entrenador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEntrenador identrenador;
    @JoinColumn(name = "Id_rutina", referencedColumnName = "Id_rutina")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TRutina idrutina;
    @JoinColumn(name = "Id_administrador", referencedColumnName = "Id_administrador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TAdministrador idadministrador;

    public TAgendaRutina() {
    }

    public TAgendaRutina(Integer idagendarutina) {
        this.idagendarutina = idagendarutina;
    }

    public Integer getIdagendarutina() {
        return idagendarutina;
    }

    public void setIdagendarutina(Integer idagendarutina) {
        this.idagendarutina = idagendarutina;
    }

    public TEntrenador getIdentrenador() {
        return identrenador;
    }

    public void setIdentrenador(TEntrenador identrenador) {
        this.identrenador = identrenador;
    }

    public TRutina getIdrutina() {
        return idrutina;
    }

    public void setIdrutina(TRutina idrutina) {
        this.idrutina = idrutina;
    }

    public TAdministrador getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(TAdministrador idadministrador) {
        this.idadministrador = idadministrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagendarutina != null ? idagendarutina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAgendaRutina)) {
            return false;
        }
        TAgendaRutina other = (TAgendaRutina) object;
        if ((this.idagendarutina == null && other.idagendarutina != null) || (this.idagendarutina != null && !this.idagendarutina.equals(other.idagendarutina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TAgendaRutina[ idagendarutina=" + idagendarutina + " ]";
    }
    
}
