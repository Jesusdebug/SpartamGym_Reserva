/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TEvento;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio
 */
@Stateless
public class TEventoFacade extends AbstractFacade<TEvento> implements TEventoFacadeLocal {

    @PersistenceContext(unitName = "SpartamGym_ReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TEventoFacade() {
        super(TEvento.class);
    }

    @Override
    public boolean registroEventos(String Nombre, String Descripcion, String Lugar, Date Fecha, String Hora_inicio, String Hora_fin, int t_entrenador_Id_entrenador, int t_administrador_Id_administrador) {
        try {
            Query q = em.createNativeQuery("INSERT INTO `t_evento` (`Nombre`, `Descripcion`, `Lugar`, `Fecha`, `Hora_inicio`, `Hora_fin`, `t_entrenador_Id_entrenador`, `t_administrador_Id_administrador`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            q.setParameter(1, Nombre);
            q.setParameter(2, Descripcion);
            q.setParameter(3,Lugar);
            q.setParameter(4, Fecha);
            q.setParameter(5, Hora_inicio);
            q.setParameter(6, Hora_fin);
            q.setParameter(7, t_entrenador_Id_entrenador);
            q.setParameter(8, t_administrador_Id_administrador);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
