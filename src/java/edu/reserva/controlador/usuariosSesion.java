/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.controlador;

import edu.reserva.entity.TAgendaEvento;
import edu.reserva.entity.TEvento;
import edu.reserva.entity.TRol;
import edu.reserva.entity.TUsuario;
import edu.reserva.facade.TAgendaEventoFacadeLocal;
import edu.reserva.facade.TRolFacadeLocal;
import edu.reserva.facade.TUsuarioFacadeLocal;
import edu.reserva.utilities.Email;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import edu.reserva.facade.TEventoFacadeLocal;

/**
 *
 * @author Antonio
 */
@Named(value = "usuariosSesion")
@SessionScoped
public class usuariosSesion  implements Serializable{

    @EJB
    TAgendaEventoFacadeLocal tAgendaEventoFacadeLocal;
    @EJB
    TEventoFacadeLocal tEventoFacadeLocal;
    @EJB
    TUsuarioFacadeLocal tUsuarioFacadeLocal;
    @EJB
    TRolFacadeLocal tRolFacadeLocal;

    /**
     * Creates a new instance of usuariosSesion
     */
    //onjetos de las clases
    private TUsuario regUsu = new TUsuario();
    private TUsuario logUsuario = new TUsuario();
    private List<TUsuario> listaUsuario = new ArrayList<>();
    private List<TRol> listaRol = new ArrayList<>();
    private TRol rol = new TRol();
    private int idRol;
    //atributos de la clase y atrubtos para operar en la vista
    private String bandera = "";
    private String correoIn = "";
    private String claveIn = "";
    private int idUsuario = 0;
    private int eventoId;
    private TAgendaEvento regAgendaEvento = new TAgendaEvento();
List<agendaView> agenda = new ArrayList<>();
    //metodo contructor
    public usuariosSesion() {
    }

    @PostConstruct
    public void cargaInicial() {
        listaUsuario.addAll(tUsuarioFacadeLocal.listaUsuarios());
        listaRol.addAll(tRolFacadeLocal.findAll());
    }

    public void registrarUsuario() {
        regUsu.setEstado(Short.valueOf("1"));
        tUsuarioFacadeLocal.create(regUsu);
        tRolFacadeLocal.ingresarRol(regUsu.getIdUsuario(), 1);
        regUsu = new TUsuario();
    }

    public void crearRol() {
        tRolFacadeLocal.ingresarRol(idUsuario, idRol);
        regUsu = new TUsuario();
    }

