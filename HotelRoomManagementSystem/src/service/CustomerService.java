package service;

import dao.CustomerDao;
import model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDao dao = new CustomerDao();

    public List<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }

    public int addCustomer(Customer c) {
        return dao.addCustomer(c);
    }

    public int deleteCustomer(int id) {
        return dao.deleteCustomer(id);
    }
}