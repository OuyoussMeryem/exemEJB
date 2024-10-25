package org.example.services;

import org.example.Model.CD;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import java.util.List;
import javax.inject.Named;
@Named
@RequestScoped
public class CDBean {

    @EJB
    private CDServiceBean cdService;

    private Long selectedCDId;
    private String message;

    public List<CD> getAvailableCDs() {
        return cdService.listAvailableCDs();
    }

    public String borrowCD(Long userId) {
        message = cdService.borrowCD(selectedCDId, userId);  // Remplacez getUserId() par l’ID utilisateur actuel
        return "cd-list"; // Redirection vers la page de liste des CDs
    }

    public String returnCD(Long cdId) {
        message = cdService.returnCD(cdId);
        return "cd-list"; // Redirection vers la page de liste des CDs
    }

    // Getters et Setters
    public Long getSelectedCDId() { return selectedCDId; }
    public void setSelectedCDId(Long selectedCDId) { this.selectedCDId = selectedCDId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
