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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "t_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUsuario.findAll", query = "SELECT t FROM TUsuario t")
    , @NamedQuery(name = "TUsuario.findByIdUsuario", query = "SELECT t FROM TUsuario t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TUsuario.findByTipodoc", query = "SELECT t FROM TUsuario t WHERE t.tipodoc = :tipodoc")
    , @NamedQuery(name = "TUsuario.findByNumerodoc", query = "SELECT t FROM TUsuario t WHERE t.numerodoc = :numerodoc")
    , @NamedQuery(name = "TUsuario.findByNombres", query = "SELECT t FROM TUsuario t WHERE t.nombres = :nombres")
    , @NamedQuery(name = "TUsuario.findByApellidos", query = "SELECT t FROM TUsuario t WHERE t.apellidos = :apellidos")
    , @NamedQuery(name = "TUsuario.findByDireccion", query = "SELECT t FROM TUsuario t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "TUsuario.findByNumerocelular", query = "SELECT t FROM TUsuario t WHERE t.numerocelular = :numerocelular")
    , @NamedQuery(name = "TUsuario.findByCorreo", query = "SELECT t FROM TUsuario t WHERE t.correo = :correo")
    , @NamedQuery(name = "TUsuario.findByClave", query = "SELECT t FROM TUsuario t WHERE t.clave = :clave")
    , @NamedQuery(name = "TUsuario.findByEstado", query = "SELECT t FROM TUsuario t WHERE t.estado = :estado")})
public class TUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tipo_doc")
    private String tipodoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Numero_doc")
    private String numerodoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Numero_celular")
    private String numerocelular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Clave")
    private String clave;
    @Column(name = "Estado")
    private Short estado;
    @ManyToMany(mappedBy = "tUsuarioCollection", fetch = FetchType.LAZY)
    private Collection<TRol> tRolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusuarioIdUsuario", fetch = FetchType.LAZY)
    private Collection<TCliente> tClienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusuarioIdUsuario", fetch = FetchType.LAZY)
    private Collection<TEntrenador> tEntrenadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusuarioIdUsuario", fetch = FetchType.LAZY)
    private Collection<TAdministrador> tAdministradorCollection;

    public TUsuario() {
    }

    public TUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TUsuario(Integer idUsuario, String tipodoc, String numerodoc, String nombres, String apellidos, String direccion, String numerocelular, String correo, String clave) {
        this.idUsuario = idUsuario;
        this.tipodoc = tipodoc;
        this.numerodoc = numerodoc;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.numerocelular = numerocelular;
        this.correo = correo;
        this.clave = clave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getNumerodoc() {
        return numerodoc;
    }

    public void setNumerodoc(String numerodoc) {
        this.numerodoc = numerodoc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumerocelular() {
        return numerocelular;
    }

    public void setNumerocelular(String numerocelular) {
        this.numerocelular = numerocelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<TRol> getTRolCollection() {
        return tRolCollection;
    }

    public void setTRolCollection(Collection<TRol> tRolCollection) {
        this.tRolCollection = tRolCollection;
    }

    @XmlTransient
    public Collection<TCliente> getTClienteCollection() {
        return tClienteCollection;
    }

    public void setTClienteCollection(Collection<TCliente> tClienteCollection) {
        this.tClienteCollection = tClienteCollection;
    }

    @XmlTransient
    public Collection<TEntrenador> getTEntrenadorCollection() {
        return tEntrenadorCollection;
    }

    public void setTEntrenadorCollection(Collection<TEntrenador> tEntrenadorCollection) {
        this.tEntrenadorCollection = tEntrenadorCollection;
    }

    @XmlTransient
    public Collection<TAdministrador> getTAdministradorCollection() {
        return tAdministradorCollection;
    }

    public void setTAdministradorCollection(Collection<TAdministrador> tAdministradorCollection) {
        this.tAdministradorCollection = tAdministradorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUsuario)) {
            return false;
        }
        TUsuario other = (TUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TUsuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
