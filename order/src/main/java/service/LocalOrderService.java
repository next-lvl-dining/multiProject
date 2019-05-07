package service;

import dao.interfaces.LocalOrderDao;
import domain.DeliveryOrder;
import domain.LocalOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LocalOrderService {
    @Inject
    private LocalOrderDao ld;

    public void create(LocalOrder a){
        ld.create(a);
    }

    public LocalOrder find(String id){
        return ld.find(id);
    }

    public void edit(LocalOrder a){
        ld.edit(a);
    }

    public void delete(LocalOrder a){
        ld.delete(a);
    }

    public List<LocalOrder> getAll(String userId){
        return ld.getAll(userId);
    }
}
