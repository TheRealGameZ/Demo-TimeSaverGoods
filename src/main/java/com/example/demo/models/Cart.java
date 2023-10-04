package com.example.demo.models;

public class Cart 
{
    private String cartId;
    private String uniqueProductCount;
    private String grandTotalAmount;
    private CartItems[] cartItems;

    public class CartItems 
    {
        private CartItem cartItem;

        public CartItem getCartItem() 
        {
            return cartItem;
        }

        public void setCartItem(CartItem cartItem) 
        {
            this.cartItem = cartItem;
        }

        public class CartItem 
        {
            private String cartItemId;
            private String name;
            private String salesPrice;
            private String quantity;
            
            public String getCartItemId() 
            {
                return cartItemId;
            }

            public void setCartItemId(String cartItemId) 
            {
                this.cartItemId = cartItemId;
            }

            public String getName() 
            {
                return name;
            }

            public void setName(String name) 
            {
                this.name = name;
            }

            public String getSalesPrice() 
            {
                return salesPrice;
            }

            public void setSalesPrice(String salesPrice) 
            {
                this.salesPrice = salesPrice;
            }

            public String getQuantity() 
            {
                return quantity;
            }

            public void setQuantity(String quantity) 
            {
                this.quantity = quantity;
            }
        }      
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUniqueProductCount() {
        return uniqueProductCount;
    }

    public void setUniqueProductCount(String uniqueProductCount) {
        this.uniqueProductCount = uniqueProductCount;
    }

    public String getGrandTotalAmount() {
        return grandTotalAmount;
    }

    public void setGrandTotalAmount(String grandTotalAmount) {
        this.grandTotalAmount = grandTotalAmount;
    }

    public CartItems[] getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItems[] cartItems) {
        this.cartItems = cartItems;
    }

}
