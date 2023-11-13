package com.onxshield.invoiceyou.invoicestatement.service;

public class later {


//    private ArrayList<merchandiseGenerationResponse> invoiceIdGenerator(merchandiseGenerationRequest requestDetails) {
//
//        ArrayList<merchandiseGenerationResponse> generatedMerch = new ArrayList<>();
//
//
//        // to get a random index for the lists used below
//        Random random = new Random();
//        //check the price level
//
//        int generatedCount = 0;
//        double targetedTotal = 0;
//        //categorizing products by price range;
//        // ranges of invoices
//        //        // 1,000DH to 5,000DH
//        //        // 5,000DH to 20,000DH
//        //        // 20,000DH to +100,000DH
//        // ranges of product prices pL(price level)
//        // 0DH to 500DH
//        // 500DH to 1,000DH
//        // 1,000DH to 10,000DH
//        double p1 = 0,p2 = 0,p3 = 0;
//        // we should categorize the products based on: price range + categories in inputs
//        // getting only products in the wanted category
//        List<inventory> allProducts = inventoryRepository.findAll().stream().filter(
//                inventory ->  inventory.getProduct().getCategoryList().contains(requestDetails.getCategory()
//                )).toList();
//
//        List<inventory> pL1 = allProducts.stream()
//                .filter(inventory -> inventory.getSellPrice() <= p1 )
//                .toList();
//        List<inventory> pL2 = allProducts.stream()
//                .filter(inventory -> inventory.getSellPrice() >= p1 & inventory.getSellPrice() <= p2)
//                .toList();
//        List<inventory> pL3 = allProducts.stream()
//                .filter(inventory -> inventory.getSellPrice() <= p3 & inventory.getSellPrice() >= p1)
//                .toList();
//
//
//
//        if(requestDetails.getTotalTTC()<=5000){ //use pL1
//
//            while(targetedTotal<=requestDetails.getTotalTTC()+0.5 & targetedTotal>=requestDetails.getTotalTTC()+1.2){
//
//
//
//            }
//        } else if (requestDetails.getTotalTTC()>=5000 & requestDetails.getTotalTTC()<=20000) { //use pL2
//
//        }else { //use pL3
//
//        }
//
//        return generatedMerch;
//    }
}
