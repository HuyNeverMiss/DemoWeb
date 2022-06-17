***Diệp Thanh Huy***
<h1>Báo cáo thực tập tuần 1</h1> 

<img src="https://octodex.github.com/images/daftpunktocat-thomas.gif" width="50%"><img src="https://octodex.github.com/images/daftpunktocat-guy.gif" width="50%">

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

<h2>Đôi nét về Spring Boot</h2>

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
* Tạo các ứng dụng Spring độc lập
* Nhúng trực tiếp Tomcat, Jetty hoặc Undertow (không cần phải deploy ra file WAR)
* Các starter dependency giúp việc cấu hình Maven đơn giản hơn
* Tự động cấu hình Spring khi cần thiết
* Không sinh code cấu hình và không yêu cầu phải cấu hình bằng XML …

***
***Diệp Thanh Huy & Cao Thanh Tuấn***
<h1>BÁO CÁO THỰC TẬP TUẦN 2</h1>

<img src="https://octodex.github.com/images/mona-the-rivetertocat.png" width="30%"><img src="https://octodex.github.com/images/manufacturetocat.png" width="30%">

**Đề tài:** Hệ thống quản lý chỉ đạo tuyến và NCKH.

***Nội dung công việc tuần 2:***
* Phân tích hệ thống.
* Vẽ sơ đồ chức năng (use case diagram), sơ đồ lớp(class diagram), sơ đồ tuần tự (sequence diagram).

**1. Phân tích hệ thống:**

-   Hệ thống "Quản lý chỉ đạo tuyến và NCKH" bao gồm 4 chức năng chính:

> \+ Quản lý lý do công tác: *quản lý lý do công tác của bác sĩ.*
>
> \+ Quản lý kết quả công tác: *quản lý kết quả công tác của bác sĩ.*
>
> \+ Quản lý kỹ thuật hỗ trợ: *quản lý kỹ thuật mà bác sĩ được hỗ trợ.*
>
> \+ Quản lý nơi đến công tác: *quản lý địa điểm mà bác sĩ được điều đi
> công tác.*
>
> \+ Quản lý vật tư hỗ trợ: *quản lý máy móc bác sĩ được hỗ trợ để trị
> bệnh.*

**2. Các sơ đồ của hệ thống:**

***2.1. Sơ đồ Class:***
> Bao gồm các thực thể: ChiDaoTuyen, LyDoCongTac, KetQuaCongTac,
> KyThuatHoTro, VatTuHoTro, NoiDungHoTro, NhanVienTiepNhan,
> BaoCaoTaiChinh, NoiDenCongTac, NoiDungCdt, DeTai, ChuyenMuc.

<img src="https://user-images.githubusercontent.com/89766191/172789217-43e09164-1e4b-477f-874a-2a2659267962.jpg">
*Sơ đồ Class của hệ thống "Quản lý chỉ đạo tuyến và NCKH*

***2.2. Sơ đồ UseCase:***

**Use Case Diagram** được hiểu là sơ đồ tính năng của hệ thống. Bản vẽ
này sẽ cho người dùng hiểu được sản phẩm này cung cấp những tính năng gì
cho người dùng, hoặc người dùng có thể làm được gì với nó.

<img src="https://user-images.githubusercontent.com/89766191/172792629-f4b8aa2d-a872-4c1e-a7f8-0d615b89fac0.jpg">
*Sơ đồ Usecase của các chức năng Quản lý chỉ đạo tuyến và nghiên cứu khoa học*

-   Chức năng quản lý lý do công tác:

> \+ Actor: Cán bộ quản lý
>
> \+ Usecase: Đăng nhập, danh mục, quản lý chỉ đạo tuyến, quản lý lý do
> công tác, thêm lý do công tác, sửa lý do công tác, xóa lý do công tác.

-   Chức năng quản lý kết quả công tác:

> \+ Actor: Cán bộ quản lý
> \+Usecase: Đăng nhập, danh mục, quản lý chỉ đạo tuyến, quản lý kết quả
    > công tác, thêm kết quả công tác, sửa kết quả công tác, xóa kết quả
    > công tác.

-   Chức năng quản lý nơi đến công tác:

> \+ Actor: Cán bộ quản lý
>
> \+ Usecase: Đăng nhập, danh mục, quản lý chỉ đạo tuyến, quản lý nơi đến công tác, thêm nơi đến công tác, sửa nơi đến công tác, xóa nơi đến công tác.
> 

-   Chức năng quản lý kỹ thuật hỗ trợ:

