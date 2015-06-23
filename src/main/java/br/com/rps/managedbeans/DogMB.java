package br.com.rps.managedbeans;

import br.com.rps.facade.DogFacade;
import br.com.rps.model.Dog;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author julian.fernando
 */
@RequestScoped
@ManagedBean
public class DogMB {
    
    @EJB
    private DogFacade dogFacade;
    
    private static final String CREATE_DOG = "createDog";
    private static final String DELETE_DOG = "deleteDog";
    private static final String UPDATE_DOG = "updateDog";
    private static final String LIST_ALL_DOGS = "listAllDogs";
    private static final String STAY_IN_SAME_PAGE = null;
    
    private Dog dog; 

    public Dog getDog() {
        if (dog == null) {
            this.dog = new Dog();
        }
        return this.dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
    
    public List<Dog> getAllDogs() {
        return this.dogFacade.findAll();
    }
    
    public String updateDogStart() {
        return UPDATE_DOG;
    }
    
    public String updateDogEnd() {
        try {
            this.dogFacade.update(dog);
        } catch (EJBException e) {
            sendErrorMessageToUser("Error. Verifique se o tamanho está acima de zero ou se você está logado como administrador.");
            return STAY_IN_SAME_PAGE;
        }
        sendInfoMessageToUser("Operação de atualização completa");
        
        return LIST_ALL_DOGS;
    }

    public String deleteDogStart() {
        return DELETE_DOG;
    }
    
    public String deleteDogEnd() {
        try {
            this.dogFacade.delete(dog);
        } catch (EJBException e) {
            sendErrorMessageToUser("Error. Verifique com o Administrador.");
            
            return STAY_IN_SAME_PAGE;
        }
        
        sendInfoMessageToUser("Operação de exclusão completada.");
        
        return LIST_ALL_DOGS;
    }
    
    public String createDogStart() {
        return CREATE_DOG;
    }
    
    public String createDogEnd() {
        try {
            this.dogFacade.save(dog);
        } catch (EJBException e) {
            sendErrorMessageToUser("Error. Verifique se o tamanho está acima de zero ou contacte o Administrador.");
            return STAY_IN_SAME_PAGE;
        }
        
        sendInfoMessageToUser("Operação de inserção completada.");
        
        return LIST_ALL_DOGS;
    }
    
    public String listAllDogs() {
        return LIST_ALL_DOGS;
    }
    
    private void sendErrorMessageToUser(String message) {
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    private void sendInfoMessageToUser(String message) {
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    private FacesContext getContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }
}
