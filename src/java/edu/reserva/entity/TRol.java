/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name = "t_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRol.findAll", query = "SELECT t FROM TRol t")
    , @NamedQuery(name = "TRol.findByIdrol", query = "SELECT t FROM TRol t WHERE t.idrol = :idrol")
    , @NamedQuery(name = "TRol.findByNombre", query = "SELECT t FROM TRol t WHERE t.nombre = :nombre")})
public class TRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_rol")
    private Integer idrol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @JoinTable(name = "t_usuario_has_t_rol", joinColumns = {
        @JoinColumn(name = "t_rol_Id_rol", referencedColumnName = "Id_rol")}, inverseJoinColumns = {
        @JoinColumn(name = "t_usuario_Id_Usuario", referencedColumnName = "Id_Usuario")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<TUsuario> tUsuarioCollection;

    public TRol() {
    }

    public TRol(Integer idrol) {
        this.idrol = idrol;
    }

    public TRol(Integer idrol, String nombre) {
        this.idrol = idrol;
        this.nombre = nombre;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<TUsuario> getTUsuarioCollection() {
        return tUsuarioCollection;
    }

    public void setTUsuarioCollection(Collection<TUsuario> tUsuarioCollection) {
        this.tUsuarioCollection = tUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRol)) {
            return false;
        }
        TRol other = (TRol) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TRol[ idrol=" + idrol + " ]";
    }
    
}
