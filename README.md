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

***Nội dung tuần 4: Vẽ sơ đồ luồng chức năng mục lý do công tác, mô tả cấu trúc file java phía backend***

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
>	  \+ LyDoCongTacService.java
>	
> 	\+ LyDoCongTacServicelmpl.java
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
>  Giao diện EntityMapper của nó để thực hiện ánh xạ giữa các đối tượng miền LyDoCongTac và DTO sử dụng mapstruct khi thêm @Mapping.
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

***Diệp Thanh Huy***
<h1>Báo cáo thực tập tuần 5</h1>
    
**Tìm hiểu về DTO**
    
DTO hay tên gọi đầy đủ là Data Tranfer Object là một design pattern lần đầu tiên được giới thiệu bởi Martin Fowler trong cuốn sách EAA. Mục đích sử dụng chính của DTO đó là giảm số lần gọi các method giữa các tiến trình xử lý.
    
DTO là một cấu trúc dữ liệu phẳng và không chứa business logic trong đó chỉ dùng để lưu trữ dữ liệu, các method cho phép cập dữ liệu và sử dụng trong quá trình serialization or deserialization. Dữ liệu được ánh xạ từ domain model sang DTO và ngược lại thông qua một thành phần gọi là Mapper được đặt trong presentation hoặc facade layer.   

Các dto sẽ tương ứng với các entity được đề cập ở trên nhưng sẽ khác bằng việc thay các entity bằng tên khóa chính của entity đó thông qua mapper.

<img src="https://shareprogramming.net/wp-content/uploads/2021/09/layers-4.svg">

***Domain model và DTO***

Chúng ta cần phân biệt giữa Domain model và DTO để tránh nhầm lẫn. Domain model là các Entity class dùng để ánh xạ một table trong database còn DTO là một object kết hợp nhiều tham số thành một đặt trong một DTO class.

    
 **Các dạng data**
    
 * Data trong ứng dụng Spring Boot được chia thành 2 loại chính:
 - Public: nghĩa là để trao đổi, chia sẻ với bên ngoài qua REST API hoặc giao tiếp với các service khác trong microservice. Data lúc này ở dạng DTO.
    
 - Private: các data dùng trong nội bộ ứng dụng, bên ngoài không nên biết. Data lúc này nằm trong các Domain model hoặc Entity.
    
Từ 2 loại Public và Private trên, chúng ta có 3 dạng data:
    
 -DTO (Data transfer object): là các class đóng gói data để chuyển giữa client - server hoặc giữa các service trong microservice. Mục đích tạo ra DTO là để giảm bớt lượng info không cần thiết phải chuyển đi, và cũng tăng cường độ bảo mật.
    
-Domain model: là các class đại diện cho các domain, hiểu là các đối tượng thuộc business như Client, Report, Department,... chẳng hạn. Trong ứng dụng thực, các class đại diện cho kết quả tính toán, các class làm tham số đầu vào cho service tính toán,... được coi là domain model.
    
-Entity: cũng là domain model nhưng tương ứng với table trong DB, có thể map vào DB được. Lưu ý chỉ có entity mới có thể đại diện cho data trong DB.
    
 ***Nên sử dụng DTO vì:***
    
- Trước đây data chỉ có 1 dạng, nhưng có nhiều layer, mỗi layer hành xử khác nhau với data nên data đã thực hiện nhiều nhiệm vụ. Điều này vi phạm vào Single responsibility, nên chúng ta cần chia nhỏ thành nhiều dạng data.

- Nếu data chỉ có một dạng thì sẽ bị leak (lộ) các dữ liệu nhạy cảm. Lấy ví dụ chức năng tìm kiếm bạn bè của Facebook, đúng ra chỉ trên trả về data chỉ có các info cơ bản (avatar, tên,...). Nếu chỉ có một dạng data thì toàn bộ thông tin sẽ được trả về. Mặc dù client chỉ hiển thị những info cần thiết, nhưng việc trả về toàn bộ thì kẻ xấu có thể lợi dụng để chôm các info nhạy cảm.
     
**Getters và Setters trong Java**
> Trong java, getter và setter là hai phương thức thông thường được sử dụng để truy xuất và cập nhật giá trị của một biến.
   
**Tại sao lại cần Getter và Setter?**
> Bằng cách sử dụng getter và setter, lập trình viên có thể kiểm soát cách các biến quan trọng của họ được truy cập và cập nhật theo cách thích hợp, chẳng hạn như thay đổi giá trị của một biến trong một phạm vi xác định.

### Tạo bảng (liquibase, dao, dto, các service, repository, resource ...)

Do Jhipster có hỗ trợ một công cụ generate liquibase để tạo bảng và gần như toàn bộ các công cụ có liên quan cho việc lập trình nên để giảm thời gian tạo tay thì chúng ta sẽ tạo 1 file json với cấu trúc tương tự như sau:

```json
{
  "applications": "*",
  "changelogDate": "20220623051414",
  "dto": "mapstruct",
  "embedded": false,
  "entityTableName": "ket_qua_cong_tac",
  "fields": [
    {
      "fieldName": "maKetQua",
      "fieldType": "String",
      "fieldValidateRules": ["required"]
    },
    {
      "fieldName": "tenKetQua",
      "fieldType": "String",
      "fieldValidateRules": ["required"]
    },
    {
      "fieldName": "thuTuSX",
      "fieldType": "String"
    }
  ],
  "fluentMethods": true,
  "jpaMetamodelFiltering": true,
  "name": "KetQuaCongTac",
  "pagination": "pagination",
  "readOnly": false,
  "relationships": [
    {
      "otherEntityName": "chiDaoTuyen",
      "otherEntityRelationshipName": "ketQuaCongTac",
      "relationshipName": "chiDaoTuyen",
      "relationshipType": "one-to-many"
    }
  ],
  "service": "serviceImpl"
}

```

