package com.assignment.rest_demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.assignment.rest_demo.beans.customerCreateResponse;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;


@RestController
@RequestMapping("/customer")

public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    @RequestMapping("/")
    public customerCreateResponse createCustomer(@RequestBody Customer customer) throws InterruptedException
    {
        return customerService.createCustomer(customer);

    }

    @GetMapping("/")
    public CustomerListResponse getAllCustomerList() throws InterruptedException, ExecutionException
    {
        return customerService.getCustomerList();
    }


    @GetMapping("/search")
    public CustomerListResponse getCustomerList() throws InterruptedException, ExecutionException
    {
        Firestore firestore= FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("customer").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        List<Customer> customer.list = list.stream().map((doc) ->doc.toObject(Customer.class)).collect(Collectors.toList());


    }

    public customerCreateResponse updateCustomer(@RequestBody Customer customer)
    {
        customerService.updateCustomer(customer);
    }

    



}
