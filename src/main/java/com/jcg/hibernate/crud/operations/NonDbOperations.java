
 

 
package com.jcg.hibernate.crud.operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class NonDbOperations {
    
     static List<Employee> employeeList= Arrays.asList(new Employee("emp1", "password", 40,1200000,"Active","Mumbai","12344","MH","IN"),
          new Employee("emp2", "password", 42,200000,"Active","Mumbai","12344","MH","IN"),
          new Employee("emp3", "password", 40,1200000,"Active","Mumbai","12344","MH","IN"),
          new Employee("emp4", "password", 40,1200000,"Active","Mumbai","12344","MH","IN"),
          new Employee("emp5", "password", 40,1200000,"INctive","Mumbai","12344","MH","IN"));
     
     public static void findbyage(){
    employeeList.stream().filter(e -> "Active".equals(e.getStatus())).
                        filter(emp->emp.getAge()< 42).
            forEach(emp -> System.out.println(emp.getUsername()));
         
     }
     
     public static void findbyctc(){
    employeeList.stream().filter(e -> "Active".equals(e.getStatus())).
            filter(emp->emp.getCtc()> 200000).
            filter(emp->emp.getAge()< 42).
            forEach(emp -> System.out.println(emp.getUsername()));
         
     }
    
}
