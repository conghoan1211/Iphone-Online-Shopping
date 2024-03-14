/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.CartDAO;
import entity.product.Category;
import entity.product.ColorProduct;
import entity.Item;
import entity.product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Cart {

    private List<Item> listItems;

    public Cart() {
        listItems = new ArrayList<>();
    }

    public Cart(List<Item> listItems) {
        this.listItems = listItems;
    }

    public List<Item> getItems() {
        return listItems;
    }

    public void setItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    /**
     * Get item product in the cart by product id
     *
     * @param pid
     * @return
     */
    public Item getItemByPId(int pid) {
        for (Item i : listItems) {
            if (i.getProduct().getId() == pid) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityByPId(int pid) {
        Item item = getItemByPId(pid);
        return (item != null) ? item.getQuantity() : 0;
    }

    /**
     * Adds a new item product to the list of items.
     *
     * @param newItem
     * @return
     */
    public boolean addItemIntoCart(Item newItem) {
        Item currentItem = this.getItemByPId(newItem.getProduct().getId());
        if (currentItem != null) {
            currentItem.setQuantity(currentItem.getQuantity() + newItem.getQuantity());
        } else {
            listItems.add(newItem);
            return true;
        }
        return false;
    }

    /**
     * Return true if delete success, otherwise if delete failed or item is null
     *
     * @param pid
     * @return
     */
    public boolean deleteItem(int pid) {
        Item item = getItemByPId(pid);
        return (item != null) && listItems.remove(item);
    }

    public int getTotalMoney() {
        int total = 0;
        for (Item i : listItems) {
            total += i.getQuantity() * i.getProduct().getCurrentPrice();
        }
        return total;
    }

    public int getAmountItems() {
        int amountItem;
        if (listItems != null) {
            amountItem = listItems.size();
        } else {
            amountItem = 0;
        }
        return amountItem;
    }

    public Product getProductById(int pid, List<Product> listProduct) {
        for (Product p : listProduct) {
            if (p.getId() == pid) {
                return p;
            }
        }
        return null;
    }

    public void initializeCartFromText(String txt, List<Product> listProduct) {
        CartDAO cdao = new CartDAO();
        if (txt != null) {
            String[] prod = txt.split("-");
            for (String p : prod) {
                String[] ent = p.split(":");
                try {
                    int id = Integer.parseInt(ent[0]);
                    int quantity = Integer.parseInt(ent[1]);
                    int c_id = Integer.parseInt(ent[2]);
                    Product product = getProductById(id, listProduct);
                    if (product != null) {
                        ColorProduct cp = cdao.getColorById(c_id);
                        Item item = new Item(product, quantity, cp);
                        this.addItemIntoCart(item);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Product> listProduct = new ArrayList<>();
        ColorProduct cd = new ColorProduct();
        
         CartDAO cdao = new CartDAO();
        ColorProduct cp = cdao.getColorById(1);
        
        Category c = new Category(0, "c");
        Product p = new Product(0, "i", "t", 340, 1000000, 233123240, "a", "a", c, "d", 0);
        Item i = new Item(p, 1, cp);

        Product p2 = new Product(1, "i", "t", 0, 4000000, 0, "a", "a", c, "d", 0);
        Product p1 = new Product(3, "i", "t", 0, 2000000, 0, "a", "a", c, "d", 0);
        Item i1 = new Item(p1, 1, cp);

        listProduct.add(p);
        listProduct.add(p1);
        listProduct.add(p2);

        Cart cart = new Cart();

        cart.initializeCartFromText("1:1:1", listProduct);
//        cart.initializeCartFromText("0:2:1-1:1:3", listProduct);
       

//        Item i2 = new Item(p, 1, cp);
        cart.addItemIntoCart(i1);
                cart.deleteItem(1);


//        boolean a = cart.addItemIntoCart(i);
//        if (a) {
//            System.out.println("ok");
//        } else {
//            System.out.println("none");
//        }
        List<Item> listC = cart.getItems();
        for (Item item : listC) {
            System.out.println(item);
        }
//        double o = cart.getTotalMoney();
//        int cc = cart.getItems().get(0).getColor();
//        System.out.println(cc);
    }
}
