package service;

import data.interfaces.DeliveryDao;
import domain.Delivery;
import domain.Route;
import service.interfaces.IDeliveryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Stateless
public class DeliveryService implements IDeliveryService {

    private DeliveryDao deliveryDao;

    @Inject
    public void setDeliveryDao(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    @Override
    public Delivery getById(String id) {
        return deliveryDao.find(id);
    }

    @Override
    public Delivery addRoute(String deliveryId, Route route) {
        Delivery delivery = deliveryDao.find(deliveryId);
        delivery.addRoute(route);
        return deliveryDao.edit(delivery);
    }

    @Override
    public Set<Route> getRoutes(String deliveryId) {
        return deliveryDao.find(deliveryId).getRoutes();
    }

    @Override
    public Delivery assignEmployee(String deliveryId, String employeeId) {
        Delivery delivery = deliveryDao.find(deliveryId);
        delivery.setEmployeeId(employeeId);
        return deliveryDao.edit(delivery);
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryDao.getAllDeliveries();
    }

    @Override
    public void removeById(String id) {
        deliveryDao.delete(deliveryDao.find(id));
    }

    @Override
    public void removeByEmployeeId(String employeeId) {
        List<Delivery> deliveries = deliveryDao.getByEmployeeId(employeeId);
        for(Delivery d : deliveries){
            deliveryDao.delete(d);
        }
    }

    @Override
    public Delivery editDelivery(String deliveryId, Set<String> orderList) {
        Delivery delivery = deliveryDao.find(deliveryId);
        delivery.setOrderIdList(orderList);
        return deliveryDao.edit(delivery);
    }

    @Override
    public List<Delivery> getByEmployeeId(String employeeId) {
        return deliveryDao.getByEmployeeId(employeeId);
    }

    @Override
    public Delivery addDelivery(Set<String> orderList) {
        Delivery delivery = new Delivery(orderList);
        return deliveryDao.create(delivery);
    }

    @Override
    public Delivery createDelivery(Delivery delivery) {
       return deliveryDao.create(delivery);
    }
}
