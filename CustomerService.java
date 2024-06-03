package com.assignment.rest_demo.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.assignment.rest_demo.entity.Customer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;

@Service


public class CustomerService {
    @Autowired
    CustomerCreateResponse customerCreateResponse;

    @Autowired
    CustomerListResponse customerListResponse;

    public CustomerCreateResponse createCustomer(Customer customer) throws InterruptedException {
        Firestore fireStore = FirestoreClient.getFirestore();

        DocumentReference docReference = fireStore.collection("customer").document();

        customer.setId(docReference.getId());
        ApiFuture<WriteResult> apiFuture = docReference.set(customer);

        customerCreateResponse.setUpdatedTime(apiFuture.get().getUpdatedTime().toDate());
        customerCreateResponse.setId(customer.getId());

        return customerCreateResponse;
    }
    public CustomerListResonse getCustomerList() throws InterruptedException, ExecutionException{
        Firestore firestore = com.google.cloud.firestore.v1.FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("customer").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        List<Customer> customerList = list.stream().map((doc)->doc.toObject(Customer.class));  

        customerListResponse.setList(customerList);

        return customerListResponse;


     }

     public CustomerCreateResponse update Customer(Customer customer)
    {
        Firestore firestore= FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("customer").document(customer.getId());
        ApiFuture<WriteResult> apiFuture = documentReference.set(customer);

        customerCreateResponse.setUpdatedTime(apiFuture.get().getUpdatedTime().toDate());
        customerCreateResponse.setId(customer.getId());

        return customerCreateResponse;


    }
}
