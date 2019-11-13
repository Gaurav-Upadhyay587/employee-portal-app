# employee-portal-app

To run this app locally in your system follow the below process step by step:

1.  Clone the repository to the local
2.  import the project into eclipse/sts as maven project
3.  I am using MySQL DB, credentials for my local instance given below replace with your DB's credential.
      url: jdbc:mysql://localhost:3306/employee_portal
      username: root
      password: root
4.  update maven project: right click on the project root select maven and click update
5. run the application as java application
6. got to http://localhost:8080/swagger-ui.html for rest api documentation.
7. Test Using post man
  a. make get request to http://localhost:8080/employees/v1.0/  to get the list of employees
  b. make post request to http://localhost:8080/employees/v1.0/ to create/register a new empoyee . Pass employee as request body payload        will look like below:
  
      {
        "dateOfBirth": "2019-11-13",
        "department": "Support",
        "firstName": "Ankit",
        "lastName": "Sharma",
        "sex": "MALE"
      }
  Note: Emplee id is auto generated so no need to pass it.

  c. make get request to http://localhost:8080/employees/v1.0/id_value   to get the employee detail for a specific employee based on id.        replace id_value with the id of employee. 
  d. make request to http://localhost:8080/employees/v1.1/filters/firstName_asc  to get the list of employee sorted by firstname.
 
 8. Integreation test are written for repository and service. you can directly test them in eclispse just go to the test method and           Press Alt+Shift+X,T .
  
