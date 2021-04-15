/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TRutina;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TRutinaFacadeLocal {

    void create(TRutina tRutina);

    void edit(TRutina tRutina);

    void remove(TRutina tRutina);

    TRutina find(Object id);

    List<TRutina> findAll();

    List<TRutina> findRange(int[] range);

    int count();
    
}
