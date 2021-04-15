/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "t_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEvento.findAll", query = "SELECT t FROM TEvento t")
    , @NamedQuery(name = "TEvento.findByIdevento", query = "SELECT t FROM TEvento t WHERE t.idevento = :idevento")
    , @NamedQuery(name = "TEvento.findByNombre", query = "SELECT t FROM TEvento t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TEvento.findByDescripcion", query = "SELECT t FROM TEvento t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TEvento.findByLugar", query = "SELECT t FROM TEvento t WHERE t.lugar = :lugar")
    , @NamedQuery(name = "TEvento.findByFecha", query = "SELECT t FROM TEvento t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TEvento.findByHorainicio", query = "SELECT t FROM TEvento t WHERE t.horainicio = :horainicio")
    , @NamedQuery(name = "TEvento.findByHorafin", query = "SELECT t FROM TEvento t WHERE t.horafin = :horafin")})
public class TEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_evento")
    private Integer idevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Lugar")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @JoinColumn(name = "t_administrador_Id_administrador", referencedColumnName = "Id_administrador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TAdministrador tadministradorIdadministrador;
    @JoinColumn(name = "t_entrenador_Id_entrenador", referencedColumnName = "Id_entrenador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEntrenador tentrenadorIdentrenador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevento", fetch = FetchType.LAZY)
    private Collection<TAgendaEvento> tAgendaEventoCollection;

    public TEvento() {
    }

    public TEvento(Integer idevento) {
        this.idevento = idevento;
    }

    public TEvento(Integer idevento, String nombre, String descripcion, String lugar, Date fecha, Date horainicio, Date horafin) {
        this.idevento = idevento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public TAdministrador getTadministradorIdadministrador() {
        return tadministradorIdadministrador;
    }

    public void setTadministradorIdadministrador(TAdministrador tadministradorIdadministrador) {
        this.tadministradorIdadministrador = tadministradorIdadministrador;
    }

    public TEntrenador getTentrenadorIdentrenador() {
        return tentrenadorIdentrenador;
    }

    public void setTentrenadorIdentrenador(TEntrenador tentrenadorIdentrenador) {
        this.tentrenadorIdentrenador = tentrenadorIdentrenador;
    }

    @XmlTransient
    public Collection<TAgendaEvento> getTAgendaEventoCollection() {
        return tAgendaEventoCollection;
    }

    public void setTAgendaEventoCollection(Collection<TAgendaEvento> tAgendaEventoCollection) {
        this.tAgendaEventoCollection = tAgendaEventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevento != null ? idevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEvento)) {
            return false;
        }
        TEvento other = (TEvento) object;
        if ((this.idevento == null && other.idevento != null) || (this.idevento != null && !this.idevento.equals(other.idevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TEvento[ idevento=" + idevento + " ]";
    }
    
}
