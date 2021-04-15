/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.controlador;

import edu.reserva.entity.TUsuario;
import edu.reserva.facade.TUsuarioFacadeLocal;
import edu.reserva.utilities.Email;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Antonio
 */
@Named(value = "usuariosSesion")
@SessionScoped
public class usuariosSesion implements Serializable {

    @EJB
    TUsuarioFacadeLocal tUsuarioFacadeLocal;
    
    /**
     * Creates a new instance of usuariosSesion
     */
    //onjetos de las clases
    private TUsuario regUsu = new TUsuario();
    private TUsuario logUsuario = new TUsuario();
    
    //atributos de la clase y atrubtos para operar en la vista
    private String bandera = "";
    private String correoIn="";
    private String claveIn="";
    
    
    
    
    //metodo contructor
    public usuariosSesion() {
    }
    
    
    public void registrarUsuario(){
        tUsuarioFacadeLocal.create(regUsu);
        regUsu = new TUsuario();
    }
    public void ingresarUsuario(){
        try {
            logUsuario= tUsuarioFacadeLocal.validar(correoIn, claveIn);
            if (logUsuario.getIdUsuario()!=null) {
                   FacesContext fc= FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect("administrador/index.xhtml");
            
            }else{
                
               bandera = "2";
             }
        } catch (IOException e) {
             bandera = "4";
        }
    }
    public void recuperarClave(){
        try {
          logUsuario = tUsuarioFacadeLocal.buscarCorreo(correoIn);
            if (logUsuario ==null) {
                bandera="2";
            }else{
                String nuevaClave = "Reserva spartmaGym" + 100*Math.random();
               tUsuarioFacadeLocal.edit(logUsuario);
                Email.sendClaves(logUsuario.getCorreo(), logUsuario.getNombres() +" "+ logUsuario.getApellidos(),nuevaClave);
            }
        } catch (Exception e) {
            bandera="3";
        }
        
        
    }
    
    
    
    //metthods getters end setters

    public TUsuario getRegUsu() {
        return regUsu;
    }

    public void setRegUsu(TUsuario regUsu) {
        this.regUsu = regUsu;
    }

    public TUsuario getLogUsuario() {
        return logUsuario;
    }

    public void setLogUsuario(TUsuario logUsuario) {
        this.logUsuario = logUsuario;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClaveIn() {
        return claveIn;
    }

    public void setClaveIn(String claveIn) {
        this.claveIn = claveIn;
    }
    
    
}
