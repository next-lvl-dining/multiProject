package rest;

import domain.DeliveryOrder;
import jdk.management.resource.internal.ResourceNatives;
import service.DeliveryOrderService;
import socket.OrderWebsocket;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("deliveryorders")
public class DeliveryOrderResource {
    @Inject
    private DeliveryOrderService as;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public DeliveryOrder newDeliveryOrder(DeliveryOrder a) {
        return as.create(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public DeliveryOrder getDeliveryOrderById(@PathParam("id") String id) {
        return as.find(id);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/startdelivery/{id}")
    public Response startDelivery(@PathParam("id") String  d){
        OrderWebsocket orderWebsocket = new OrderWebsocket();
        DeliveryOrder deliveryOrder = as.find(d);
        if(deliveryOrder==null){
            return Response.ok(false).build();
        }
        return Response.ok(orderWebsocket.updateOrders(deliveryOrder)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public DeliveryOrder edit(DeliveryOrder a) {
        return as.edit(a);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(DeliveryOrder a) {
        as.delete(a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all/{id}")
    public List<DeliveryOrder> getAll(@PathParam("id") String userId){
        return as.getAll(userId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all/status/{status}")
    public Response getAllWithStatus(@PathParam("status") String status) {return  Response.ok(as.getAllDeliveryOrdersByStatus(status)).build();}
}
