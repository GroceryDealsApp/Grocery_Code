package com.example.cosc341_finalproject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class storeCart {
    private double totalcost;
    private double totalvalue;
    private double totalweight;
    private String store;
    private NumberFormat currFormat;
    private String weightUnit;
    private List<Product> cartItems;
    private boolean cartAbleToBeFilled;

    public storeCart(String store) {
        this.totalcost = 0;
        this.totalvalue = 0;
        this.store = store;
        this.currFormat = NumberFormat.getCurrencyInstance();
        this.weightUnit =  "kg";
        this.cartItems =  new ArrayList<>();
        this.cartAbleToBeFilled = false;
    }

    //useful methods
    public boolean isCartAbleToBeFilled() {
        return cartAbleToBeFilled;
    }
    public String getTotalvalueFormatted() { //use these for your strings
        return currFormat.format(getTotalvalue()) + "/100g";
    }
    public String getTotalcostFormatted() {
        return currFormat.format(this.getTotalcost());
    }
    public void createCart(){ //use after a user has created a cart to create carts of real items for each store
        cartItems = new ArrayList<>();
        cartAbleToBeFilled = true;
        //first create an arraylist of all the products in the user's cart (can probably be removed/changed if newcart_items element's become Product or something instead of String)
        //(you can also use this code whenever you want to get a Product list of every usercart item)
        List<Product> userCartProds = new ArrayList<>();
        for (String cartItemName : Global.newcart_items){
            int allprodsSize = Global.products.size();
            for (int i = 0; i < allprodsSize; i++) {
                Product p = Global.products.get(i);
                if (cartItemName.toLowerCase().equals(p.getFullName().toLowerCase())) {
                    userCartProds.add(p);
                }
            }
        }
        //now, for every usercart item,
        int allCartItemsSize = userCartProds.size();
        for (int i = 0; i < allCartItemsSize; i++) {
            //find all items from the store that has the same base item
            Product userCartProduct = userCartProds.get(i);
            List<Product> itemsWithSameBaseInStore = new ArrayList<>();
            int allprodsSize = Global.products.size();
            for (int j = 0; j < allprodsSize; j++) {
                Product prodWithSameBase = Global.products.get(j);
                if (prodWithSameBase.getBaseItemName().equals(userCartProduct.getBaseItemName())) {
                    itemsWithSameBaseInStore.add(prodWithSameBase);
                }
            }
            //if the store has none of the required base item, show error
            if (itemsWithSameBaseInStore.size() == 0) {
                cartAbleToBeFilled = false;
                return;
            }
            //check to see if the store carries a base item of better value
            Product bestValProduct = itemsWithSameBaseInStore.get(0);
            int allprodsSameBaseSize = itemsWithSameBaseInStore.size();
            for (int j = 0; j < allprodsSameBaseSize; j++) {
                Product potentiallyBetterProd = itemsWithSameBaseInStore.get(j);
                if (bestValProduct.getValue() < potentiallyBetterProd.getValue())
                    bestValProduct = potentiallyBetterProd;
            }
            //add the bestvalue product to cartItems
            cartItems.add(bestValProduct);
        }
    }

    //other methods
    private void updateTotalCost() {
        totalcost = 0;
        for (Product item : cartItems) {
            totalcost += item.getPrice();
        }
    }
    private void updateTotalWeight() {
        totalweight = 0;
        for (Product item : cartItems) {
            totalweight += item.getWeight();
        }
    }
    public void updateTotalValue() {
        updateTotalCost();
        updateTotalWeight();
        totalvalue = (totalcost/totalweight)*100; // $ per 100g i think
    }



//getters and setters
    public double getTotalcost() { //use these for store comparisons
         return totalcost;
    }
    public double getTotalvalue() {
        updateTotalValue();
        return totalvalue;
    }



    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }


    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }


}
