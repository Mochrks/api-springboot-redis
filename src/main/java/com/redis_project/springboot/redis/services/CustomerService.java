package com.redis_project.springboot.redis.services;

import com.redis_project.springboot.redis.dto.CustomerDTO;
import com.redis_project.springboot.redis.exception.CustomerNotFoundException;
import com.redis_project.springboot.redis.model.Customer;
import com.redis_project.springboot.redis.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private static final String REDIS_PREFIX = "customer:";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // eksekusi redis --> db postgreedb
    // private ScheduledExecutorService scheduler =
    // Executors.newScheduledThreadPool(1);

    // public CustomerDTO saveCustomer(CustomerDTO addCustomerDTO) {
    // final LocalDateTime now = LocalDateTime.now();
    // logger.info("Saving customer to Redis: {}", addCustomerDTO);

    // Customer customer = Customer.builder()
    // .id(28)
    // .name(addCustomerDTO.getName())
    // .email(addCustomerDTO.getEmail())
    // .phone(addCustomerDTO.getPhone())
    // .address(addCustomerDTO.getAddress())
    // .createdAt(now)
    // .updatedAt(now)
    // .build();

    // CustomerDTO savedCustomerDTO = toDTO(customer);
    // redisTemplate.opsForValue().set(REDIS_PREFIX + savedCustomerDTO.getId(),
    // savedCustomerDTO);

    // // set data scheduller 10 detik dari redis akan otomatis ke simpan di
    // postgreedb
    // scheduler.schedule(() -> {
    // logger.info("Saving customer from Redis to database: {}", savedCustomerDTO);
    // customerRepository.save(customer);
    // redisTemplate.delete(REDIS_PREFIX + savedCustomerDTO.getId());
    // }, 10, TimeUnit.SECONDS);

    // return savedCustomerDTO;
    // }

    public List<CustomerDTO> getAllCustomers() {
        logger.info("Fetching all customers from database");
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CustomerDTO saveCustomer(CustomerDTO AddcustomerDTO) {
        final LocalDateTime now = LocalDateTime.now();
        logger.info("Saving customer: {}", AddcustomerDTO);
        Customer customer = Customer.builder()
                .name(AddcustomerDTO.getName())
                .email(AddcustomerDTO.getEmail())
                .phone(AddcustomerDTO.getPhone())
                .address(AddcustomerDTO.getAddress())
                .createdAt(now)
                .updatedAt(now)
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Customer saved and cached: {}", savedCustomer);
        CustomerDTO savedCustomerDTO = toDTO(savedCustomer);
        redisTemplate.opsForValue().set(REDIS_PREFIX + savedCustomerDTO.getId(), savedCustomerDTO);
        return savedCustomerDTO;
    }

    public CustomerDTO updateCustomer(Integer id, CustomerDTO updateCust) {
        final LocalDateTime now = LocalDateTime.now();
        logger.info("Updating customer with id: {}", id);
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        existingCustomer.setName(updateCust.getName());
        existingCustomer.setEmail(updateCust.getEmail());
        existingCustomer.setPhone(updateCust.getPhone());
        existingCustomer.setAddress(updateCust.getAddress());
        existingCustomer.setUpdatedAt(now);

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        logger.info("Customer updated and cached: {}", updatedCustomer);
        CustomerDTO updatedCustomerDTO = toDTO(updatedCustomer);
        redisTemplate.opsForValue().set(REDIS_PREFIX + updatedCustomerDTO.getId(), updatedCustomerDTO);
        return updatedCustomerDTO;

    }

    public CustomerDTO getCustomer(Integer id) {
        logger.info("Fetching customer with id: {}", id);
        CustomerDTO customerDTO = (CustomerDTO) redisTemplate.opsForValue().get(REDIS_PREFIX + id);
        if (customerDTO == null) {
            logger.warn("Customer not found in cache, fetching from database");
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer != null) {
                customerDTO = toDTO(customer);
                redisTemplate.opsForValue().set(REDIS_PREFIX + id, customerDTO);
                logger.info("Customer fetched from database and cached: {}", customerDTO);
            } else {
                throw new CustomerNotFoundException("Customer with ID " + id + " not found");
            }
        } else {
            logger.info("Customer found in cache: {}", customerDTO);
        }
        return customerDTO;
    }

    public void deleteCustomer(Integer id) {
        logger.info("Deleting customer with id: {}", id);
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            redisTemplate.delete(REDIS_PREFIX + id);
            logger.info("Customer deleted and removed from cache with id: {}", id);
        } else {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
    }

    private CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .build();
    }

}