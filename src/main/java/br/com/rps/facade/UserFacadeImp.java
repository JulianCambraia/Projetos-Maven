/**
 * A classe UserFacadeImp faz todo o trabalho de proteger nosso DAO do mundo exterior. 
 * Ela poderá ter as regras de negócio e evitar o acesso ao banco de dados caso alguma regra seja quebrada.
 */
package br.com.rps.facade;

import br.com.rps.dao.UserDAO;
import br.com.rps.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Classe que implementa os métodos da fachada configurando as regras de negócio
 * @author julian.fernando
 */
@Stateless
public class UserFacadeImp implements UserFacade {
    
    @EJB
    private UserDAO userDAO;
    
    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }
    
}
