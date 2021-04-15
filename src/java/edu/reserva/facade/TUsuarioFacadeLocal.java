/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TUsuarioFacadeLocal {

    void create(TUsuario tUsuario);

    void edit(TUsuario tUsuario);

    void remove(TUsuario tUsuario);

    TUsuario find(Object id);

    List<TUsuario> findAll();

    List<TUsuario> findRange(int[] range);

    int count();

    public TUsuario validar(String correoIn, String claveIn);

    public TUsuario buscarCorreo(String correoIn);
    
}
