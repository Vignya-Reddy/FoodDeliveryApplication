package com.fooddelivery.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dao.CustomerRepository;
import com.fooddelivery.dao.OrdersRepository;
import com.fooddelivery.dao.UserRepository;
import com.fooddelivery.dto.CustomersDTO;
import com.fooddelivery.entity.Customer;
import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.Ratings;
import com.fooddelivery.entity.UserInfo;
import com.fooddelivery.exception.CustomException;
import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;
import com.fooddelivery.exception.OrdersNotFoundException;
import com.fooddelivery.exception.ReviewsNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
 
@Service
public class CustomerServiceImpl implements CustomerService {
	
	   @Autowired
	    CustomerRepository customerDao;
  
	   @Autowired
	   OrdersRepository orderDao;
	   
	   @Autowired
	   UserRepository userDao;
	   
	   
	   @Override
	   @Transactional
	    public List<Customer> showCustomers() {
	        System.out.println("Service layer Customer called");
	        return customerDao.findAll();
	    }


	   @Transactional
		@Override
	   public Customer updateCustomer(Customer customer)throws CustomException  {	
				Optional<Customer> cust= customerDao.findById(customer.getCustomerId());
				if (!cust.isPresent()) {
		            throw new CustomException("Item with ID " + customer.getCustomerId() + " not found");
		        }
				Customer existingCustomer = cust.get();
				  existingCustomer.setCustomerName(customer.getCustomerName());
				  existingCustomer.setCustomerEmail(customer.getCustomerEmail());
				  existingCustomer.setCustomerPhone(customer.getCustomerPhone());
				  return customerDao.save(existingCustomer);
			}
	   
	  

	     @Override
	     @Transactional
	      public void deleteCustomerByID(int custID) throws CustomException{
	    	 Optional<Customer> optionalCustomer = customerDao.findById(custID);
	         if (optionalCustomer.isEmpty()) {
	             throw new CustomException("Item not found");
	         } else {
	        	 customerDao.deleteById(custID);
	         }
	     }
	     
         @Override
	     @Transactional
	     public int addCustomers(Customer customer) throws CustomException {
        	 Optional<Customer> res=customerDao.findById(customer.getCustomerId());
		
		if(res.isPresent()) {			
				throw new CustomException("Customer with Id"+customer.getCustomerId()+" already Exists");
			}
		customerDao.saveAndFlush(customer);
		return customer.getCustomerId();
		}
         
         
     	@Override
    	@Transactional
    		public Customer findByCustomerId(int customerId) throws CustomException {
    			Optional<Customer> res=customerDao.findById(customerId);
    			if(!(res.isPresent())) {
    				throw new CustomException("Not Found");
    			}
    			return res.get();
        }
     	
//     	 @Override
//     	 @Transactional
//         public List<Order> getOrdersByCustomerId(int customerId) throws CustomException {
//             Customer customer = findByCustomerId(customerId);
//
//             Query query = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId");
//             query.setParameter("customerId", customerId);
//             List<Order> orders = query.getResultList();
//
//             if (orders.isEmpty()) {
//                 throw new CustomException("No orders found for the specified customer");
//             }
//
//             return orders;
//         }
     	
     	
     	
     	

     	
}


