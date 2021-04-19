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
@Table(name = "t_agenda_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAgendaEvento.findAll", query = "SELECT t FROM TAgendaEvento t")
    , @NamedQuery(name = "TAgendaEvento.findByIdagendaevento", query = "SELECT t FROM TAgendaEvento t WHERE t.idagendaevento = :idagendaevento")})
public class TAgendaEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_agenda_evento")
    private Integer idagendaevento;
    @JoinColumn(name = "Id_evento", referencedColumnName = "Id_evento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEvento idevento;

    public TAgendaEvento() {
    }

    public TAgendaEvento(Integer idagendaevento) {
        this.idagendaevento = idagendaevento;
    }

    public Integer getIdagendaevento() {
        return idagendaevento;
    }

    public void setIdagendaevento(Integer idagendaevento) {
        this.idagendaevento = idagendaevento;
    }

    public TEvento getIdevento() {
        return idevento;
    }

    public void setIdevento(TEvento idevento) {
        this.idevento = idevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagendaevento != null ? idagendaevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAgendaEvento)) {
            return false;
        }
        TAgendaEvento other = (TAgendaEvento) object;
        if ((this.idagendaevento == null && other.idagendaevento != null) || (this.idagendaevento != null && !this.idagendaevento.equals(other.idagendaevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TAgendaEvento[ idagendaevento=" + idagendaevento + " ]";
    }
    
}
