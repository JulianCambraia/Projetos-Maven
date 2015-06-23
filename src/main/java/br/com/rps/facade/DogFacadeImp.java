/**
 * A classe DogFacadeImp faz todo o trabalho de proteger nosso DAO do mundo exterior. 
 * Ela poderá ter as regras de negócio e evitar o acesso ao banco de dados caso alguma regra seja quebrada.
 */
package br.com.rps.facade;

import br.com.rps.dao.DogDAO;
import br.com.rps.model.Dog;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Classe que implementa os métodos da fachada configurando as regras de negócio
 * @author julian.fernando
 */
@Stateless
public class DogFacadeImp implements DogFacade {
    
    @EJB 
    private DogDAO dogDAO;
    
    @Override
    public void save(Dog dog) {
        isDogWithAllData(dog);
        dogDAO.save(dog);
    }

    @Override
    public Dog update(Dog dog) {
        isDogWithAllData(dog);
        return dogDAO.update(dog);
    }

    @Override
    public void delete(Dog dog) {
        dogDAO.delete(dog);
    }

    @Override
    public Dog find(long entityID) {
        return dogDAO.find(entityID);
    }

    @Override
    public List<Dog> findAll() {
        return dogDAO.findAll();
    }
    /**
     * Protege contra dados inválidos
     * @param dog 
     */
    private void isDogWithAllData(Dog dog) {
        boolean hasError = false;
        if (dog == null) {
            hasError = true;
        }
        
        if (dog.getName() == null || "".equals(dog.getName().trim())) {
            hasError = true;
        }
        
        if (dog.getWeight() <= 0) {
            hasError = true;
        }
        
        if (hasError) {
            throw new IllegalArgumentException("Não foram informados dados para Dog. verifique o campo Nome ou Tamanho foram preenchidos.");
        }
    }
}
