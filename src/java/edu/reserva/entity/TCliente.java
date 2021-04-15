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
@Table(name = "t_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCliente.findAll", query = "SELECT t FROM TCliente t")
    , @NamedQuery(name = "TCliente.findByIdcliente", query = "SELECT t FROM TCliente t WHERE t.idcliente = :idcliente")})
public class TCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_cliente")
    private Integer idcliente;
    @JoinColumn(name = "t_usuario_Id_Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUsuario tusuarioIdUsuario;

    public TCliente() {
    }

    public TCliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public TUsuario getTusuarioIdUsuario() {
        return tusuarioIdUsuario;
    }

    public void setTusuarioIdUsuario(TUsuario tusuarioIdUsuario) {
        this.tusuarioIdUsuario = tusuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCliente)) {
            return false;
        }
        TCliente other = (TCliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.reserva.entity.TCliente[ idcliente=" + idcliente + " ]";
    }
    
}
