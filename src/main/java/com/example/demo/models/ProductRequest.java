package com.example.demo.models;

public class ProductRequest 
{

    private ProductsPage productsPage;

    public ProductsPage getProductsPage() {
        return productsPage;
    }

    public void setProductsPage(ProductsPage productsPage) {
        this.productsPage = productsPage;
    }

    public class ProductsPage 
    {
        private String total;
        private Products[] products;
        
        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public Products[] getProducts() {
            return products;
        }

        public void setProducts(Products[] products) {
            this.products = products;
        }

        public class Products 
        {
            private DefaultImage defaultImage;
            public String id;
            private String name;
            public String quantity;
            public Prices prices;


            public Prices getPrices() {
                return prices;
            }

            public void setPrices(Prices prices) {
                this.prices = prices;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public DefaultImage getDefaultImage() {
                return defaultImage;
            }

            public void setDefaultImage(DefaultImage defaultImage) {
                this.defaultImage = defaultImage;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public class DefaultImage
            {
                private String url = "tree-736885__480.jpg";

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
                
            public class Prices
            {
                public String listPrice;
                public String pricebookEntryId;
                public String unitPrice;
                
                public String getPricebookEntryId() {
                    return pricebookEntryId;
                }
                public void setPricebookEntryId(String pricebookEntryId) {
                    this.pricebookEntryId = pricebookEntryId;
                }
                public String getListPrice() {
                    return listPrice;
                }
                public void setListPrice(String listPrice) {
                    this.listPrice = listPrice;
                }
                
                public String getUnitPrice() {
                    return unitPrice;
                }
                public void setUnitPrice(String unitPrice) {
                    this.unitPrice = unitPrice;
                }
                
            }    
        }
    }
} 
    