- Sau khi đã có file json trên chạy lệnh `jhipster entityName` để jshiper tự tạo các thứ cần thiết để làm việc với bảng đó.
- Do giới hạn ký tự tên bảng và jhispter khác nhau nên có thể tên bảng sẽ phải thu gọn lại khi gọi lệnh trên. Nhưng sau đó có thể đổi lại tên bảng ở `@Table(name ="")` ở Entity và ``tableName`` ở file liquibase. Sau khi tất
cả đã ổn chạy ``liquibaseUpdate`` từ gradle để tiến hành tạo bảng và constraint, có thể comment phần fake-data để không có dữ liệu ảo.
- Sau khi đã generate ra thì đây là cấu trúc thư mục được khởi tạo:

### 1. Domain
```java
/**
 * A ChiDaoTuyen.
 */
@Entity
@Table(name = "chi_dao_tuyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChiDaoTuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "so_quyet_dinh", nullable = false)
    private String soQuyetDinh;

    @NotNull
    @Column(name = "ngay_quyet_dinh", nullable = false)
    private Instant ngayQuyetDinh;

    @NotNull
    @Column(name = "so_hd", nullable = false)
    private String soHD;

    @NotNull
    @Column(name = "ngay_hd", nullable = false)
    private Instant ngayHD;

    @NotNull
    @Column(name = "noi_dung", nullable = false)
    private String noiDung;

    @NotNull
    @Column(name = "ngay_bat_dau", nullable = false)
    private Instant ngayBatDau;

    @NotNull
    @Column(name = "ngay_ket_thuc", nullable = false)
    private Instant ngayKetThuc;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @NotNull
    @Column(name = "ngay_tao", nullable = false)
    private Instant ngayTao;

    @Column(name = "so_bn_kham_dieu_tri")
    private String soBnKhamDieuTri;

    @Column(name = "so_bn_phau_thuat")
    private String soBnPhauThuat;

    @Column(name = "so_can_bo_chuyen_giao")
    private String soCanBoChuyenGiao;

    @Column(name = "luu_tru")
    private String luuTru;

    @Column(name = "tien_an")
    private String tienAn;

    @Column(name = "tien_o")
    private String tienO;

    @Column(name = "tien_di_lai")
    private String tienDiLai;

    @Column(name = "tai_lieu")
    private String taiLieu;

    @Column(name = "giang_day")
    private String giangDay;

    @Column(name = "khac")
    private String khac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private LyDoCongTac lyDoCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private NoiDenCongTac noiDenCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private KetQuaCongTac ketQuaCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private KyThuatHoTro kyThuatHoTro;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private VatTuHoTro vatTuHoTro;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private NhanVien nhanVien;

//Getter,Setter...
```
- Một Entity (Domain) là ánh xạ của 1 bảng ở db vào java, với ``@Column(name = "tencotdb")``
- Đối với các mối quan hệ Many to One, One to many, thì khóa ngoại sẽ được thể hiện qua việc các
dựa trên entity này chứa entity khác. Như ví dụ thì trong bảng ``chi_dao_tuyen`` có chứa 6 foreign key từ
  bảng lyDoCongTac, noiDenCongTac, ketQuaCongTac, kyThuatHoTro, vatTuHoTro và nhanVien. Mặc định các entity kia Spring sẽ tự hiểu thông qua các Mapper do
  trong DTO tương ứng với entity đó các khóa ngoại sẽ được thể hiện ở dạng 1 trường id chứ không còn
  là entity lồng nhau nữa ví dụ: ``lyDoCongTac`` sẽ được thay bằng ``LyDoCongTacDTO lyDoCongTac``
