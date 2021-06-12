/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TAgendaRutina;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio
 */
@Stateless
public class TAgendaRutinaFacade extends AbstractFacade<TAgendaRutina> implements TAgendaRutinaFacadeLocal {

    @PersistenceContext(unitName = "SpartamGym_ReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TAgendaRutinaFacade() {
        super(TAgendaRutina.class);
    }
    public List<TAgendaRutina> listarRutina(){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT agr FROM TAgendaRutina agr");
            return q.getResultList();
            
        } catch (Exception e) {
            return null;
        }
        
    }
}
