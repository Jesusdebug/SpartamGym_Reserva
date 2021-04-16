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
    private List<TUsuario> listaUsuario = new ArrayList<>();
    //atributos de la clase y atrubtos para operar en la vista
    private String bandera = "";
    private String correoIn = "";
    private String claveIn = "";
    private int idUsuario = 0;

    //metodo contructor
    public usuariosSesion() {
    }

    @PostConstruct
    public void cargaInicial() {
        listaUsuario.addAll(tUsuarioFacadeLocal.findAll());
        listaUsuario.addAll(tUsuarioFacadeLocal.listaUsuarios());
    }

    public void registrarUsuario() {
        regUsu.setEstado(Short.valueOf("1"));
        tUsuarioFacadeLocal.create(regUsu);
        regUsu = new TUsuario();
    }

    public void ingresarUsuario() {
        try {
            logUsuario = tUsuarioFacadeLocal.validar(correoIn, claveIn);
            if (logUsuario.getIdUsuario() != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect("administrador/index.xhtml");

            } else {

                bandera = "2";
            }
        } catch (IOException e) {
            bandera = "4";
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
            bandera = "3";
        }

    }

    public void eliminarUsuario(int Id_usuario) {
        logUsuario = tUsuarioFacadeLocal.find(Id_usuario);
        listaUsuario.remove(logUsuario);
        tUsuarioFacadeLocal.remove(logUsuario);

    }

    public void cargaUsuarios(FileUploadEvent event) throws IOException {

        InputStream input = event.getFile().getInputStream();
        List cellData = new ArrayList();
        try {
            XSSFWorkbook workobook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workobook.getSheetAt(0);
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
        } catch (Exception e) {
//            System.out.println("edu.spartamgym.controlador.usuariosSesion.cargaUsuarios()" + e.getMessage());
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

}
