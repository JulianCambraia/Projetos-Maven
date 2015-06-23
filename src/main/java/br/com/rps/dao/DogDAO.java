package br.com.rps.dao;

import br.com.rps.model.Dog;
import javax.ejb.Stateless;

/**
 * Classe DogDAO que implementa métodos específicos do CRUD fazendo uso da Super Classe Genérica
 * @author julian.fernando
 */
@Stateless
public class DogDAO extends GenericDAO<Dog>{

    public DogDAO() {
        super(Dog.class);
    }

    public void delete(Dog dog) {
        super.delete(dog.getId(), Dog.class);
    }
}