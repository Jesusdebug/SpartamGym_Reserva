/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.controlador;

import com.mysql.jdbc.Connection;
import edu.reserva.entity.TAdministrador;
import edu.reserva.entity.TEntrenador;
import edu.reserva.entity.TEvento;
import edu.reserva.facade.TAdministradorFacadeLocal;
import edu.reserva.facade.TEntrenadorFacadeLocal;
import edu.reserva.facade.TEventoFacadeLocal;
import java.sql.DriverManager;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Antonio
 */
@Named(value = "eventosView")
@ViewScoped
public class eventosView implements Serializable {

    @EJB
    TEventoFacadeLocal tEventoFacadeLocal;
    @EJB
    TEntrenadorFacadeLocal tEntrenadorFacadeLocal;
    @EJB
    TAdministradorFacadeLocal tAdministradorFacadeLocal;

    /**
     * Creates a new instance of eventosView
     */
    private TEvento regEvento = new TEvento();
    private List<TEntrenador> listaEntrenador = new ArrayList<>();
    private List<TAdministrador> listaAdministrador = new ArrayList<>();
private List<TEvento> listaEventos = new ArrayList<>();
    private String nombre;
    private String descripcion;
    private String lugar;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private int fk_entrenador;
    private int entrenadorId;
    private int fk_administrador;
    private int administradorId;

    public eventosView() {
    }

    @PostConstruct
    public void cargaId() {
        listaEntrenador.addAll(tEntrenadorFacadeLocal.findAll());
        listaAdministrador.addAll(tAdministradorFacadeLocal.findAll());
        listaEventos.addAll(tEventoFacadeLocal.findAll());
    }

    public void registrarEvento() {
        TEntrenador nuevoEntrenador = tEntrenadorFacadeLocal.find(entrenadorId);
        regEvento.setTentrenadorIdentrenador(nuevoEntrenador);
        TAdministrador nuevoAdministrador = tAdministradorFacadeLocal.find(administradorId);
        regEvento.setTadministradorIdadministrador(nuevoAdministrador);
        tEventoFacadeLocal.registroEventos(nombre, descripcion, lugar, fecha, horaInicio, horaFin, entrenadorId, administradorId);
        regEvento = new TEvento();
    }

    
    public void descargaListadoUsuaroios_Roles() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            //Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/SpartamGym_Reservas", "root", "");
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spartamgym_reserva", "root", "");
            File jasper = new File(context.getRealPath("/reportes/Usuarios.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=listadoEstudiantes.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
        } catch (Exception e) {
            System.out.println("edu.cidesem.controlador.GestionView.descargaListadoEstudiantes()"+e.getMessage());
        }
    }
    
    
    
    
    public List<TEntrenador> getListaEntrenador() {
        return listaEntrenador;
    }

    public void setListaEntrenador(List<TEntrenador> listaEntrenador) {
        this.listaEntrenador = listaEntrenador;
    }

    public List<TAdministrador> getListaAdministrador() {
        return listaAdministrador;
    }

    public void setListaAdministrador(List<TAdministrador> listaAdministrador) {
        this.listaAdministrador = listaAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getFk_entrenador() {
        return fk_entrenador;
    }

    public void setFk_entrenador(int fk_entrenador) {
        this.fk_entrenador = fk_entrenador;
    }

    public int getFk_administrador() {
        return fk_administrador;
    }

    public void setFk_administrador(int fk_administrador) {
        this.fk_administrador = fk_administrador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    public int getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(int administradorId) {
        this.administradorId = administradorId;
    }

    public TEvento getRegEvento() {
        return regEvento;
    }

    public void setRegEvento(TEvento regEvento) {
        this.regEvento = regEvento;
    }

    public List<TEvento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<TEvento> listaEventos) {
        this.listaEventos = listaEventos;
    }

}
