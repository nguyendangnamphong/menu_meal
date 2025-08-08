# UserService
**Tổng Quan**       
Dịch Vụ Người Dùng là một microservice chịu trách nhiệm quản lý thông tin người dùng, bao gồm tên, chức vụ và danh sách các món ăn không thích. Dịch vụ này cung cấp dữ liệu quan trọng cho các dịch vụ khác trong hệ thống, đảm bảo tích hợp mượt mà để lập kế hoạch bữa ăn cá nhân hóa và quản lý món ăn.
                             
**Mục Đích**             
                                    
+ Quản Lý Người Dùng: Quản lý thông tin người dùng như tên, chức vụ và danh sách ID món ăn không thích (lưu dưới dạng chuỗi phân tách bằng dấu phẩy, tối đa 5 ID, có thể null).
+ Tích Hợp với Dịch Vụ Bữa Ăn: Cung cấp sở thích người dùng (danh sách món ăn không thích) cho Dịch Vụ Bữa Ăn để đưa ra gợi ý bữa ăn phù hợp.
+ Hỗ Trợ Dịch Vụ Món Ăn: Cung cấp thông tin ID và chức vụ của người dùng cho Dịch Vụ Món Ăn, chuẩn bị sẵn cho việc phân quyền trong tương lai (hiện tại chưa áp dụng phân quyền).
                                        
**Tính Năng**

Các Endpoint RESTful:                              
+ POST /users: Tạo người dùng mới với ID duy nhất (100-999), tên và chức vụ.                          
+ PUT /users/{id}/disliked-dishes: Cập nhật danh sách ID món ăn không thích của người dùng.                       
+ DELETE /users/{id}/disliked-dishes: Xóa danh sách ID món ăn không thích của người dùng.                                   
+ GET /users/{id}/disliked-dishes: Lấy danh sách ID món ăn không thích của người dùng.                                 
+ GET /users/{id}/role: Lấy ID và chức vụ của người dùng cho Dịch Vụ Món Ăn.                                    



**Chi Tiết Kỹ Thuật**

+ Khung Công Tác: Được xây dựng bằng Quarkus, sử dụng Panache ORM cho thao tác cơ sở dữ liệu và RESTEasy cho các endpoint RESTful.                      
+ Cơ Sở Dữ Liệu: Sử dụng PostgreSQL để lưu trữ dữ liệu người dùng, với lược đồ được khởi tạo qua schema.sql.                                 
+ Sinh ID: Tạo ID người dùng duy nhất trong khoảng 100-999 bằng lớp IdGenerator, đảm bảo không trùng lặp thông qua kiểm tra cơ sở dữ liệu.               
+ Xác Thực Đầu Vào: Xác thực đầu vào (ví dụ: tên và chức vụ không rỗng, ID món ăn hợp lệ trong khoảng 100-999) bằng InputValidator.                                     
+ Ghi Log: Sử dụng java.util.logging để ghi log lỗi chi tiết, bao gồm tên phương thức và lớp để dễ theo dõi.                              
+ Triển Khai: Được đóng gói thành container Docker, điều phối bằng docker-compose cùng với container PostgreSQL.                          

**Cấu Trúc Dự Án**
 
+ User.java: Lớp thực thể đại diện cho người dùng với các trường id (Integer), name, role và dislikedDishes (String).                              
+ IdGenerator.java: Tạo ID người dùng duy nhất trong khoảng 100-999.                           
+ UserRepository.java: Xử lý các thao tác CRUD cho thực thể User bằng Panache ORM.                  
+ InputValidator.java: Xác thực dữ liệu đầu vào cho tất cả endpoint.                                  
+ CreateUserService.java: Logic nghiệp vụ để tạo người dùng.                                   
+ UpdateDislikedDishesService.java: Logic nghiệp vụ để cập nhật danh sách món ăn không thích.                 
+ ClearDislikedDishesService.java: Logic nghiệp vụ để xóa danh sách món ăn không thích.                         
+ GetDislikedDishesService.java: Logic nghiệp vụ để lấy danh sách món ăn không thích.                             
+ GetUserRoleService.java: Logic nghiệp vụ để lấy chức vụ người dùng.                                  
+ CreateUserResource.java: Endpoint REST để tạo người dùng.                                     
+ UpdateDislikedDishesResource.java: Endpoint REST để cập nhật danh sách món ăn không thích.                                          
+ ClearDislikedDishesResource.java: Endpoint REST để xóa danh sách món ăn không thích.                                     
+ GetDislikedDishesResource.java: Endpoint REST để lấy danh sách món ăn không thích.                                    
+ GetUserRoleResource.java: Endpoint REST để lấy chức vụ người dùng.                                           



**Cài Đặt và Chạy**

*Yêu Cầu:*

Java 21 (ví dụ: jdk-21.0.1+12)                                      
Maven 3.9.x                                
Docker và Docker Compose                            


*Build Dự Án:*                       
cd D:\userService                               
set JAVA_HOME=D:\userService\jdk-21.0.1+12                                  
mvn clean package -U                                        


*Build Image Docker:*                                    
docker build -t menu_meal/userservice:latest -f src\main\docker\Dockerfile .                       


*Chạy với Docker Compose:*                    
docker-compose -f src\main\docker\docker-compose.yml up -d                           

