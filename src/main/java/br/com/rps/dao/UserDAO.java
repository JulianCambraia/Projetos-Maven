package br.com.rps.dao;

import br.com.rps.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 * Classe UserDAO que implementa métodos específicos do CRUD fazendo uso da Super Classe Genérica
 * @author julian.fernando
 */
@Stateless
public class UserDAO  extends  GenericDAO<User>{

    public UserDAO() {
        super(User.class);
    }
    /**
     * Método específico desta classe porém note que este herda da classe pai o findOneResult
     * @param email
     * @return Object User
     */
    public User findUserByEmail(String email) {
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("email", email);
        return super.findOneResult(User.FIND_BY_EMAIL, parameters);
    }
}
