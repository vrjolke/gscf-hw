### 1 .Which SQL statement lists the buyer names in the buyer table that are not contained in the invoice table?

- a) select b.name from buyer b left join invoice i on b.id = i.buyer_id where i.buyer_id is null; 
- b) select distinct b.name from buyer b join invoice i on b.id = i.buyer_id; 
- c) select b.name from buyer b join invoice i on b.id = i.buyer_id; 
- d) select distinct b.name from buyer b left join invoice i on b.id = i.buyer_id;

Answer: a


### 2. Which SQL statement returns the department number with the maximum salary given to any employee? 
- a) select department_id, max(salary) from employees; 
- b) select max(salary) from employees group by department_id ; 
- c) select department_id, max(salary) from employees group by department_id; 
- d) select max(salary) from employees;

Answer: "c" is the closest, but it would show the max salary for each department, but not the maximum out of all the employees
If we wanted to see which department has the highest paid employee out of everyone, we would need a statement like:

`SELECT department_id FROM employees WHERE salary = (SELECT MAX(salary) FROM employees);`


### 3. What does the below query do:
````
update inv 
set inv.status_id = 
(case 
    when inv.buyer_id = 1 then 'In Progress' 
    when inv.buyer_id = 2 then 'New' 
    else 'Rejected' Choose an item.
end) 
from invoices inv;
````
- a) Updates no invoices 
- b) Updates all invoices for buyer 1 to 'In Progress', buyer 2's invoices to 'New' and any other buyer's invoices to 'Rejected' 
- c) Updates all invoices to 'Rejected' 
- d) Throws exception

Answer: "b", if 'status_id' is a string data type, otherwise "d"


### 4. Which statement below is correct to insert ‘Baker’ as the lastname in the persons table?
- a) Insert into persons ('Baker') into lastname; 
- b) Insert into persons values ('Baker'); 
- c) Insert into persons (lastname) values ('Baker'); 
- d) Insert ('Baker') into persons (lastname);

Answer: c


### 5. What is the output of the below query?
````
MS SQL: select substring('123456789', charindex('b', 'abcabcabc'), 4); 
Oracle: select substr('123456789', instr('abcabcabc', 'b'), 4) from dual;
````
- a) 6789 
- b) 2345 
- c) 1234 
- d) 456789

Answer: b


### 6. A table my_numbers has 6 number values: 1, 2, null, 1, 1, null. 
Predict the output of the below query: select count(num) from my_numbers
- a) 6 
- b) 4 
- c) 3 
- d) Throws exception because count function does not work with null value

Answer: b




