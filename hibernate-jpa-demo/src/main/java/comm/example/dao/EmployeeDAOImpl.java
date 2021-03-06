package comm.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import comm.example.factory.EmployeeFactory;
import comm.example.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	private EmployeeFactory employeeFactory;
	private EntityManager entityManager;
	public EmployeeDAOImpl() {
		employeeFactory=new EmployeeFactory();
		entityManager=employeeFactory.getMyEntityManager();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		TypedQuery<Employee> query=entityManager.createQuery("From Employee",Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee raiseSalary(int id, double salary) {
		// TODO Auto-generated method stub
		Employee employee=entityManager.find(Employee.class, id);
		if(employee!=null)
		{
			entityManager.getTransaction().begin();
			employee.setSalary(employee.getSalary()+salary);
			//entity manager merge method is replacing update methode.
			entityManager.merge(employee);
			entityManager.getTransaction().commit();
		}
		return employee;
	}

	@Override
	public void deleteEmployee(int id) {

		//find by id
		// if the entity is found we shall remove
		// else print a message saying entity not found
		Employee employee=entityManager.find(Employee.class, id);
		if(employee==null)
		{
			
			System.out.println("no such entity found with id: "+id);
			System.exit(0);
		}
		else
		{
			entityManager.getTransaction().begin();
			entityManager.remove(employee);
			entityManager.getTransaction().commit();
			System.out.println("employee removed sucessfully");
		}
		
		
	}

}
