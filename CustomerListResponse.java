package com.assignment.rest_demo.beans;

import org.springframework.stereotype.Component;

@Component
public class CustomerListResponse {

    private List<Customer> list;

    public <any> getList() {
        return list;
    }

    public void setList(<any> list) {
        this.list = list;
    }


}