- Ở 1 số trường hợp sẽ dùng ``@JoinColumn`` để map các khóa ngoại lại với nhau ở [JoinColumn Explain]
- Ngoài ra còn 1 thứ quan trọng sẽ tác động hiệu năng khi lấy dữ liệu từ db ánh xạ lên entity đó là
kiểu ``Fetch`` của Entity, đại loại như ví dụ trên khi ta lấy dữ liệu từ bảng ``chi_dao_tuyen`` thì
Spring sẽ lấy luôn dữ liệu của các dòng dữ liệu khóa ngoại như ``lyDoCongTac``, ``noiDenCongTac`` lên
nếu như ta để mặc định hoặc ``Fetch.Eager``. Tham khảo thêm tại [Fetch của entity](https://www.baeldung.com/hibernate-lazy-eager-loading).  
- Các thuộc tính đi kèm trong ``@Column`` như ``nullable``, ``length`` sẽ được tự tạo khi file jhipster của
entity được generate.

### 2. Repository
```java
package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ChiDaoTuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiDaoTuyenRepository extends JpaRepository<ChiDaoTuyen, Long>, JpaSpecificationExecutor<ChiDaoTuyen> {}

```
- Repository là 1 Interface được định nghĩa với @Repository, đại loại có thể hiểu repository là 1 kho lưu trữ, truy xuất dữ liệu trung gian giữa các entity và 
database. Chi tiết  (https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html)

### 3.DTO
- Các dto sẽ tương ứng với các entity được đề cập ở trên nhưng sẽ khác bằng việc thay các entity bằng tên khóa chính của entity đó thông qua mapper.
Ví dụ như 1 DTO của Entity ``chi_dao_tuyen`` (việc có các trường do người dùng tự định nghĩa để ra qua api sẽ không ảnh hưởng) sẽ có cấu trúc như sau:
```java
/**
 * A DTO for the {@link com.mycompany.myapp.domain.ChiDaoTuyen} entity.
 */
public class ChiDaoTuyenDTO implements Serializable {

    private Long id;

    @NotNull
    private String soQuyetDinh;

    @NotNull
    private Instant ngayQuyetDinh;

    @NotNull
    private String soHD;

    @NotNull
    private Instant ngayHD;

    @NotNull
    private String noiDung;

    @NotNull
    private Instant ngayBatDau;

    @NotNull
    private Instant ngayKetThuc;

    private String ghiChu;

    @NotNull
    private Instant ngayTao;

    private String soBnKhamDieuTri;

    private String soBnPhauThuat;

    private String soCanBoChuyenGiao;

    private String luuTru;

    private String tienAn;

    private String tienO;

    private String tienDiLai;

    private String taiLieu;

    private String giangDay;

    private String khac;

    private LyDoCongTacDTO lyDoCongTac;

    private NoiDenCongTacDTO noiDenCongTac;

    private KetQuaCongTacDTO ketQuaCongTac;

    private KyThuatHoTroDTO kyThuatHoTro;

    private VatTuHoTroDTO vatTuHoTro;

    private NhanVienDTO nhanVien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoQuyetDinh() {
        return soQuyetDinh;
    }

    public void setSoQuyetDinh(String soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public Instant getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(Instant ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public String getSoHD() {
        return soHD;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public Instant getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(Instant ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Instant getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Instant ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Instant getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Instant ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getSoBnKhamDieuTri() {
        return soBnKhamDieuTri;
    }

    public void setSoBnKhamDieuTri(String soBnKhamDieuTri) {
        this.soBnKhamDieuTri = soBnKhamDieuTri;
    }

    public String getSoBnPhauThuat() {
        return soBnPhauThuat;
    }

    public void setSoBnPhauThuat(String soBnPhauThuat) {
        this.soBnPhauThuat = soBnPhauThuat;
    }

    public String getSoCanBoChuyenGiao() {
        return soCanBoChuyenGiao;
    }

    public void setSoCanBoChuyenGiao(String soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public String getLuuTru() {
        return luuTru;
    }

    public void setLuuTru(String luuTru) {
        this.luuTru = luuTru;
    }

    public String getTienAn() {
        return tienAn;
    }

    public void setTienAn(String tienAn) {
        this.tienAn = tienAn;
    }

    public String getTienO() {
        return tienO;
    }

    public void setTienO(String tienO) {
        this.tienO = tienO;
    }

    public String getTienDiLai() {
        return tienDiLai;
    }

    public void setTienDiLai(String tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public String getTaiLieu() {
        return taiLieu;
    }

    public void setTaiLieu(String taiLieu) {
        this.taiLieu = taiLieu;
    }

    public String getGiangDay() {
        return giangDay;
    }

    public void setGiangDay(String giangDay) {
        this.giangDay = giangDay;
    }

    public String getKhac() {
        return khac;
    }

    public void setKhac(String khac) {
        this.khac = khac;
    }

    public LyDoCongTacDTO getLyDoCongTac() {
        return lyDoCongTac;
    }

    public void setLyDoCongTac(LyDoCongTacDTO lyDoCongTac) {
        this.lyDoCongTac = lyDoCongTac;
    }

    public NoiDenCongTacDTO getNoiDenCongTac() {
        return noiDenCongTac;
    }

    public void setNoiDenCongTac(NoiDenCongTacDTO noiDenCongTac) {
        this.noiDenCongTac = noiDenCongTac;
    }

    public KetQuaCongTacDTO getKetQuaCongTac() {
        return ketQuaCongTac;
    }

    public void setKetQuaCongTac(KetQuaCongTacDTO ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public KyThuatHoTroDTO getKyThuatHoTro() {
        return kyThuatHoTro;
    }

    public void setKyThuatHoTro(KyThuatHoTroDTO kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public VatTuHoTroDTO getVatTuHoTro() {
        return vatTuHoTro;
    }

    public void setVatTuHoTro(VatTuHoTroDTO vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public NhanVienDTO getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVienDTO nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiDaoTuyenDTO)) {
            return false;
        }

        ChiDaoTuyenDTO chiDaoTuyenDTO = (ChiDaoTuyenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chiDaoTuyenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiDaoTuyenDTO{" +
            "id=" + getId() +
            ", soQuyetDinh='" + getSoQuyetDinh() + "'" +
            ", ngayQuyetDinh='" + getNgayQuyetDinh() + "'" +
            ", soHD='" + getSoHD() + "'" +
            ", ngayHD='" + getNgayHD() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", ngayBatDau='" + getNgayBatDau() + "'" +
            ", ngayKetThuc='" + getNgayKetThuc() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", soBnKhamDieuTri='" + getSoBnKhamDieuTri() + "'" +
            ", soBnPhauThuat='" + getSoBnPhauThuat() + "'" +
            ", soCanBoChuyenGiao='" + getSoCanBoChuyenGiao() + "'" +
            ", luuTru='" + getLuuTru() + "'" +
            ", tienAn='" + getTienAn() + "'" +
            ", tienO='" + getTienO() + "'" +
            ", tienDiLai='" + getTienDiLai() + "'" +
            ", taiLieu='" + getTaiLieu() + "'" +
            ", giangDay='" + getGiangDay() + "'" +
            ", khac='" + getKhac() + "'" +
            ", lyDoCongTac=" + getLyDoCongTac() +
            ", noiDenCongTac=" + getNoiDenCongTac() +
            ", ketQuaCongTac=" + getKetQuaCongTac() +
            ", kyThuatHoTro=" + getKyThuatHoTro() +
            ", vatTuHoTro=" + getVatTuHoTro() +
            ", nhanVien=" + getNhanVien() +
            "}";
    }
}
```
- Như chúng ta thấy entity ``LyDoCongTac lyDoCongTac`` và ``NoiDenCongTac noiDenCongTac`` đã được thay thế thành 
``LyDoCongTacDTO lyDoCongTac và NoiDenCongTacDTO noiDenCongTac``. Sở dĩ Repository có thể hiểu được là nhờ vào mapper.

### 4.Mapper (Mapstruct)
- [Mapstruct](https://viblo.asia/p/huong-dan-su-dung-mapstruct-de-mapping-giua-cac-java-bean-trong-java-gDVK22PwKLj) thường được sử dụng để map các thuộc tính giữa các object với nhau. 
- Code ví dụ (Map các thuộc tính giữa entity và DTO của nó):
```java

/**
 * Mapper for the entity {@link ChiDaoTuyen} and its DTO {@link ChiDaoTuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiDaoTuyenMapper extends EntityMapper<ChiDaoTuyenDTO, ChiDaoTuyen> {
    @Mapping(target = "lyDoCongTac", source = "lyDoCongTac", qualifiedByName = "lyDoCongTacId")
    @Mapping(target = "noiDenCongTac", source = "noiDenCongTac", qualifiedByName = "noiDenCongTacId")
    @Mapping(target = "ketQuaCongTac", source = "ketQuaCongTac", qualifiedByName = "ketQuaCongTacId")
    @Mapping(target = "kyThuatHoTro", source = "kyThuatHoTro", qualifiedByName = "kyThuatHoTroId")
    @Mapping(target = "vatTuHoTro", source = "vatTuHoTro", qualifiedByName = "vatTuHoTroId")
    @Mapping(target = "nhanVien", source = "nhanVien", qualifiedByName = "nhanVienId")
    ChiDaoTuyenDTO toDto(ChiDaoTuyen s);

    @Named("lyDoCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LyDoCongTacDTO toDtoLyDoCongTacId(LyDoCongTac lyDoCongTac);

    @Named("noiDenCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NoiDenCongTacDTO toDtoNoiDenCongTacId(NoiDenCongTac noiDenCongTac);

    @Named("ketQuaCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    KetQuaCongTacDTO toDtoKetQuaCongTacId(KetQuaCongTac ketQuaCongTac);

    @Named("kyThuatHoTroId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    KyThuatHoTroDTO toDtoKyThuatHoTroId(KyThuatHoTro kyThuatHoTro);

    @Named("vatTuHoTroId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VatTuHoTroDTO toDtoVatTuHoTroId(VatTuHoTro vatTuHoTro);

    @Named("nhanVienId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NhanVienDTO toDtoNhanVienId(NhanVien nhanVien);
}

```
- ChiDaoTuyenMapper sẽ tự ``Autowired`` các mapper của những entitty có liên quan để sử dụng
mà không cần phải khai báo lại.

### 5.Criteria
- [Criteria](https://www.jhipster.tech/entities-filtering/) là 1 class sử dụng thư viên criteria do jhipster hỗ trợ. Các thuộc tính sẽ tương ứng với các thuộc tính trong DTo
nhưng kiểu dữ liệu sẽ kèm theo ``Filer`` ví dụ: ``Integer = IntegerFilter``
- Các Filter sẽ hỗ trợ tùy theo như ``setEquals, setContains(StringFilter), greaterThanOrEquals(LocalDateFilter, ZonedDateTimeFilter)...``
các thuộc tính này sẽ được sử dụng trong phần ``createSpecification`` ở QueryService để build các câu truy vấn sẽ được đề cấp sau đây
- Code mẫu: 
```java
/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ChiDaoTuyen} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ChiDaoTuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chi-dao-tuyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ChiDaoTuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter soQuyetDinh;

    private InstantFilter ngayQuyetDinh;

    private StringFilter soHD;

    private InstantFilter ngayHD;

    private StringFilter noiDung;

    private InstantFilter ngayBatDau;

    private InstantFilter ngayKetThuc;

    private StringFilter ghiChu;

    private InstantFilter ngayTao;

    private StringFilter soBnKhamDieuTri;

    private StringFilter soBnPhauThuat;

    private StringFilter soCanBoChuyenGiao;

    private StringFilter luuTru;

    private StringFilter tienAn;

    private StringFilter tienO;

    private StringFilter tienDiLai;

    private StringFilter taiLieu;

    private StringFilter giangDay;

    private StringFilter khac;

    private LongFilter lyDoCongTacId;

    private LongFilter noiDenCongTacId;

    private LongFilter ketQuaCongTacId;

    private LongFilter kyThuatHoTroId;

    private LongFilter vatTuHoTroId;

    private LongFilter nhanVienId;

    private Boolean distinct;

    public ChiDaoTuyenCriteria() {}

    public ChiDaoTuyenCriteria(ChiDaoTuyenCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.soQuyetDinh = other.soQuyetDinh == null ? null : other.soQuyetDinh.copy();
        this.ngayQuyetDinh = other.ngayQuyetDinh == null ? null : other.ngayQuyetDinh.copy();
        this.soHD = other.soHD == null ? null : other.soHD.copy();
        this.ngayHD = other.ngayHD == null ? null : other.ngayHD.copy();
        this.noiDung = other.noiDung == null ? null : other.noiDung.copy();
        this.ngayBatDau = other.ngayBatDau == null ? null : other.ngayBatDau.copy();
        this.ngayKetThuc = other.ngayKetThuc == null ? null : other.ngayKetThuc.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.ngayTao = other.ngayTao == null ? null : other.ngayTao.copy();
        this.soBnKhamDieuTri = other.soBnKhamDieuTri == null ? null : other.soBnKhamDieuTri.copy();
        this.soBnPhauThuat = other.soBnPhauThuat == null ? null : other.soBnPhauThuat.copy();
        this.soCanBoChuyenGiao = other.soCanBoChuyenGiao == null ? null : other.soCanBoChuyenGiao.copy();
        this.luuTru = other.luuTru == null ? null : other.luuTru.copy();
        this.tienAn = other.tienAn == null ? null : other.tienAn.copy();
        this.tienO = other.tienO == null ? null : other.tienO.copy();
        this.tienDiLai = other.tienDiLai == null ? null : other.tienDiLai.copy();
        this.taiLieu = other.taiLieu == null ? null : other.taiLieu.copy();
        this.giangDay = other.giangDay == null ? null : other.giangDay.copy();
        this.khac = other.khac == null ? null : other.khac.copy();
        this.lyDoCongTacId = other.lyDoCongTacId == null ? null : other.lyDoCongTacId.copy();
        this.noiDenCongTacId = other.noiDenCongTacId == null ? null : other.noiDenCongTacId.copy();
        this.ketQuaCongTacId = other.ketQuaCongTacId == null ? null : other.ketQuaCongTacId.copy();
        this.kyThuatHoTroId = other.kyThuatHoTroId == null ? null : other.kyThuatHoTroId.copy();
        this.vatTuHoTroId = other.vatTuHoTroId == null ? null : other.vatTuHoTroId.copy();
        this.nhanVienId = other.nhanVienId == null ? null : other.nhanVienId.copy();
        this.distinct = other.distinct;
    }
  //Getter, setter, equals, hasCode, toString
}
```

### 6.QueryService
- Trong ``QueryService`` sẽ có 1 hàm ``createSpecification``, [Specicification](https://loda.me/spring-jpa-huong-dan-su-dung-specification-phan-1-loda1575947295198/).
Đại loại specification ở các ``QueryService`` ta có thể hiểu là phần phía sau ``where`` của một câu query.
- Một ``Specification<TenEntity>`` sẽ được sử dụng trong Repository với hàm ``findAll()``.
- Có thể ghép nhiều ``Specification`` lại với nhau thông qua ``.and`` hoặc ``.or``  
- Code mẫu 1 số cách sử dụng Specification:
```java
package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.criteria.ChiDaoTuyenCriteria;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.mapper.ChiDaoTuyenMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ChiDaoTuyen} entities in the database.
 * The main input is a {@link ChiDaoTuyenCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChiDaoTuyenDTO} or a {@link Page} of {@link ChiDaoTuyenDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChiDaoTuyenQueryService extends QueryService<ChiDaoTuyen> {

    private final Logger log = LoggerFactory.getLogger(ChiDaoTuyenQueryService.class);

    private final ChiDaoTuyenRepository chiDaoTuyenRepository;

    private final ChiDaoTuyenMapper chiDaoTuyenMapper;

    public ChiDaoTuyenQueryService(ChiDaoTuyenRepository chiDaoTuyenRepository, ChiDaoTuyenMapper chiDaoTuyenMapper) {
        this.chiDaoTuyenRepository = chiDaoTuyenRepository;
        this.chiDaoTuyenMapper = chiDaoTuyenMapper;
    }

    /**
     * Return a {@link List} of {@link ChiDaoTuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChiDaoTuyenDTO> findByCriteria(ChiDaoTuyenCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ChiDaoTuyen> specification = createSpecification(criteria);
        return chiDaoTuyenMapper.toDto(chiDaoTuyenRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ChiDaoTuyenDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ChiDaoTuyenDTO> findByCriteria(ChiDaoTuyenCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ChiDaoTuyen> specification = createSpecification(criteria);
        return chiDaoTuyenRepository.findAll(specification, page).map(chiDaoTuyenMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChiDaoTuyenCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ChiDaoTuyen> specification = createSpecification(criteria);
        return chiDaoTuyenRepository.count(specification);
    }

    /**
     * Function to convert {@link ChiDaoTuyenCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ChiDaoTuyen> createSpecification(ChiDaoTuyenCriteria criteria) {
        Specification<ChiDaoTuyen> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ChiDaoTuyen_.id));
            }
            if (criteria.getSoQuyetDinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoQuyetDinh(), ChiDaoTuyen_.soQuyetDinh));
            }
            if (criteria.getNgayQuyetDinh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayQuyetDinh(), ChiDaoTuyen_.ngayQuyetDinh));
            }
            if (criteria.getSoHD() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoHD(), ChiDaoTuyen_.soHD));
            }
            if (criteria.getNgayHD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayHD(), ChiDaoTuyen_.ngayHD));
            }
            if (criteria.getNoiDung() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiDung(), ChiDaoTuyen_.noiDung));
            }
            if (criteria.getNgayBatDau() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayBatDau(), ChiDaoTuyen_.ngayBatDau));
            }
            if (criteria.getNgayKetThuc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayKetThuc(), ChiDaoTuyen_.ngayKetThuc));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), ChiDaoTuyen_.ghiChu));
            }
            if (criteria.getNgayTao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayTao(), ChiDaoTuyen_.ngayTao));
            }
            if (criteria.getSoBnKhamDieuTri() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoBnKhamDieuTri(), ChiDaoTuyen_.soBnKhamDieuTri));
            }
            if (criteria.getSoBnPhauThuat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoBnPhauThuat(), ChiDaoTuyen_.soBnPhauThuat));
            }
            if (criteria.getSoCanBoChuyenGiao() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getSoCanBoChuyenGiao(), ChiDaoTuyen_.soCanBoChuyenGiao));
            }
            if (criteria.getLuuTru() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLuuTru(), ChiDaoTuyen_.luuTru));
            }
            if (criteria.getTienAn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTienAn(), ChiDaoTuyen_.tienAn));
            }
            if (criteria.getTienO() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTienO(), ChiDaoTuyen_.tienO));
            }
            if (criteria.getTienDiLai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTienDiLai(), ChiDaoTuyen_.tienDiLai));
            }
            if (criteria.getTaiLieu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTaiLieu(), ChiDaoTuyen_.taiLieu));
            }
            if (criteria.getGiangDay() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGiangDay(), ChiDaoTuyen_.giangDay));
            }
            if (criteria.getKhac() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKhac(), ChiDaoTuyen_.khac));
            }
            if (criteria.getLyDoCongTacId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getLyDoCongTacId(),
                            root -> root.join(ChiDaoTuyen_.lyDoCongTac, JoinType.LEFT).get(LyDoCongTac_.id)
                        )
                    );
            }
            if (criteria.getNoiDenCongTacId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getNoiDenCongTacId(),
                            root -> root.join(ChiDaoTuyen_.noiDenCongTac, JoinType.LEFT).get(NoiDenCongTac_.id)
                        )
                    );
            }
            if (criteria.getKetQuaCongTacId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getKetQuaCongTacId(),
                            root -> root.join(ChiDaoTuyen_.ketQuaCongTac, JoinType.LEFT).get(KetQuaCongTac_.id)
                        )
                    );
            }
            if (criteria.getKyThuatHoTroId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getKyThuatHoTroId(),
                            root -> root.join(ChiDaoTuyen_.kyThuatHoTro, JoinType.LEFT).get(KyThuatHoTro_.id)
                        )
                    );
            }
            if (criteria.getVatTuHoTroId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getVatTuHoTroId(),
                            root -> root.join(ChiDaoTuyen_.vatTuHoTro, JoinType.LEFT).get(VatTuHoTro_.id)
                        )
                    );
            }
            if (criteria.getNhanVienId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getNhanVienId(),
                            root -> root.join(ChiDaoTuyen_.nhanVien, JoinType.LEFT).get(NhanVien_.id)
                        )
                    );
            }
        }
        return specification;
    }
}

```


***Diệp Thanh Huy & Cao Thanh Tuấn***
<h1>Báo cáo thực tập tuần 6</h1>

<img src="https://scontent.fsgn4-1.fna.fbcdn.net/v/t1.15752-9/286346948_356776589871969_6120382663026335379_n.png?_nc_cat=101&ccb=1-7&_nc_sid=ae9488&_nc_ohc=GJwrZ622J2sAX-EQcQD&_nc_ht=scontent.fsgn4-1.fna&oh=03_AVLJzSCp812H6_mz9Le3ASDMSjsDXm4-g8WRPC7LnqGIYw&oe=62E40818">
- Đây là giao diện chủ khi người dùng đăng nhập vào


<img src="https://scontent.fsgn13-4.fna.fbcdn.net/v/t1.15752-9/286314707_1532784020491570_2976475086756235986_n.png?_nc_cat=107&ccb=1-7&_nc_sid=ae9488&_nc_ohc=QuiSBKUAtbgAX9fu2xb&tn=JeuMJ6bi5WQPzS2N&_nc_ht=scontent.fsgn13-4.fna&oh=03_AVKcd7cxSicEPw3l9IMTAjEE11d9rz8nm8iBG9o1pqSmrQ&oe=62E55C24">
- Trên thanh công cụ chọn Chi Dao Tuyen  -> Ly Do Cong Tac.
- Trên màn hình cấu hình danh mục lý do công tác, sau khi điền đầy đủ thông tin nhấn nút Create để thêm Lý do công tác của bác sĩ ( khai báo đầy đủ thông tin của Lý do công tác như: Mã lý do, tên lý do, thứ tự SX).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn lý do cần xóa. Nhấn vào nút Delete. Thông tin Lý do công tác cần xóa đã được xóa.
- Để sửa thông tin nhập sai, chọn Lý do công tác cần sửa. Sửa các thông tin. Sau đó nhấn nút Edit. Thông tin Lý do công tác sẽ được chỉnh sửa.

<img src="https://scontent.fsgn13-4.fna.fbcdn.net/v/t1.15752-9/286582251_4015683241991217_5020803794271696994_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=qCsts600jMQAX-WTSB_&_nc_ht=scontent.fsgn13-4.fna&oh=03_AVLhqbPcXYjb6kZ20fdICe7kVxt_yQ2dLG6BpTj6lIpokg&oe=62E4DF56">
- Trên thanh công cụ chọn Chi Dao Tuyen  -> Ket Qua Cong Tac.
- Trên màn hình cấu hình danh mục kết quả công tác, sau khi điền đầy đủ thông tin nhấn nút Create để thêm Kết quả công tác của bác sĩ ( khai báo đầy đủ thông tin của Kết quả công tác như: Mã kết quả, tên kết quả, thứ tự SX).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn Kết quả công tác cần xóa. Nhấn vào nút Delete. Thông tin Kết quả công tác cần xóa đã được xóa.
- Để sửa thông tin nhập sai, chọn Kết quả công tác cần sửa. Sửa các thông tin. Sau đó nhấn nút Edit. Thông tin Kết quả công tác sẽ được chỉnh sửa.

<img src="https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.15752-9/288798125_701146927643341_4903978954718303917_n.png?_nc_cat=105&ccb=1-7&_nc_sid=ae9488&_nc_ohc=s_ETW9-qmgwAX8wooTM&_nc_ht=scontent.fsgn8-2.fna&oh=03_AVJja8IW0WeoFkob79pHQivtaJsutvpn_x4YcyvHuZAqeQ&oe=62E4B6D8">

- Trên thanh công cụ chọn Chi Dao Tuyen  ->Ky Thuat Ho Tro.
- Trên màn hình cấu hình danh mục kỹ thuật hỗ trợ, sau khi điền đầy đủ thông tin nhấn nút Create để thêm Kỹ thuật hỗ trợ của bác sĩ ( khai báo đầy đủ thông tin của Kỹ thuật hỗ trợ như: Mã kỹ thuật, tên kỹ thuật, thứ tự SX).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn Kỹ thuật hỗ trợ cần xóa. Nhấn vào nút Delete. Thông tin Kỹ thuật hỗ trợ cần xóa đã được xóa.
- Để sửa thông tin nhập sai, chọn kỹ thuật hỗ trợ cần sửa. Sửa các thông tin. Sau đó nhấn nút Edit. Thông tin Kỹ thuật hỗ trợ sẽ được chỉnh sửa.

<img src="https://scontent.fsgn13-2.fna.fbcdn.net/v/t1.15752-9/287951851_780415759757304_7771173869713048328_n.png?_nc_cat=106&ccb=1-7&_nc_sid=ae9488&_nc_ohc=Lsy6lIoAWs0AX9QxkRB&_nc_ht=scontent.fsgn13-2.fna&oh=03_AVJ3ccJDWL9q2z1Rv1MMfZ_mQ6t1l48Gluh0cSMbwDJ8cA&oe=62E4BE10">

- Trên thanh công cụ chọn Chi Dao Tuyen  ->Noi Den Cong Tac.
- Trên màn hình cấu hình danh mục nơi đến công tác, sau khi điền đầy đủ thông tin nhấn nút Create để thêm Nơi đến công tác của bác sĩ ( khai báo đầy đủ thông tin của Nơi đến công tác như: Mã nơi đến, tên nơi đến, thứ tự SX).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn Nơi đến công tác cần xóa. Nhấn vào nút Delete. Thông tin Nơi đến công tác cần xóa đã được xóa.
- Để sửa thông tin nhập sai, chọn Nơi đến công tác cần sửa. Sửa các thông tin. Sau đó nhấn nút Edit. Thông tin Nơi đến công tác sẽ được chỉnh sửa.

<img src="https://scontent.fsgn13-4.fna.fbcdn.net/v/t1.15752-9/282679183_1718035861867489_9202883709370947801_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=y1ICohU4Cx8AX9_w_L8&_nc_ht=scontent.fsgn13-4.fna&oh=03_AVKWcKHh28127NWoAYgURqq7Ntf7wwh0JWgkbCFMea3doA&oe=62E4B170">

- Trên thanh công cụ chọn Chi Dao Tuyen  ->Vat Tu Ho Tro.
- Trên màn hình cấu hình danh mục vật tư hỗ trợ, sau khi điền đầy đủ thông tin nhấn nút Create để thêm Vật tư hỗ trợ của bác sĩ ( khai báo đầy đủ thông tin của Vật tư hỗ trợ như: Mã vật tư, tên vật tư, thứ tự SX).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn Vật tư hỗ trợ cần xóa. Nhấn vào nút Delete. Thông tin Vật tư hỗ trợ cần xóa đã được xóa.
- Để sửa thông tin nhập sai, chọn Vật tư hỗ trợ cần sửa. Sửa các thông tin. Sau đó nhấn nút Edit. Thông tin Vật tư hỗ trợ sẽ được chỉnh sửa.

<img src="https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.15752-9/289054200_1473073346471361_8741898594378866951_n.png?_nc_cat=105&ccb=1-7&_nc_sid=ae9488&_nc_ohc=imHjcgyhUuYAX8apQJB&_nc_ht=scontent.fsgn8-2.fna&oh=03_AVKuuFwOZz91GZlOKMLLRjZq7-7ioQOxhhn3tQIHoh8JpA&oe=62E5D37A">

- Trên thanh công cụ chọn Chi Dao Tuyen  ->Nhan Vien.
- Trên màn hình cấu hình danh mục vật tư hỗ trợ, sau khi điền đầy đủ thông tin nhấn nút Create để thêm Nhân viên( khai báo đầy đủ thông tin ủa nhân viên như: Mã Nhân viên, chức vụ).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn Nhân viên cần xóa. Nhấn vào nút Delete. Thông tin Nhân viên cần xóa đã được xóa.
- Để sửa thông tin nhập sai, chọn Nhân viên cần sửa. Sửa các thông tin. Sau đó nhấn nút Edit. Thông tin Nhân viên sẽ được chỉnh sửa.

<img src="https://scontent.fsgn4-1.fna.fbcdn.net/v/t1.15752-9/289994162_482165663673606_4195901214651386204_n.png?_nc_cat=103&ccb=1-7&_nc_sid=ae9488&_nc_ohc=t1ASAbLAwPAAX8pe-jJ&_nc_ht=scontent.fsgn4-1.fna&oh=03_AVKIL424L_6uJMJirSHVpvT79TMUdIN8o6ADo8XoW-quCw&oe=62E3A68D">

- Trên thanh công cụ chọn Chi Dao Tuyen  ->Chi Dao Tuyen.
- Trên màn hình cấu hình danh mục vật tư hỗ trợ, nhấn vào nút Create để thêm mới chỉ đạo tuyến, sau khi điền đầy đủ thông tin của chỉ đạo tuyến nhấn vào nút lưu để lưu các thông tin đã được khai báo( khai báo đầy đủ thông tin của Chỉ đạo tuyến như: Số quyết định, ngày quyết định, số hợp đồng, ngày hợp đồng, Nội dung, từ ngày , đến ngày, ghi chú, ngày tạo, nhân viên, kỹ thuật hỗ trợ, vật tư hỗ trợ, Số bệnh nhân khám và điều trị, số bệnh nhân phẫu thuật, số cán bộ được chuyển giao, đánh giá kết quả công tác, lưu trú, tiền ăn, tiền ở, tiền đi lại, tài liệu, giảng dạy, khác.).
- Thông tin sẽ được hiển thị ở bên trái màn hình.
- Để xóa thông tin nhập sai, chọn chỉ đạo tuyến cần xóa. Nhấn vào nút Delete. Thông tin chỉ đạo tuyến cần xóa đã được xóa.

<br>
<h2>Báo cáo tuần 7</h2>
<h3>Chức năng đăng nhập</h3>
<table>
	<thead>
      <th> STT </th>
      <th> Miêu tả test case </th>
      <th> Các bước kiểm thử </th>
      <th> Kết quả mong đợi </th>
      <th> Kết quả đạt được </th>
      <th> Ngày test </th>
   </thead>
  	<tbody>
      <td>1</td>
      <td>Đăng nhập admin</td>
       <td>-Bước 1: Vào trang chủ <br>
           -Bước 2: Chọn Admin -> đăng nhập.<br>
           -Bước 3: Đăng nhập với Username/Password là admim/admin
      </td>
      <td> -Bước 1: Truy cập vào trang chủ thành công <br>
           -Bước 2: Truy cập vào đăng nhập thành công.<br>
           -Bước 3: Đăng nhập thành công
      </td>
      <td> Thành công</td>
      <td> 06/7/2022</td>
    </tbody>
  

</table>

<h3> Chức năng thêm Lý do công tác </h3>
<table>
	<thead>
      <th> STT </th>
      <th> Miêu tả test case </th>
      <th> Các bước kiểm thử </th>
      <th> Kết quả mong đợi </th>
      <th> Kết quả đạt được </th>
      <th> Ngày test </th>
   </thead>
  	<tbody>
      <td>1</td>
      <td>Thêm mới Lý do công tác</td>
       <td>-Bước 1: Vào trang chủ <br>
           -Bước 2:Chọn Chỉ đạo tuyến-> Lý do công tác -> Nhập dữ liệu: <br>
         		+ Mã Lý do<br>
         		+ Tên Lý Do<br>
         		+ Thứ tự sắp xếp<br>
           -Bước 3: Nhấp nút "Lưu" để lưu thông tin Lý do công tác vào cơ sở dữ liệu /"Hủy" để hủy thêm mới Lý do công tác
      </td>
      <td> -Bước 1: Truy cập vào trang chủ thành công <br>
           -Bước 2: Vào được danh mục Lý do công tác và thực hiện được các tác vụ.<br>
           -Bước 3: Thực hiện thành công thao tác và quay lại trang Lý do công tác
      </td>
      <td> Thành công</td>
      <td> 06/7/2022</td>
    </tbody>
  

</table>
<br>

<h3> Chức năng sửa Lý do công tác </h3>
<table>
	<thead>
      <th> STT </th>
      <th> Miêu tả test case </th>
      <th> Các bước kiểm thử </th>
      <th> Kết quả mong đợi </th>
      <th> Kết quả đạt được </th>
      <th> Ngày test </th>
   </thead>
  	<tbody>
      <td>1</td>
      <td>Thêm mới Lý do công tác</td>
       <td>-Bước 1: Vào trang chủ <br>
           -Bước 2:Chọn Chỉ đạo tuyến-> Lý do công tác -> Chọn vào dữ liệu muốn chỉnh sửa-> Thay đổi: <br>
         		+ Mã Lý do<br>
         		+ Tên Lý Do<br>
         		+ Thứ tự sắp xếp<br>
           -Bước 3: Nhấp nút "Sửa" để lưu thông tin Lý do công tác đã được thay đổi vào cơ sở dữ liệu /"Hủy" để hủy sửa Lý do công tác
      </td>
      <td> -Bước 1: Truy cập vào trang chủ thành công <br>
           -Bước 2: Vào được danh mục Lý do công tác và thực hiện được các tác vụ.<br>
           -Bước 3: Thực hiện thành công thao tác và quay lại trang Lý do công tác
      </td>
      <td> Thành công</td>
      <td> 06/7/2022</td>
    </tbody>
  

</table>
<br>

<h3> Chức năng xóa Lý do công tác </h3>
<table>
	<thead>
      <th> STT </th>
      <th> Miêu tả test case </th>
      <th> Các bước kiểm thử </th>
      <th> Kết quả mong đợi </th>
      <th> Kết quả đạt được </th>
      <th> Ngày test </th>
   </thead>
  	<tbody>
      <td>1</td>
      <td>Thêm mới Lý do công tác</td>
       <td>-Bước 1: Vào trang chủ <br>
           -Bước 2:Chọn Chỉ đạo tuyến-> Lý do công tác -> Chọn vào dữ liệu muốn xóa> Thay đổi: <br>
           -Bước 3: Nhấp nút "Xóa" để xóa thông tin Lý do công tác ra khỏi cơ sở dữ liệu /"Hủy" để hủy xóa Lý do công tác
      </td>
      <td> -Bước 1: Truy cập vào trang chủ thành công <br>
           -Bước 2: Vào được danh mục Lý do công tác và thực hiện được các tác vụ.<br>
           -Bước 3: Thực hiện thành công thao tác và quay lại trang Lý do công tác
      </td>
      <td> Thành công</td>
      <td> 06/7/2022</td>
    </tbody>
  

</table>
<br>
