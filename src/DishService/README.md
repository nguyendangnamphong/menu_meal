# Dish Service
Dish Service là một ứng dụng REST API được xây dựng bằng Quarkus và Java 23, sử dụng PostgreSQL 17 để quản lý danh sách món ăn. Dự án nằm trong ứng dụng tổng thể menuMeal và cung cấp các endpoint để tạo, cập nhật, xóa, và truy vấn món ăn, phục vụ cho Meal Service.                                
                                     

                             
**Yêu cầu**                                  

+ Java: 23
+ Maven: 3.9.9 hoặc cao hơn
+ Docker: Để chạy PostgreSQL 17 và ứng dụng Quarkus
+ Docker Compose: Để quản lý các container
                   
                       
                    
**Cấu trúc dự án**         


+ pom.xml: Cấu hình Maven với các dependency Quarkus (quarkus-core, quarkus-resteasy-reactive-jackson, quarkus-hibernate-orm-panache, quarkus-jdbc-postgresql, quarkus-hibernate-validator).
+ application.properties: Cấu hình kết nối PostgreSQL (cổng 9090, database Dish), Hibernate ORM, và logging console.
+ schema.sql: Định nghĩa bảng Dish trong PostgreSQL.
+ Dockerfile: Build image cho ứng dụng Quarkus.
+ docker-compose.yml: Chạy ứng dụng Quarkus và PostgreSQL.
+ Java files (trong src/main/java/com/example/dishservice/resource):
+ Dish.java: Entity ánh xạ bảng Dish.
+ IdGenerator.java: Sinh ID ngẫu nhiên (100-999).
+ CreateDishResource.java: Xử lý POST /dishes để tạo món ăn mới.
+ UpdateDishByIdResource.java: Xử lý PUT /dishes/{id} để cập nhật món ăn theo ID.
+ UpdateDishByNameResource.java: Xử lý POST /dishes để cập nhật món ăn theo tên.
+ DeleteDishResource.java: Xử lý DELETE /dishes/{id} để xóa món ăn.
+ GetAllDishesResource.java: Xử lý GET /dishes để lấy danh sách món ăn.
+ GetDishByIdResource.java: Xử lý GET /dishes/{id} để lấy món ăn theo ID.
+ GetDishByNameResource.java: Xử lý GET /dishes/{name} để lấy món ăn theo tên.
+ DishOperationManager.java: Quản lý logic chung cho tạo/cập nhật món ăn.
+ DishExceptionMapper.java: Ánh xạ ngoại lệ thành HTTP response.                     
                            





**Cấu trúc bảng Dish**                                      



+ id: Integer (100-999, unique, NOT NULL).
+ name: Varchar(255, unique, NOT NULL).
+ dish_type: Varchar(10, "đạm" hoặc "rau", NOT NULL).
+ time_not_repeat: Decimal(10,2, 0-7, NOT NULL).
+ price: Decimal(10,2, NOT NULL).



**API Endpoints**                  

+ POST /dishes: Tạo món ăn mới hoặc cập nhật dựa trên id hoặc name.
+ PUT /dishes/{id}: Cập nhật món ăn theo id.
+ DELETE /dishes/{id}: Xóa món ăn theo id.
+ GET /dishes: Lấy danh sách tất cả món ăn.
+ GET /dishes/{id}: Lấy món ăn theo id.
+ GET /dishes/{name}: Lấy món ăn theo name.

