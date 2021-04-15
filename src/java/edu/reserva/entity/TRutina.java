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
@Table(name = "t_rutina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRutina.findAll", query = "SELECT t FROM TRutina t")
    , @NamedQuery(name = "TRutina.findByIdrutina", query = "SELECT t FROM TRutina t WHERE t.idrutina = :idrutina")
    , @NamedQuery(name = "TRutina.findByTipo", query = "SELECT t FROM TRutina t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TRutina.findByNombre", query = "SELECT t FROM TRutina t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TRutina.findByDescripcion", query = "SELECT t FROM TRutina t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TRutina.findByFecha", query = "SELECT t FROM TRutina t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TRutina.findByHorainicio", query = "SELECT t FROM TRutina t WHERE t.horainicio = :horainicio")
    , @NamedQuery(name = "TRutina.findByHorafin", query = "SELECT t FROM TRutina t WHERE t.horafin = :horafin")})
public class TRutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_rutina")
    private Integer idrutina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tipo")
    private String tipo;
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
    @JoinColumn(name = "t_entrenador_Id_entrenador", referencedColumnName = "Id_entrenador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEntrenador tentrenadorIdentrenador;
    @JoinColumn(name = "t_entrenador_Id_entrenador1", referencedColumnName = "Id_entrenador")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEntrenador tentrenadorIdentrenador1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrutina", fetch = FetchType.LAZY)
    private Collection<TAgendaRutina> tAgendaRutinaCollection;

    public TRutina() {
    }

    public TRutina(Integer idrutina) {
        this.idrutina = idrutina;
    }

    public TRutina(Integer idrutina, String tipo, String nombre, String descripcion, Date fecha, Date horainicio, Date horafin) {
        this.idrutina = idrutina;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
    }

    public Integer getIdrutina() {
        return idrutina;
    }

    public void setIdrutina(Integer idrutina) {
        this.idrutina = idrutina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public TEntrenador getTentrenadorIdentrenador() {
        return tentrenadorIdentrenador;
    }

    public void setTentrenadorIdentrenador(TEntrenador tentrenadorIdentrenador) {
        this.tentrenadorIdentrenador = tentrenadorIdentrenador;
    }

    public TEntrenador getTentrenadorIdentrenador1() {
        return tentrenadorIdentrenador1;
    }

    public void setTentrenadorIdentrenador1(TEntrenador tentrenadorIdentrenador1) {
        this.tentrenadorIdentrenador1 = tentrenadorIdentrenador1;
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
        hash += (idrutina != null ? idrutina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRutina)) {
            return false;
        }
        TRutina other = (TRutina) object;
        if ((this.idrutina == null && other.idrutina != null) || (this.idrutina != null && !this.idrutina.equals(other.idrutina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TRutina[ idrutina=" + idrutina + " ]";
    }
    
}
