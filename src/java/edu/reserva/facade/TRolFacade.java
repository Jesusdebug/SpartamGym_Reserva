/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio
 */
@Stateless
public class TRolFacade extends AbstractFacade<TRol> implements TRolFacadeLocal {

    @PersistenceContext(unitName = "SpartamGym_ReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TRolFacade() {
        super(TRol.class);
    }
    
    //INSERT INTO `t_usuario_has_t_rol` (`t_usuario_Id_Usuario`, `t_rol_Id_rol`) VALUES (?, ?);
    @Override
    public boolean ingresarRol(int t_usuario_Id_usuarios, int t_rol_Id_rol){
    try {
        Query q = em.createNativeQuery("INSERT INTO `t_usuario_has_t_rol` (`t_usuario_Id_Usuario`, `t_rol_Id_rol`) VALUES (?, ?)");
        q.setParameter(1, t_usuario_Id_usuarios);
        q.setParameter(2, t_rol_Id_rol);
        q.executeUpdate();
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}
