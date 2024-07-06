package com.redis_project.springboot.redis.controller;

import com.redis_project.springboot.redis.dto.CustomerDTO;
import com.redis_project.springboot.redis.utils.ResponseUtil;
import com.redis_project.springboot.redis.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCustomers() {
        logger.info("Getting all customers");
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseUtil.getAllDataSuccess(customers);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("Creating customer: {}", customerDTO);
        CustomerDTO createdCustomer = customerService.saveCustomer(customerDTO);
        return ResponseUtil.createSuccess(createdCustomer);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable Integer id,
            @RequestBody CustomerDTO customerDTO) {
        logger.info("Update customer with id: {}", id);
        CustomerDTO updateCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseUtil.createSuccess(updateCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCustomer(@PathVariable Integer id) {
        logger.info("Getting customer with id: {}", id);
        CustomerDTO customer = customerService.getCustomer(id);
        return ResponseUtil.getDataByIdSuccess(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable Integer id) {
        logger.info("Deleting customer with id: {}", id);
        customerService.deleteCustomer(id);
        return ResponseUtil.deleteSuccess();
    }

}