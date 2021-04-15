/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TAgendaEvento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TAgendaEventoFacadeLocal {

    void create(TAgendaEvento tAgendaEvento);

    void edit(TAgendaEvento tAgendaEvento);

    void remove(TAgendaEvento tAgendaEvento);

    TAgendaEvento find(Object id);

    List<TAgendaEvento> findAll();

    List<TAgendaEvento> findRange(int[] range);

    int count();
    
}
