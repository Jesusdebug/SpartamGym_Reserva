/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reserva.facade;

import edu.reserva.entity.TRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface TRolFacadeLocal {

    void create(TRol tRol);

    void edit(TRol tRol);

    void remove(TRol tRol);

    TRol find(Object id);

    List<TRol> findAll();

    List<TRol> findRange(int[] range);

    int count();

    public boolean ingresarRol(int t_usuario_Id_usuarios, int t_rol_Id_rol);
    
}