> \+ Actor: Cán bộ quản lý
>
> \+ Usecase: Đăng nhập, danh mục, quản lý chỉ đạo tuyến, quản lý kỹ
> thuật hỗ trợ, thêm kỹ thuật hỗ trợ, sửa kỹ thuật hỗ trợ, xóa kỹ thuật
> hỗ trợ.

-   Chức năng quản lý vật tư hỗ trợ:

> \+ Actor: Cán bộ quản lý
>
> \+ Usecase: Đăng nhập, danh mục, quản lý chỉ đạo tuyến, quản lý vật tư
> hỗ trợ, thêm vật tư hỗ trợ, sửa vật tư hỗ trợ, xóa vật tư hỗ trợ.

-   Chức năng quản lý đề tài:

> \+ Actor: Cán bộ quản lý
>
> \+ Usecase: Đăng nhập, danh mục, quản lý nghiên cứu khoa học, quản lý
> đề tài, thêm đề tài, sửa đề tài, xóa đề tài.

-   Chức năng quản lý chuyên mục:

> \+ Actor: Cán bộ quản lý
>
> \+ Usecase: Đăng nhập, danh mục, quản lý nghiên cứu khoa học, quản lý
> chuyên mục, thêm chuyên mục, sửa chuyên mục, xóa chuyên mục.

### \* Ứng dụng

-   Thiết kế hệ thống.

-   Làm cơ sở cho việc phát triển, kiểm tra các bản vẽ như Class
    Diagram, Activity Diagram, Sequence Diagram, Component Diagram.

-   Làm cơ sở để giao tiếp với khách hàng.

-   Hỗ trợ việc kiểm thử tính năng, chất lượng,....

***2.3. Sơ đồ tuần tự:***

Dùng để xác định các trình tự diễn ra sự kiện của một nhóm đối tượng. Nó
miêu tả chi tiết các thông điệp được gửi và nhận giữa các đối tượng đồng
thời cũng chú trọng đến việc trình tự về mặt thời gian gửi và nhận của
thông điệp đó.

<img src="https://user-images.githubusercontent.com/89766191/172797332-7b47cb8e-c8c4-45da-a8a7-6ea0202cbfb6.jpg">

*Sơ đồ tuần tự của chức năng "Quản lý lý do công tác"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của lý do công tác như: Mã lý do công tác, Thứ tự SX, tên
lý do công tác, sau đó hệ thống sẽ kiểm tra thông tin rồi sẽ cập nhật
trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn mục cần chỉnh sửa, sau đó nhấn vào nút sửa, sửa
thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn lý do công tác cần xóa, sau đó nhấn vào nút xóa,
lý do công tác sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172797424-a81062c9-4873-4580-b6c0-a6963ce82459.jpg">

*Sơ đồ tuần tự chức năng "Quản lý kết quả công tác"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của kết quả công tác như: Mã kết quả công tác, Thứ tự SX,
tên kết quả công tác, sau đó hệ thống sẽ kiểm tra thông tin rồi sẽ cập
nhật trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn kết quả công tác cần xóa, sau đó nhấn vào nút
xóa, kết quả công tác sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172797595-ed8e6ee6-d528-4410-9c1a-13b600f4b7b3.jpg">

*Sơ đồ tuần tự chức năng "Quản lý vật tư hỗ trợ"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của vật tư hỗ trợ như: Mã vật tư hỗ trợ, Thứ tự SX, tên
vật tư hỗ trợ, sau đó hệ thống sẽ kiểm tra thông tin rồi sẽ cập nhật
trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn vật tư hỗ trợ cần xóa, sau đó nhấn vào nút xóa,
vật tư hỗ trợ sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172797504-23609823-afa5-4851-9e22-cf6f9675ef84.jpg">

*Sơ đồ tuần tự chức năng "Quản lý nơi đến công tác"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của nơi đến công tác như: Mã nơi đến công tác, Thứ tự SX,
tên nơi đến công tác, sau đó hệ thống sẽ kiểm tra thông tin rồi sẽ cập
nhật trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn nơi đến công tác cần xóa, sau đó nhấn vào nút
xóa, nơi đến công tác sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172798021-17813b7f-6537-4cee-9908-96e0eb41d990.jpg">

