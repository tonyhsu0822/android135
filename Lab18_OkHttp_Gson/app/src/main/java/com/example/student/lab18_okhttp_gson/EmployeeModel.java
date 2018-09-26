package com.example.student.lab18_okhttp_gson;

public class EmployeeModel {

    private Employee[] employees;

    public Employee[] getEmployees() {
        return employees;
    }

    public static class Employee {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "First Name: " + firstName + ", " +
                    "Last Name: " + lastName + "}";
        }
    }
}
