/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TEntrenador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TEntrenadorFacadeLocal {

    void create(TEntrenador tEntrenador);

    void edit(TEntrenador tEntrenador);

    void remove(TEntrenador tEntrenador);

    TEntrenador find(Object id);

    List<TEntrenador> findAll();

    List<TEntrenador> findRange(int[] range);

    int count();
    
}
