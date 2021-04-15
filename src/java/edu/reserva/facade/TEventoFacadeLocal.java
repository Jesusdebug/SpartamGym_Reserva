/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TEvento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TEventoFacadeLocal {

    void create(TEvento tEvento);

    void edit(TEvento tEvento);

    void remove(TEvento tEvento);

    TEvento find(Object id);

    List<TEvento> findAll();

    List<TEvento> findRange(int[] range);

    int count();
    
}
