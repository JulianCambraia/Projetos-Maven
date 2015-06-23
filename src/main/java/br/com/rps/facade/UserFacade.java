/*
 * Interface que irá servir de ponte entre a view e o DAO.
 * com isto tramitando dados entre View e DAO.
 * É nesta camada que se encontra TODA a Regra de Negócio, deixando o DAO apenas com as transações no BD
 */
package br.com.rps.facade;

import br.com.rps.model.User;
import javax.ejb.Local;

/**
 *
 * @author julian.fernando
 */
@Local
public interface UserFacade {
    public User findUserByEmail(String email);
}
