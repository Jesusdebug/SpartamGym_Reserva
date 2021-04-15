/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TAdministrador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TAdministradorFacadeLocal {

    void create(TAdministrador tAdministrador);

    void edit(TAdministrador tAdministrador);

    void remove(TAdministrador tAdministrador);

    TAdministrador find(Object id);

    List<TAdministrador> findAll();

    List<TAdministrador> findRange(int[] range);

    int count();
    
}
