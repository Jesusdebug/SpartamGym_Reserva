/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TCliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TClienteFacadeLocal {

    void create(TCliente tCliente);

    void edit(TCliente tCliente);

    void remove(TCliente tCliente);

    TCliente find(Object id);

    List<TCliente> findAll();

    List<TCliente> findRange(int[] range);

    int count();
    
}
