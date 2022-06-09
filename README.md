<h1>Báo cáo thực tập tuần 1 (Diệp Thanh Huy)</h1> 

![Minion](https://octodex.github.com/images/minion.png) 

**Câu 1: Cơ sở dữ liệu phân tán là gì?**

CSDL phân tán là một tập hợp dữ liệu có liên quan (ᴠề logic) được dùng chung ᴠà phân tán ᴠề mặt ᴠật lí trên một mạng máу tính.

Một hệ QTCSDL phân tán là một hệ thống phần mềm cho phép quản trị CSDL phân tán ᴠà làm cho người ѕử dụng không nhận thấу ѕự phân tán ᴠề lưu trữ dữ liệu.

Người dùng truу cập ᴠào CSDL phân tán thông quan chương trình ứng dụng. Các chương trình ứng dụng được chia làm hai loại:
* Chương trình không уêu cầu dữ liệu từ nơi khác.
* Chương trình có уêu cầu dữ liệu từ nơi khác.

Có thể chia các hệ CSDL phân tán thành 2 loại chính: thuần nhất ᴠà hỗn hợp.
* Hệ CSDL phân tán thuần nhất: các nút trên mạng đều dùng cùng một hệ QTCSDL.
* Hệ CSDL phân tán hỗn hợp: các nút trên mạng có thể dùng các hệ QTCSDL khác nhau.

***Một ѕố ưu điểm ᴠà hạn chế của các hệ CSDL phân tán***
* Sự phân tán dữ liệu ᴠà các ứng dụng có một ѕố ưu điểm ѕo ᴠới các hệ CSDL tập trung:
* So ᴠới các hệ CSDL tập trung, hệ CSDL phân tán có một ѕố hạn chế như ѕau:

Hệ thống phức tạp hơn ᴠì phải làm ẩn đi ѕự phân tán dữ liệu đối ᴠới người dùng.Chi phí cao hơn.Đảm bảo an ninh khó khăn hơn.Đảm bảo tính nhất quán dữ liệu khó hơn.Việc thiết kế CSDL phân tán phức tạp hơn.

**Câu 2: Angular là gì? Angular khác với Angular JVS những điểm nào?**
|   | Angular  | AngularJVS  | 
|---|---|---|
| Tên gọi  |  Angular là từ gọi chung cho Angular 2 trở lên |  AngularJS là từ được được dùng để nói về Angular 1 |
|  Năm ra mắt | 2016  | 2009  |
|  Ngôn ngữ | TypeScript phiên bản nâng cao của JavaScript  |JavaScript   |
|Kiến trúc|Angular sử dụng các components và directives. Components là directives có template.|AngularJS hỗ trợ thiết kế Model-View-Controller. Chế độ xem xử lý thông tin có sẵn trong mô hình để tạo ra kết quả đầu ra.|
|Routing|Angular dùng @Route Config{(…)} cho cấu hình định tuyến|AngularJS dùng $routeprovider.when() cho cấu hình định tuyến|
|Google hỗ trợ|Có|Không còn được Google hỗ trợ nâng cấp|
|Hỗ trợ mobile|Hỗ trợ tất cả các trình duyệt mobile phổ biến|Không hỗ trợ các trình duyệt trên mobile|
  
**Câu 3: Cơ sở dữ liệu quan hệ khác cơ sỡ dữ liệu phi quan hệ ở những điểm nào?**
|   |  Cơ sở dữ liệu quan hệ |Cơ sở dữ liệu NoSQL|
|---|---|--|
| Khối lượng công việc tối ưu  |Cơ sở dữ liệu quan hệ được thiết kế dành cho các ứng dụng xử lý giao dịch trực tuyến (OLTP) trong giao dịch có độ ổn định cao và thích hợp để xử lí phân tích trực tuyến (OLAP).|Các cơ sở dữ liệu NoSQL được thiết kế cho các mẫu truy cập dữ liệu, bao gồm các ứng dụng có độ trễ thấp. Cơ sở dữ liệu tìm kiếm NoSQL được thiết kế để phục vụ phân tích dữ liệu có cấu trúc chưa hoàn chỉnh. |
| Mô hình dữ liệu |Mô hình quan hệ chuẩn hóa dữ liệu vào bảng được hình thành từ hàng và cột. Sơ đồ quy định rõ ràng bảng, hàng, cột, chỉ mục, mối quan hệ giữa các bảng và các thành tố cơ sở dữ liệu khác. Cơ sở dữ liệu sẽ thực thi tính toàn vẹn tham chiếu trong mối quan hệ giữa các bảng. |Các cơ sở dữ liệu NoSQL cung cấp nhiều mô hình dữ liệu khác nhau như khóa-giá trị, tài liệu và biểu đồ, được tối ưu hóa để đạt hiệu năng và quy mô tối ưu. |
|Thuộc tính ACID|Cơ sở dữ liệu quan hệ có các thuộc tính mang tính nguyên tố, nhất quán, tách biệt và bền vững (ACID): *Tính nguyên tố đòi hỏi giao dịch phải được thực thi đầy đủ hoặc hoàn toàn không thực hiện*. *Tính nhất quán đòi hỏi rằng khi giao dịch được thực hiện, dữ liệu phải nhất quán với sơ đồ cơ sở dữ liệu*. *Tính tách biệt đòi hỏi rằng các giao dịch diễn ra đồng thời phải được thực thi tách biệt với nhau*. *Tính bền vững đòi hỏi phải có khả năng phục hồi từ tình trạng hư hỏng hệ thống hoặc mất điện đột ngột về trạng thái đã biết cuối cùng*.|Cơ sở dữ liệu NoSQL thường phải đánh đổi bằng cách nới lỏng một số thuộc tính ACID này của cơ sở dữ liệu quan hệ để có mô hình dữ liệu linh hoạt hơn có khả năng thay đổi quy mô theo chiều ngang. Việc này biến các cơ sở dữ liệu NoSQL thành lựa chọn tuyệt vời cho các trường hợp sử dụng cần thông lượng cao, độ trễ thấp cần thay đổi quy mô theo chiều ngang vượt qua giới hạn của một phiên bản duy nhất.|
|Hiệu năng|	Hiệu năng thường phụ thuộc vào hệ thống con của ổ đĩa. Thông thường, việc tối ưu hóa các truy vấn, chỉ mục và cấu trúc bảng bắt buộc phải được thực hiện để đạt mức hiệu năng tối đa.|Hiệu năng thường được xem là chức năng của kích cỡ cụm phần cứng ngầm, độ trễ mạng và ứng dụng đưa ra lệnh gọi.|
|Quy mô|Cơ sở dữ liệu quan hệ thường tăng quy mô bằng cách tăng năng lực điện toán của phần cứng hoặc tăng quy mô bằng cách thêm bản sao của khối lượng công việc chỉ đọc.|Cơ sở dữ liệu NoSQL thường có tính phân mảnh cao do các mẫu truy cập khóa-giá trị có khả năng tăng quy mô bằng cách sử dụng kiến trúc được phân phối để tăng thông lượng, đem đến hiệu năng ổn định với quy mô gần như không giới hạn.|
|API|Yêu cầu lưu trữ và truy xuất dữ liệu được truyền đạt bằng cách sử dụng các truy vấn nhất quán với ngôn ngữ truy vấn có cấu trúc (SQL). Các truy vấn này được phân tích và thực thi bởi cơ sở dữ liệu quan hệ.|API trên cơ sở đối tượng cho phép các nhà phát triển ứng dụng dễ dàng lưu trữ và truy xuất cấu trúc dữ liệu trong bộ nhớ. Khóa phân mảnh tìm kiếm các cặp khóa-giá trị, tập hợp cột hoặc văn bản có cấu trúc chưa hoàn chỉnh có chứa đối tượng và thuộc tính của ứng dụng được xếp theo chuỗi.|

**Câu 4: Microservices là gì? Sự khác nhau giữa Microservice và kiến trúc nguyên khối ?**

***Khái niệm:***
Microservices là các dịch vụ nhỏ, tách biệt đại diện cho 1 phần nhỏ tương ứng trong business domain của bạn. Với kiến trúc Monolithic, bạn sẽ có 1 server lớn chịu trách nhiệm giải quyết tất cả các requests. Việc này sẽ gây khó khăn rất nhiều trên phương diện scale.  Microservices có thể cân bằng traffic theo nhu cầu của doanh nghiệp. Nếu đang nhận được 1 lượng lớn thanh toán, bạn có thể scale up thiết bị thanh toán và giữ các dịch vụ khác ở mức sử dụng 1 lượng nhỏ hơn các services.

***Sự khác nhau giữa Microservice và kiến trúc nguyên khối:***

- Lập trình viên phải tốn nhiều công sức hơn để thực hiện phần giao tiếp giữa các microservice, với kiến trúc nguyên khối có khi họ chỉ cần gọi hàm để thực hiện việc này.
- Việc triển khai hệ thống microservice phức tạp hơn nhiều so với việc triển khai hệ thống nguyên khối.
- Cần tính toán kích cỡ của một microservice. Nếu một microservice quá lớn, bản thân nó trở thành một ứng dụng theo kiến trúc nguyên khối.

<style>
  .ok{font-size: 30px; text-align: center; font-family: Arial;}
</style>
<div class="ok">
  Đôi nét về Spring Boot
</div>

**Spring Boot** là một dự án phát triển bởi JAV (ngôn ngữ java) trong hệ sinh thái Spring framework. Nó giúp cho các lập trình viên chúng ta đơn giản hóa quá trình lập trình một ứng dụng với Spring, chỉ tập trung vào việc phát triển business cho ứng dụng.

**Khi dùng Spring là việc cấu hình (config) dự án quá phức tạp. Bạn phải làm đủ thứ việc chỉ để tạo một web HelloWorld:**
* Tạo Maven hoặc Gradle project
* Thêm các thư viện cần thiết
* Tạo XML để cấu hình project, cấu hình các bean
* Code và build thành file WAR
* Cấu hình Tomcat server để chạy được file WAR vừa build

**Spring khá mạnh mẽ nhưng việc cấu hình nghe thôi cũng mệt rồi. Do đó Spring boot ra đời, với các ưu điểm:**
* Auto config: tự động cấu hình thay cho bạn, chỉ cần bắt đầu code và chạy là được
* Xây dựng các bean dựa trên annotation thay vì XML
* Server Tomcat được nhúng ngay trong file JAR build ra, chỉ cần chạy ở bất kì đâu java chạy được.

**So sánh với Spring, thì Spring Boot bạn chỉ cần:**
* Dùng Spring Initializr, nhập các info của project, chọn thư viện rồi down code về
* Mở source code ra và bắt đầu code
* Chạy ngay trong IDE, hoặc build thành file JAR để chạy được ngay, không cần cấu hình server

***Tính năng:***
* Tạo các ứng dụng Spring độc lậ
* Nhúng trực tiếp Tomcat, Jetty hoặc Undertow (không cần phải deploy ra file WAR)
* Các starter dependency giúp việc cấu hình Maven đơn giản hơn
* Tự động cấu hình Spring khi cần thiết
* Không sinh code cấu hình và không yêu cầu phải cấu hình bằng XML …




