/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.cart;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author AD
 */
public class CartObj implements Serializable {

    private Map<String, Item> items;

    public CartObj() {

    }

    public Map<String, Item> getItems() {
        return items;
    }

    public Collection<Item> getValues() {
        if (items == null) {
            return null;
        }
        return items.values();
    }

    public void addItemToCart(String title) {
        if (this.items == null) {
            items = new HashMap<>();
        }

        int quantity = 1;
        if (this.items.containsKey(title)) {
            quantity = ((Item) this.items.get(title)).getQuantity() + 1;
        }
        this.items.put(title, new Item(title, quantity));
    }

    public void removeItemFromCart(String title) {
        if (this.items == null) {
            return;
        }

        if (this.items.containsKey(title)) {
            this.items.remove(title);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }

    }

}