    public void ingresarUsuario() {
        try {
            logUsuario = tUsuarioFacadeLocal.validar(correoIn, claveIn);

            if (logUsuario == null) {
                bandera = "2";
            } else {
                if (logUsuario.getEstado() == 1 && logUsuario.getIdUsuario().equals(41)) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("administrador/index.xhtml");

                } else {
                    if (logUsuario.getEstado() == 1 && logUsuario.getIdUsuario().equals(134)) {
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("entrenador/index.xhtml");
                    } else {
                        if (logUsuario.getEstado() == 1 ) {
                            FacesContext fc = FacesContext.getCurrentInstance();
                            fc.getExternalContext().redirect("cliente/index.xhtml");
                        }
                    }
                }
                bandera = "3";
            }
        } catch (IOException e) {
            bandera = "Intente nuevamente";
        }
    }

    public void recuperarClave() {
        try {
            logUsuario = tUsuarioFacadeLocal.buscarCorreo(correoIn);
            if (logUsuario == null) {
                bandera = "2";
            } else {
                String nuevaClave = "Reserva spartmaGym" + 100 * Math.random();
                tUsuarioFacadeLocal.edit(logUsuario);
                Email.sendClaves(logUsuario.getCorreo(), logUsuario.getNombres() + " " + logUsuario.getApellidos(), nuevaClave);
            }
        } catch (Exception e) {
            System.out.println("edu.reserva.controlador.usuariosSesion.recuperarClave()" + e.getMessage());
        }

    }

    public void eliminarUsuario(int Id_usuario) {
        logUsuario = tUsuarioFacadeLocal.find(Id_usuario);
        listaUsuario.remove(logUsuario);
        logUsuario = new TUsuario();

    }

    public void cargaUsuarios(FileUploadEvent event) throws IOException {

        InputStream input = event.getFile().getInputStream();
        List cellData = new ArrayList();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workbook.getSheetAt(0);
            Iterator rowIterador = hssfSheet.rowIterator();
            rowIterador.next();
            while (rowIterador.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterador.next();
                Iterator iterador = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                while (iterador.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterador.next();
                    //este arreglo lo guarda en el objeto temporal
                    cellTemp.add(hssfCell);
                }

                cellData.add(cellTemp);
            }

            for (int i = 0; i < cellData.size(); i++) {
                List cellTemp = (List) cellData.get(i);
                TUsuario RegUsu = new TUsuario();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell celda = (XSSFCell) cellTemp.get(j);

                    switch (j) {
                        case 0:
                            RegUsu.setTipodoc(celda.toString());
                            break;
                        case 1:
                            RegUsu.setNumerodoc(celda.toString());
                            break;
                        case 2:
                            RegUsu.setNombres(celda.toString());
                            break;
                        case 3:
                            RegUsu.setApellidos(celda.toString());
                            break;
                        case 4:
                            RegUsu.setNumerocelular(celda.toString());
                            break;
                        case 5:
                            RegUsu.setDireccion(celda.toString());
                            break;
                        case 6:
                            RegUsu.setCorreo(celda.toString());
                            break;
                        case 7:
                            RegUsu.setClave(celda.toString());
                            tUsuarioFacadeLocal.registrarUsuariosCarga(RegUsu.getTipodoc(), RegUsu.getNumerodoc(), RegUsu.getNombres(), RegUsu.getApellidos(), RegUsu.getDireccion(), RegUsu.getNumerocelular(), RegUsu.getCorreo(), RegUsu.getClave());
                            RegUsu.setClave(claveIn);
                            tUsuarioFacadeLocal.edit(RegUsu);
                            Email.sendClaves(RegUsu.getCorreo(), RegUsu.getNombres(), RegUsu.getClave());
                            break;
                    }

                }

            }
        } catch (IOException e) {
            System.out.println("edu.spartamgym.controlador.usuariosSesion.cargaUsuarios()" + e.getMessage());
        }
    }

//notificaciones
    public void agendar() {
        
         try {
             TEvento nuevoEvento = tEventoFacadeLocal.find(eventoId);
        regAgendaEvento.setIdevento(nuevoEvento);
        tAgendaEventoFacadeLocal.create(regAgendaEvento);
        regAgendaEvento = new TAgendaEvento();
       
            logUsuario = tUsuarioFacadeLocal.buscarCorreo(correoIn);
            if (logUsuario == null) {
                bandera = "2";
            } else {
                String mensaje = "Te has agendado un evento revisa la pagina web";
                tUsuarioFacadeLocal.edit(logUsuario);
            Email.sendNotificacion(logUsuario.getCorreo(), logUsuario.getNombres()+" "+logUsuario.getApellidos(), mensaje);
            }
        } catch (Exception e) {
            System.out.println("edu.reserva.controlador.usuariosSesion.recuperarClave()" + e.getMessage());
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

    public List<TUsuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<TUsuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<TRol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<TRol> listaRol) {
        this.listaRol = listaRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public TRol getRol() {
        return rol;
    }

    public void setRol(TRol rol) {
        this.rol = rol;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public TAgendaEvento getRegAgendaEvento() {
        return regAgendaEvento;
    }

    public void setRegAgendaEvento(TAgendaEvento regAgendaEvento) {
        this.regAgendaEvento = regAgendaEvento;
    }

}