*Sơ đồ tuần tự chức năng "Quản lý kỹ thuật hỗ trợ"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của kết quả công tác như: Mã kỹ thuật hỗ trợ, Thứ tự SX,
tên kỹ thuật hỗ trợ, sau đó hệ thống sẽ kiểm tra thông tin rồi sẽ cập
nhật trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn kỹ thuật hỗ trợ cần xóa, sau đó nhấn vào nút xóa,
kỹ thuật hỗ trợ sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172798102-42c3e7e3-a24a-4072-b947-b2dc99f9908b.jpg">

*Sơ đồ tuần tự "Chỉ đạo tuyến"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của chỉ đạo tuyến như: Số quyết định, ngày quyết định, nội
dung sau đó hệ thống sẽ kiểm tra thông tin rồi sẽ cập nhật trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn chỉ đạo tuyến cần xóa, sau đó nhấn vào nút xóa,
chỉ đạo tuyến sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172798141-7f501df0-f895-454c-8850-f3480627ca83.jpg">

*Sơ đồ tuần tự chức năng "Quản lý chuyên mục"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của chuyên mục như: Tên chuyên mục, Số TT, sau đó hệ thống
sẽ kiểm tra thông tin rồi sẽ cập nhật trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn chuyên mục cần xóa, sau đó nhấn vào nút xóa,
chuyên mục sẽ bị xóa khỏi CSDL.

<img src="https://user-images.githubusercontent.com/89766191/172798392-bde926a1-9ebd-4e9e-90a9-5d07876d13eb.jpg">

*Sơ đồ tuần tự chức năng "quản lý đề tài"*

**Mô tả:**

***Chức năng thêm:***

Sau khi Cán bộ quản lý đăng nhập vào hệ thống, cán bộ quản lý sẽ nhập
các thông tin của đề tài như:Chuyên mục, Số thứ tự, Tên đề tài, ngày bắt
đầu, ngày kết thúc, ngày duyệt, nghiệm thu, hiện trạng, xuất bản, cấp
quản lý, kinh phí, nguồn kinh phí sau đó hệ thống sẽ kiểm tra thông tin
rồi sẽ cập nhật trong CSDL.

***Chức năng sửa:***

Cán bộ quản lý sẽ chọn dữ liệu cần chỉnh sửa, sau đó nhấn vào nút sửa,
sửa thông tin và lưu, trả về kết quả thay đổi và lưu vào CSDL.

***Chức năng xóa:***

Cán bộ quản lý sẽ chọn đề tài cần xóa, sau đó nhấn vào nút xóa, đề tài
sẽ bị xóa khỏi CSDL.

***
***Diệp Thanh Huy & Cao Thanh Tuấn***
<h1>Báo cáo thực tập tuần 3</h1>
<img src="https://octodex.github.com/images/NUX_Octodex.gif" width="30%"> <img src="https://octodex.github.com/images/hula_loop_octodex03.gif" width="30%">

**Jhipster là gì?**

***Jhipster(viết tắt của Java Hipster)*** là cách đơn giản để chúng tạo ra project xung quanh những công nghệ được ưa thích nhất với Spring technologies và Angular/React. Jhipster hỗ trợ rất nhiều công nghệ khác nhau. 
>* Phía Backend, chúng ta có thể dùng các công gnheej như Spring boot, Spring Sercurity, Maven, Grandle... 
>* Phía Frontend, các framework chúng ta có thể sử dụng như React, Angular, VueJs,... 
>* Chúng ta có thể sử dụng nhiều loại cư sở dữ liệu khác nhau cả về Sql hoặc NoSql như MySql, Cassandra, MongoDB, PostgreSql... 
>* Sau khi generate chúng ta có thể đưa code của mình lên server, Jhipster hỗ trợ nhiều cách khác nhau như: Docker, Aws, Heroku,... 

**Yêu cầu hệ thống**
>* Cài đặt đầy đủ các thư viện cho Jhipster bao gồm:
>* Cài đặt cấu hình Java 11 trên máy tính.
>* Cài đặt NodeJs từ trang chủ.
>* Cài đặt Gradle.

**Khởi tạo dự án**
> \- Tất cả câu lệnh đều nhập thủ công trên command prompt của máy tính. Để tạo dự án Jhipster, thực hiện:
>* Tạo 1 thư mục trống chứa dự án bằng lệnh: mkdir "Tên dự án".
>* Chuyển đến thư mục vừa tạo bằng lệnh: cd "tên dự án".
>* Tạo tự động ứng dụng JHipster bằng lệnh: jhipster.
>* Sau đó, lựa chọn những thứ phù hợp với dự án của người dùng.

> \- Bây giờ người dùng đã có dự án với:
>* Backend: Spring boot
>* Frontend : Angular, Bootstrap.
>* Database: PostgreSql (production), H2 with disk-based (development).

