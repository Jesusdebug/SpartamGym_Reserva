/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.controlador;

import com.mysql.jdbc.Connection;
import edu.reserva.entity.TAdministrador;
import edu.reserva.entity.TAgendaRutina;
import edu.reserva.entity.TEntrenador;
import edu.reserva.entity.TRutina;
import edu.reserva.facade.TAdministradorFacadeLocal;
import edu.reserva.facade.TAgendaRutinaFacadeLocal;
import edu.reserva.facade.TEntrenadorFacadeLocal;
import edu.reserva.facade.TRutinaFacadeLocal;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.DriverManager;
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
@Named(value = "agendaRutinaView")
@ViewScoped
public class agendaRutinaView implements Serializable {

    @EJB
    TAgendaRutinaFacadeLocal tAgendaRutinaFacadeLocal;
    @EJB
    TRutinaFacadeLocal tRutinaFacadeLocal;
    @EJB
    TEntrenadorFacadeLocal tEntrenadorFacadeLocal;
    @EJB
    TAdministradorFacadeLocal tAdministradorFacadeLocal;
    /**
     * Creates a new instance of agendaRutinaView
     */
    private List<TAdministrador> listaAdministrador = new ArrayList<>();
    private int administradorId;
    private List<TEntrenador> listaEntrenador = new ArrayList<>();
    private int entrenadorId;
    private List<TRutina> listaRutina = new ArrayList<>();
    private int rutinaId;
    private List<TAgendaRutina> listaAgendaRutina = new ArrayList<>();
    private TAgendaRutina regAgendaRutina = new TAgendaRutina();
    private TAgendaRutina gestionRegAgendaRutina = new TAgendaRutina();
    private int agendaRutinaId;

    public agendaRutinaView() {
    }

    @PostConstruct
    public void cargarlistas() {
       listaAgendaRutina.addAll(tAgendaRutinaFacadeLocal.findAll());
        listaRutina.addAll(tRutinaFacadeLocal.findAll());
        listaEntrenador.addAll(tEntrenadorFacadeLocal.findAll());
        listaAdministrador.addAll(tAdministradorFacadeLocal.findAll());
      
    }

    public void registrarAgendaRutina() {
        TRutina agendaRutina = tRutinaFacadeLocal.find(rutinaId);
        regAgendaRutina.setIdrutina(agendaRutina);
        TEntrenador nuewEntrenador = tEntrenadorFacadeLocal.find(entrenadorId);
        regAgendaRutina.setIdentrenador(nuewEntrenador);
        TAdministrador newAdministrador = tAdministradorFacadeLocal.find(administradorId);
        regAgendaRutina.setIdadministrador(newAdministrador);
        tAgendaRutinaFacadeLocal.create(regAgendaRutina);
        regAgendaRutina = new TAgendaRutina();

    }
      
       public void descargaRporteRutinas() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spartamgym_reserva", "root", "");
            File jasper = new File(context.getRealPath("/Reportes/reportAgendasRutina.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);
            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Rutinas.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();
        } catch (Exception e) {
            System.out.println("edu.reserva.controlador.agendaRutinaView.descargaRporteRutinas()"+e.getMessage());
        }
    }

    public void cargaModal(TAgendaRutina rutina) {
        gestionRegAgendaRutina = new TAgendaRutina();
        gestionRegAgendaRutina = rutina;
    }

    public void eliminarAsig( int idagendaRutina) {
        try {
            gestionRegAgendaRutina = tAgendaRutinaFacadeLocal.find(idagendaRutina);
            tAgendaRutinaFacadeLocal.remove(gestionRegAgendaRutina);
            gestionRegAgendaRutina = new TAgendaRutina();
        } catch (Exception e) {
            System.out.println("edu.cidesem.controlador.GestionView.eliminarAsig()" + e.getMessage());
        }

    }
     public void cargarlista() {
   try {
                FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("RAgenda.xhtml");

         } catch (Exception e) {
             
         }
     }

    public List<TAdministrador> getListaAdministrador() {
        return listaAdministrador;
    }

    public void setListaAdministrador(List<TAdministrador> listaAdministrador) {
        this.listaAdministrador = listaAdministrador;
    }

    public int getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(int administradorId) {
        this.administradorId = administradorId;
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

    public List<TRutina> getListaRutina() {
        return listaRutina;
    }

    public void setListaRutina(List<TRutina> listaRutina) {
        this.listaRutina = listaRutina;
    }

    public int getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(int rutinaId) {
        this.rutinaId = rutinaId;
    }

    public TAgendaRutina getRegAgendaRutina() {
        return regAgendaRutina;
    }

    public void setRegAgendaRutina(TAgendaRutina regAgendaRutina) {
        this.regAgendaRutina = regAgendaRutina;
    }

    public TAgendaRutina getGestionRegAgendaRutina() {
        return gestionRegAgendaRutina;
    }

    public void setGestionRegAgendaRutina(TAgendaRutina gestionRegAgendaRutina) {
        this.gestionRegAgendaRutina = gestionRegAgendaRutina;
    }

    public List<TAgendaRutina> getListaAgendaRutina() {
        return listaAgendaRutina;
    }

    public void setListaAgendaRutina(List<TAgendaRutina> listaAgendaRutina) {
        this.listaAgendaRutina = listaAgendaRutina;
    }

    public int getAgendaRutinaId() {
        return agendaRutinaId;
    }

    public void setAgendaRutinaId(int agendaRutinaId) {
        this.agendaRutinaId = agendaRutinaId;
    }

}
