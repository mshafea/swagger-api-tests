package resources;

import pojo.Category;
import pojo.Order;
import pojo.Pets;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {


    public Pets addPet(String id, String name, String status) {
        Pets p = new Pets();
        p.setId(id);
        Category l = new Category();
        l.setId(12);
        l.setName("dogs");
        p.setCategory(l);
        p.setName(name);
        List<String> urls = new ArrayList<String>();
        urls.add("http://testpet.com");
        p.setPhotoUrls(urls);
        p.setStatus(status);

        return p;
    }

    public Order placeOrder(String id, String petId, String quantity) {
        Order order = new Order();
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate("2022-03-22T22:08:12.883Z");
        order.setStatus("placed");
        order.setComplete(true);

        return order;
    }

}