> \- Chạy ứng dung Spring boot bằng lệnh: gradlew -x webapp.

> \- Sau đó dùng lệnh: npm start để khởi chạy trang web

**Angular trong các project Jhipster**

>* Angular được sử dụng để xây dựng giao diện người dụng trong dự án Jhipster
>* Thư viện ng-jhipster chứa các chức năng tiện ích và các thành phần phổ biến.
>	
>	\+ Validation directives
>	
>	\+ Internationalization components
>	
>	\+ Commonly-used pipes like capitalization, ordering and word truncation
>	
>	\+ Base64, date and pagination handling services
>	
>	\+ A notification system (see below)
>* Angular CLI là một công cụ để phát triển, xây dựng và duy trì các ứng dụng Angular.
>* Cấu trúc dự án được sử dụng theo kiểu Angular.
>* Sử dụng bộ định tuyến Angular để tổ chức các phần khác nhau của ứng dụng khách.

***
***Diệp Thanh Huy***
<h1>Báo cáo thực tập tuần 4</h1> 

<img src="https://octodex.github.com/images/jetpacktocat.png" width="50%"><img src="https://octodex.github.com/images/skatetocat.png" width="50%">

***Nội dung tuần 3: Vẽ sơ đồ luồng chức năng mục lý do công tác, mô tả cấu trúc file java phía backend***

***Các sơ đồ DFD:***
<img src="https://user-images.githubusercontent.com/89766191/173972976-2425bd62-d51e-46dd-910a-756a7eaee546.jpg">
*DFD View_LyDoCongTac*

<img src="https://user-images.githubusercontent.com/89766191/173972844-7e008999-05bf-4503-a4fc-ecd877f11c71.jpg">
*DFD Create_LyDoCongTac*

<img src="https://user-images.githubusercontent.com/89766191/173972899-d577c973-7427-4f78-9575-5a75b8f0d61e.jpg">
*DFD Edit_LyDoCongTac*

<img src="https://user-images.githubusercontent.com/89766191/173972955-a83284e5-ca04-45b3-91e8-d9bef087b352.jpg">
*DFD Delete_LyDoCongTac*

