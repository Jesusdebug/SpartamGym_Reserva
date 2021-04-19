/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TRutina;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio
 */
@Stateless
public class TRutinaFacade extends AbstractFacade<TRutina> implements TRutinaFacadeLocal {

    @PersistenceContext(unitName = "SpartamGym_ReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TRutinaFacade() {
        super(TRutina.class);
    }

    @Override
    public boolean ResgistrarRutina(String Tipo, String Nombre, String Descripcion, String Fecha, String Hora_inicio, String Hora_fin, int t_entrenador_Id_entrenador) {
        try {
            Query q = em.createNativeQuery("INSERT INTO `t_rutina` (`Tipo`, `Nombre`, `Descripcion`, `Fecha`, `Hora_inicio`, `Hora_fin`, `t_entrenador_Id_entrenador`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            q.setParameter(1, Tipo);
            q.setParameter(2, Nombre);
            q.setParameter(3, Descripcion);
            q.setParameter(4, Fecha);
            q.setParameter(5, Hora_inicio);
            q.setParameter(6, Hora_fin);
            q.setParameter(7, t_entrenador_Id_entrenador);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
}
