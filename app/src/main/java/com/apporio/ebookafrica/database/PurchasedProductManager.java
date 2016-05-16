package com.apporio.ebookafrica.database;

import android.content.Context;

import com.apporio.ebookafrica.logger.Logger;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by spinnosolutions on 5/16/16.
 */
public class PurchasedProductManager {



    private static Realm myRealm;
    Context con ;

    public PurchasedProductManager(Context con ){

        this.con = con  ;
        myRealm = Realm.getInstance(con);
    }




    public  void addtoPurchasedProductTable(  String name, String productid, String isbn, String image, String pages, String hours, String price, String author, String manufacturer){

        if(checkproductExsistance(productid)){
            updateexsistingRow( name,  productid,  isbn,  image,  pages,  hours,  price,  author,  manufacturer);
        }else {
            createNewRowInTable( name,  productid,  isbn,  image,  pages,  hours,  price,  author,  manufacturer);
        }
    }







    public  void updateexsistingRow (String name, String productid, String isbn, String image, String pages, String hours, String price, String author, String manufacturer){

        Purchasedproducts tobechangedelement =
                myRealm.where(Purchasedproducts.class)
                        .equalTo("productid", "" + productid)
                        .findFirst();


        myRealm.beginTransaction();
        tobechangedelement.setName(name);
        tobechangedelement.setProductid(productid);
        tobechangedelement.setIsbn(isbn);
        tobechangedelement.setImage(image);
        tobechangedelement.setPages(pages);
        tobechangedelement.setHours(hours);
        tobechangedelement.setPrice(price);
        tobechangedelement.setAuthor(author);
        tobechangedelement.setManufacturer(manufacturer);
        myRealm.commitTransaction();

        Logger.d("Row Updated");
    }



    public  void createNewRowInTable(String name, String productid, String isbn, String image, String pages, String hours, String price, String author, String manufacturer){

        myRealm.beginTransaction();

        // Create an object
        Purchasedproducts pd = myRealm.createObject(Purchasedproducts.class);

        pd.setName(name);
        pd.setProductid(productid);
        pd.setIsbn(isbn);
        pd.setImage(image);
        pd.setPages(pages);
        pd.setHours(hours);
        pd.setPrice(price);
        pd.setAuthor(author);
        pd.setManufacturer(manufacturer);
        myRealm.commitTransaction();

        Logger.d("New Row Created");
    }


    public RealmResults<Purchasedproducts> getFullTable(){
        RealmResults<Purchasedproducts> results = myRealm.where(Purchasedproducts.class).findAll();
        return  results ;
    }




    public  boolean checkproductExsistance(String product_id){

        boolean value  = false;


        RealmResults<Purchasedproducts> results1 =
                myRealm.where(Purchasedproducts.class)
                        .equalTo("productid", ""+product_id)
                        .findAll();

        if(results1.size() >0){
            value = true ;
        }else {
            value = false ;
        }
        return  value ;
    }



    public RealmResults<Purchasedproducts> getEventOnselectedDate(String product_id){
        return    myRealm.where(Purchasedproducts.class).equalTo("productid", ""+product_id).findAll();
    }




}
