package org.example.dao;

import org.example.Model.CD;

import java.util.ArrayList;
import java.util.List;

public class CDDAO {
    private List<CD> cdList;

    public CDDAO() {
        cdList = new ArrayList<>();
    }

    public void addCD(CD cd) {
        cdList.add(cd);
    }

    public void removeCD(int id) {
        cdList.removeIf(cd -> cd.getId() == id);
    }

    public void updateCD(int id, CD updatedCD) {
        for (int i = 0; i < cdList.size(); i++) {
            if (cdList.get(i).getId() == id) {
                cdList.set(i, updatedCD);
                break;
            }
        }
    }

    public List<CD> getAllCDs() {
        return cdList;
    }
}
