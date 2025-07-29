# Ứng Dụng MenuPlanner (chỉ có phần BackEnd)        

**Tổng Quan**                  
MenuPlanner là một ứng dụng desktop được thiết kế để hỗ trợ người dùng lên kế hoạch thực đơn cho các doanh nghiệp nhỏ hoặc nhóm. Ứng dụng cho phép quản lý cơ sở dữ liệu món ăn, đề xuất thực đơn dựa trên sở thích người dùng, ngân sách và số lượng người ăn, đồng thời lưu trữ thực đơn trong tối đa 7 ngày. Được xây dựng theo kiến trúc microservices và Domain Driven Design (DDD), ứng dụng đảm bảo tính mô-đun và dễ bảo trì.    
**Mục Đích**                
Dự án này được phát triển trong khuôn khổ môn học Phát triển Ứng dụng Doanh nghiệp. Nó mô phỏng một ứng dụng thực tế giúp các doanh nghiệp nhỏ quản lý thực đơn hiệu quả, đồng thời áp dụng các phương pháp kỹ thuật phần mềm hiện đại như microservices và DDD.            

**Tính Năng**        
**Tính Năng Chính**               

*Quản Lý Món Ăn:*            
Người dùng có thể thêm, chỉnh sửa hoặc xóa món ăn trong cơ sở dữ liệu món ăn.            
Tất cả người dùng đều có quyền chỉnh sửa trực tiếp cơ sở dữ liệu này.           
      

*Đề Xuất Thực Đơn:*      
Người dùng nhập số lượng người ăn, ID của họ (để lấy sở thích) và ngân sách tối đa.         
Hệ thống đề xuất thực đơn dựa trên sở thích (tránh các món không thích) và các ràng buộc khác.           


*Lưu Trữ và Chỉnh Sửa Thực Đơn:*         
Thực đơn được đề xuất sẽ lưu vào cơ sở dữ liệu bữa ăn, tối đa 7 ngày.           
Người dùng có thể chỉnh sửa thực đơn đề xuất, với yêu cầu lưu bắt buộc (dù đồng ý hay không).           


*Quản Lý Sở Thích Người Dùng:*          
Người dùng nhập sở thích (các món không thích) vào cơ sở dữ liệu người dùng để hỗ trợ đề xuất thực đơn.            



**Tính Năng Phụ (Tùy Chọn)**            

Xác Thực: Đăng ký và đăng nhập bắt buộc, sử dụng JWT để bảo mật.        
Giám Sát Tải: Theo dõi số lượng người dùng truy cập để đánh giá hiệu suất hệ thống.           
Khôi Phục Dữ Liệu: Cho phép khôi phục cơ sở dữ liệu món ăn trong trường hợp dữ liệu bị xóa hoặc sửa đổi không mong muốn.            
          
**Công Nghệ Sử Dụng**           

Backend:         
+ Quarkus: Framework Java để xây dựng API RESTful.      
+ PostgreSQL: Cơ sở dữ liệu SQL để lưu trữ món ăn, người dùng và thực đơn, chạy trên Docker.         

               
Frontend:         
Ứng dụng desktop (công nghệ sẽ được chọn sau, có thể là Electron hoặc JavaFX).        


**Công Cụ:**        
IntelliJ: IDE để phát triển backend.       
Postman: Dùng để kiểm tra API.       
Docker: Chạy PostgreSQL và hỗ trợ triển khai.        


**Kiến Trúc:**         
Microservices kết hợp Domain Driven Design (DDD) để đảm bảo tính mô-đun và khả năng mở rộng.          



**Kiến Trúc Hệ Thống**       

Microservices: Ứng dụng được chia thành các dịch vụ nhỏ, độc lập, mỗi dịch vụ xử lý một domain cụ thể (ví dụ: quản lý món ăn, quản lý người dùng, lên kế hoạch thực đơn).           
DDD: Các domain được thiết kế xoay quanh các khái niệm nghiệp vụ chính để đảm bảo tính dễ bảo trì và ranh giới rõ ràng.          
             
**Cơ Sở Dữ Liệu:**             
Cơ Sở Dữ Liệu Món Ăn: Lưu thông tin các món ăn riêng lẻ (các cột sẽ được xác định sau).          
Cơ Sở Dữ Liệu Người Dùng: Lưu thông tin người dùng, bao gồm danh sách món không thích (lưu dưới dạng ID món ăn).          
Cơ Sở Dữ Liệu Bữa Ăn: Lưu các thực đơn được đề xuất, tự động xóa dữ liệu sau 7 ngày.         
                
          

Kế Hoạch Phát Triển        

Triển Khai Tính Năng Chính:        
Phát triển và kiểm thử quản lý món ăn, đề xuất thực đơn, và quản lý sở thích người dùng.         


Tính Năng Phụ:          
Triển khai xác thực, giám sát tải và khôi phục dữ liệu.         


Phát Triển Giao Diện:        
Xây dựng giao diện desktop và tích hợp với API backend.         


Kiểm Thử:         
Kiểm tra API bằng Postman và đảm bảo tính ổn định của hệ thống.           


Triển Khai:           
Tạo một icon để khởi chạy ứng dụng desktop.         

