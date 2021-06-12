/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TAgendaRutina;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TAgendaRutinaFacadeLocal {

    void create(TAgendaRutina tAgendaRutina);

    void edit(TAgendaRutina tAgendaRutina);

    void remove(TAgendaRutina tAgendaRutina);

    TAgendaRutina find(Object id);

    List<TAgendaRutina> findAll();

    List<TAgendaRutina> findRange(int[] range);

    int count();

    public List<TAgendaRutina> listarRutina();
    
}
