/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.controlador;

import edu.reserva.entity.TAgendaEvento;
import edu.reserva.entity.TEvento;
import edu.reserva.facade.TAgendaEventoFacadeLocal;
import edu.reserva.facade.TEntrenadorFacadeLocal;
import edu.reserva.facade.TEventoFacadeLocal;
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
@Named(value = "agendaView")
@ViewScoped
public class agendaView implements Serializable{

    @EJB
    TEventoFacadeLocal tEventoFacadeLocal;
    @EJB
    TAgendaEventoFacadeLocal tAgendaEventoFacadeLocal;
 /**
     * Creates a new instance of agendaView
     */
    private TAgendaEvento regAgendaEvento = new TAgendaEvento();
    private List<TEvento> listaEventos = new ArrayList<>();
    private List<TAgendaEvento>listaAgendaEvento = new ArrayList<>();
    private TEvento gestionEvento = new TEvento();
    private int eventoId;
    public agendaView() {
    }
    
    @PostConstruct
    public void cargaInicial(){
        listaAgendaEvento.addAll(tAgendaEventoFacadeLocal.findAll());
        listaEventos.addAll(tEventoFacadeLocal.findAll());
    }
    public void cargaEventos(TEvento evento){
        gestionEvento = new TEvento();
        gestionEvento= evento;
     
        
       
    }
    public void agendar(){
        TEvento nuevoEvento = tEventoFacadeLocal.find(eventoId);
        regAgendaEvento.setIdevento(nuevoEvento);
        tAgendaEventoFacadeLocal.create(regAgendaEvento);
        regAgendaEvento = new TAgendaEvento();
    }
    
    public void eliminar(int idevento){
        regAgendaEvento = tAgendaEventoFacadeLocal.find(idevento);
        tAgendaEventoFacadeLocal.remove(regAgendaEvento);
    }

    public TAgendaEvento getRegAgendaEvento() {
        return regAgendaEvento;
    }

    public void setRegAgendaEvento(TAgendaEvento regAgendaEvento) {
        this.regAgendaEvento = regAgendaEvento;
    }

    public List<TEvento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<TEvento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public TEvento getGestionEvento() {
        return gestionEvento;
    }

    public void setGestionEvento(TEvento gestionEvento) {
        this.gestionEvento = gestionEvento;
    }

    public List<TAgendaEvento> getListaAgendaEvento() {
        return listaAgendaEvento;
    }

    public void setListaAgendaEvento(List<TAgendaEvento> listaAgendaEvento) {
        this.listaAgendaEvento = listaAgendaEvento;
    }
    
}
