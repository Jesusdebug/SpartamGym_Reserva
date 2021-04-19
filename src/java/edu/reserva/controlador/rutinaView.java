/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.controlador;

import edu.reserva.entity.TEntrenador;
import edu.reserva.entity.TRutina;
import edu.reserva.facade.TEntrenadorFacadeLocal;
import edu.reserva.facade.TRutinaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Antonio
 */
@Named(value = "rutinaView")
@ViewScoped
public class rutinaView implements Serializable {

    @EJB
    TRutinaFacadeLocal rutinaFacadeLocal;
    @EJB
    TEntrenadorFacadeLocal tEntrenadorFacadeLocal;

    /**
     * Creates a new instance of rutinaView
     */
    private TRutina regrutina = new TRutina();
    private List<TEntrenador> listaEntrenador = new ArrayList<>();
    private List<TRutina> listaRutina = new ArrayList<>();
    private int entrenadorId;
    private String Tipo;
    private String Nombre;
    private String Descripcion;
    private String Fecha;
    private String Hora_inicio;
    private String Hora_fin;
    private int t_entrenador_Id_entrenador;

    public rutinaView() {
    }

    @PostConstruct
    public void cargaInicial() {
        listaEntrenador.addAll(tEntrenadorFacadeLocal.findAll());
    }

    public void registroRutina() {
        TEntrenador nuevoEntrenador = tEntrenadorFacadeLocal.find(entrenadorId);
        regrutina.setTentrenadorIdentrenador(nuevoEntrenador);
        rutinaFacadeLocal.ResgistrarRutina(Tipo, Nombre, Descripcion, Fecha, Hora_inicio, Hora_fin, entrenadorId);
        regrutina = new TRutina();
    }

    public TRutina getRegrutina() {
        return regrutina;
    }

    public void setRegrutina(TRutina regrutina) {
        this.regrutina = regrutina;
    }

    public List<TEntrenador> getListaEntrenador() {
        return listaEntrenador;
    }

    public void setListaEntrenador(List<TEntrenador> listaEntrenador) {
        this.listaEntrenador = listaEntrenador;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora_inicio() {
        return Hora_inicio;
    }

    public void setHora_inicio(String Hora_inicio) {
        this.Hora_inicio = Hora_inicio;
    }

    public String getHora_fin() {
        return Hora_fin;
    }

    public void setHora_fin(String Hora_fin) {
        this.Hora_fin = Hora_fin;
    }

    public int getT_entrenador_Id_entrenador() {
        return t_entrenador_Id_entrenador;
    }

    public void setT_entrenador_Id_entrenador(int t_entrenador_Id_entrenador) {
        this.t_entrenador_Id_entrenador = t_entrenador_Id_entrenador;
    }

    public List<TRutina> getListaRutina() {
        return listaRutina;
    }

    public void setListaRutina(List<TRutina> listaRutina) {
        this.listaRutina = listaRutina;
    }

}
