/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.controlador.usuariosSesion;
import edu.reserva.entity.TUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio
 */
@Stateless
public class TUsuarioFacade extends AbstractFacade<TUsuario> implements TUsuarioFacadeLocal {

    @PersistenceContext(unitName = "SpartamGym_ReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUsuarioFacade() {
        super(TUsuario.class);
    }
    @Override
    public TUsuario validar(String correoIn, String claveIn){
        try {
            Query q = em.createQuery("SELECT Tu FROM TUsuario Tu WHERE Tu.correo =:correoIn AND Tu.clave=:claveIn");
            q.setParameter("correoIn", correoIn);
            q.setParameter("claveIn", claveIn);
            TUsuario usuLog = (TUsuario) q.getSingleResult();
            return usuLog;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public TUsuario buscarCorreo(String correo){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT Tu FROM TUsuario Tu WHERE Tu.correo =:correo");
            q.setParameter("correo", correo);
            return (TUsuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