***Cấu trúc và chức năng file java phía backend***
> \* Mục Lý Do Công Tác : chức năng create lý do công tác
>
> * Gồm 7 file:
> 
>  	\+ LyDoCongTac.java
>  
>   \+ LyDoCongTacResource.java
>   
>   \+ LyDoCongTacRepository.java
>   
>   \+ LyDoCongTacMapper.java
>
>   \+ LyDoCongTacDTO.java
>
>	\+ LyDoCongTacService.java
>	
>	\+ LyDoCongTacServicelmpl.java
>
> * LyDoCongTacResource.java:
> 
>  <img src="https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.15752-9/285648346_544929460444432_4909397535736779253_n.png?_nc_cat=111&ccb=1-7&_nc_sid=ae9488&_nc_ohc=bLEImXkZgIUAX9Ob63s&_nc_oc=AQmE2ktnPM4wPLm4sKSSYxwOQJUTfevD67Fdg5gP8eeAwlTshDtyc4c_ZeY5FPxPXdc&_nc_ht=scontent.fsgn8-2.fna&oh=03_AVKZgO7xR4675wnz2M2al-alTc222T3XLDt_U6q3Xd1o5w&oe=62CF6DCA">
>  
>   File này là nơi thực thi các chức năng như thêm, sữa, xóa của file LyDoCongTacService.java. Với chức năng tạo lý do công tác của hàm "createLyDoCongTac" sẽ sử dụng @PostMapping đánh dấu xử lý Post request trong Controller. 
>
>	<img src="https://scontent.fsgn3-1.fna.fbcdn.net/v/t1.15752-9/287480219_1070647836873020_2022453886817013152_n.png?_nc_cat=111&ccb=1-7&_nc_sid=ae9488&_nc_ohc=U9VFX0ZT-DAAX88lHX1&_nc_ht=scontent.fsgn3-1.fna&oh=03_AVLpEX_tyAmoI9UbUgAgzrc5yfy0H345Oovu5E_q54af3w&oe=62D0D3B2">
>
> Hàm getAllLyDoCongTacs thực hiện lấy tất cả dữ liệu trong database trả về phía client sau khi admin thêm mới lý do công tác. 
>
> * LyDoCongTacRepository.java:
> 
> <img src="https://scontent.fsgn8-1.fna.fbcdn.net/v/t1.15752-9/285324745_1368678006939883_4745318130879299687_n.png?_nc_cat=102&ccb=1-7&_nc_sid=ae9488&_nc_ohc=29Ru0xnT2SMAX83Udpg&tn=SV6hVh2R4Cm3ZCu0&_nc_ht=scontent.fsgn8-1.fna&oh=03_AVJSLsaorrqIlgdUfwPvRyFkfkdfK2xSVbOE7OFg5UA6MA&oe=62CE5F79">
>
> Khai báo gói mở rộng Spring Data JPA JpaRepository chỉ định loại miền là LyDoCongTac và loại id là Long, cho phép chúng ta có thể thực hiện các chức năng thêm, sửa, xóa , tìm kiếm. 
> * LyDoCongTac.java:
> 
> <img src="https://scontent.fsgn8-1.fna.fbcdn.net/v/t1.15752-9/287131358_411137110937186_8985633758402898099_n.png?_nc_cat=102&ccb=1-7&_nc_sid=ae9488&_nc_ohc=H_btShD5QngAX-E3DsG&_nc_ht=scontent.fsgn8-1.fna&oh=03_AVICgeW31cLJjakvsBT9eayMeT2-GSZ8lbavdF7Lrd2cpQ&oe=62CEA693">
> 
> File này dùng để xác định 1 lý do công tác đã sẵn sàng để lưu trong cơ sở dữ liệu với các biến id, ma_ly_do, ten_ly_do, thu_tu_sx.
>
> * LyDoCongTacMapper.java:
> 
> <img src="https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.15752-9/287004568_356027339934819_6365761728075412296_n.png?_nc_cat=111&ccb=1-7&_nc_sid=ae9488&_nc_ohc=mehDle7qQ0wAX-TKipM&_nc_ht=scontent.fsgn8-2.fna&oh=03_AVI4gdPXSvYjX6jvL9e5LRVUl7GGbSo0JNmv710HRdoyKw&oe=62D09A46">
> 
>  Giao diện EntityMapper của nó để thực hiện ánh xạ giữa các đối tượng miền LyDoCongTac và DTO.
>
> * LyDoCongTacDTO.java:
> 
> <img src="https://scontent.fsgn13-2.fna.fbcdn.net/v/t1.15752-9/286615668_703086684108234_6040935629804751782_n.png?_nc_cat=106&ccb=1-7&_nc_sid=ae9488&_nc_ohc=aEHU19ElFOEAX_-lwy-&_nc_ht=scontent.fsgn13-2.fna&oh=03_AVJy0g-7mjNat7gOc6ZzWtsgk5OXdHsE-DsfxrEiWAAqTQ&oe=62D0E60A">
> 
> File này sử dụng phương thưc Override(ghi đè) lên file LyDoCongTac.java cũng dùng để xác định 1 lý do công tác với các biến id, ma_ly_do, ten_ly_do, thu_tu_sx.
>
> * LyDoCongTacService.java:
> 
> <img src="https://scontent.fsgn13-2.fna.fbcdn.net/v/t1.15752-9/287685580_482582366971049_1101915831464305505_n.png?_nc_cat=108&ccb=1-7&_nc_sid=ae9488&_nc_ohc=ntkoNAzBKdUAX_gt9ho&tn=SV6hVh2R4Cm3ZCu0&_nc_ht=scontent.fsgn13-2.fna&oh=03_AVLQm3anXBJKJc4iorHF3znfkUCjExjn0l9eEksEdyv7UA&oe=62CFC6C8">
> 
> File này quản lý các dịch vụ phục vụ các logic nghiệp vụ thêm, sữa, xóa, tìm kiếm theo id hoặc tìm tất cả.
>
> * LyDoCongTacServicelmpl.java:
> 
> <img src="https://scontent.fsgn3-1.fna.fbcdn.net/v/t1.15752-9/287059320_982233159120262_5101214496782636012_n.png?_nc_cat=104&ccb=1-7&_nc_sid=ae9488&_nc_ohc=R7QGACQ58GQAX9RnzOQ&_nc_ht=scontent.fsgn3-1.fna&oh=03_AVJqJj6gzXODScg6cv2_3gzxHrz-xRNCBg4xPr6V7TH4Uw&oe=62CF9232">
> 
> File này sẽ thực hiện các nghiệp vụ thêm, sữa, xóa, tìm theo id hoặc tìm tất cả, của file LyDoCongTacService.java sử dụng phương thức Override.
