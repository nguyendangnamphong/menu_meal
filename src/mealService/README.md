# Meal Service                                                                           
**Yêu cầu**                        

*Database:*                                               

+ Loại: PostgreSQL
+ Phiên bản: 16
+ Triển khai: Chạy trong container Docker, được quản lý bởi docker-compose.yml                                           


*Môi trường:*                                          

+ Java: 21
+ Framework: Quarkus 3.6.3                                            
                                                         


**Cấu trúc dự án**                                                   
+ MealProposeResource.java: Định nghĩa endpoint REST POST /meals/propose.
+ MealProposeAggregator.java: Tổng hợp kết quả để tạo thực đơn đề xuất.
+ MealProposeUserFetcher.java: Tương tác với User Service để lấy danh sách món không thích.
+ MealProposeDishFetcher.java: Tương tác với Dish Service để lấy danh sách món ăn.
+ MealProposeDataFetcher.java: Lấy dữ liệu lịch sử từ database.
+ MealProposeFilter.java: Lọc danh sách món ăn.
+ MealSaveResource.java: Định nghĩa endpoint REST POST /meals.
+ MealSaveExecutor.java: Thực hiện lưu thực đơn và xóa dữ liệu tạm.
+ MealDatabaseReader.java: Thực hiện các truy vấn đọc dữ liệu từ database.
+ MealDatabaseSaver.java: Thực hiện các thao tác lưu dữ liệu vào database.
+ MealDatabaseCleaner.java: Thực hiện các thao tác xóa dữ liệu từ database.
+ MealCronJob.java: Quản lý cron job xóa dữ liệu trong meal sau 7 ngày.
+ MealLogger.java: Ghi log lỗi timeout.
