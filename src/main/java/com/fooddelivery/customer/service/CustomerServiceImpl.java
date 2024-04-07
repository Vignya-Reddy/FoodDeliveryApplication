package com.fooddelivery.customer.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fooddelivery.customer.dao.CustomerRepository;
import com.fooddelivery.exception.CustomerNotFoundException;
import com.fooddelivery.exception.DuplicateCustomerIDException;
import com.fooddelivery.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
 
@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	   @PersistenceContext
	    private EntityManager entityManager;
	   
	   
	   @Autowired
	    CustomerRepository customerDao;

	   
	   // @Autowired
	    //OrdersRepository OrdersDao;
	   
	   @Override
	   @Transactional
	    public List<Customer> showCustomers() {
	        // TODO Auto-generated method stub
	        System.out.println("Service layer Customer called");
	        return customerDao.findAll();
	    }


	   @Transactional
		@Override
	   public Customer updateCustomer(Customer customer)throws CustomerNotFoundException  {	
				Optional<Customer> cust= customerDao.findById(customer.getCustomerId());//Enity is in persistence state
				if (!cust.isPresent()) {
		            throw new CustomerNotFoundException("Item with ID " + customer.getCustomerId() + " not found");
		        }
				//Do not change the customer id using setter method
			 //automatically update data in table
				  Customer existingCustomer = cust.get();
				  existingCustomer.setCustomerName(customer.getCustomerName());
				  existingCustomer.setCustomerEmail(customer.getCustomerEmail());
				  existingCustomer.setCustomerPhone(customer.getCustomerPhone());
				  return customerDao.save(existingCustomer);
			}
	   
	  

	     @Override
	     @Transactional
	      public void deleteCustomerByID(int custID) throws CustomerNotFoundException{
		// TODO Auto-generated method stub
	    	 Optional<Customer> Customer = customerDao.findById(custID);
	         if (Customer.isEmpty()) {
	             throw new CustomerNotFoundException("Item not found");
	         } else {
	        	 customerDao.deleteById(custID);
	         }
	     }
	     
	    
	     
	     
         @Override
	     @Transactional
	     public int addCustomers(Customer customer) throws DuplicateCustomerIDException {
        	 Optional<Customer> res=customerDao.findById(customer.getCustomerId());
		
		if(res.isPresent()) {			
				throw new DuplicateCustomerIDException("Customer with Id"+customer.getCustomerId()+" already Exists");
			}
		customerDao.saveAndFlush(customer);
		return customer.getCustomerId();
		}
         
         
     	@Override
    	@Transactional
    		public Customer findById(int customerId) throws CustomerNotFoundException {
    			Optional<Customer> res=customerDao.findById(customerId);
    			if(!(res.isPresent())) {
    				throw new CustomerNotFoundException("Not Found");
    			}
    			return res.get();
        }
}

/*
		@Override
		public List<Customer> getOrdersByCustomerId(int customerId) {
			// TODO Auto-generated method stub
			return customerDao.findByCustomerCustomerId(customerId);
		}


		@Override
		public List<String> getAllReviewsForCustomer(int customerId) throws CustomerNotFoundException {
			// TODO Auto-generated method stub
			 TypedQuery<String> query = entityManager.createQuery(
		                "SELECT r.review FROM Ratings r WHERE r.customer.customerId = :customerId", String.class);
		        query.setParameter("customerId", customerId);
		        List<String> reviews = query.getResultList();
		        if (reviews.isEmpty()) {
		            throw new CustomerNotFoundException("No reviews found for the customer ID: " + customerId);
		        }
		        return reviews;
		}
		
	

	}*/
