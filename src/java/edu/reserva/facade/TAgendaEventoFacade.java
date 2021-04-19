/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TAgendaEvento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio
 */
@Stateless
public class TAgendaEventoFacade extends AbstractFacade<TAgendaEvento> implements TAgendaEventoFacadeLocal {

    @PersistenceContext(unitName = "SpartamGym_ReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TAgendaEventoFacade() {
        super(TAgendaEvento.class);
    }
    @Override
   public boolean resitrarAgenda(int Id_evento){
        try {
            Query q = em.createNativeQuery("INSERT INTO`t_agenda_evento` (`Id_evento`) VALUES ( ?)");
            q.setParameter(1,Id_evento);
            return true;
                    
        } catch (Exception e) {
            return false;
        }
    }
}
