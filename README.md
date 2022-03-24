# LAB211_TuanVM_Spring2022

* Một số kinh nghiệm trước khi học LAB211 TuanVM 
  - Trước khi bắt tay việc code phải tạo khung chương trình (hàm main) trước rồi mới từ hàm main mới code những phần khác. Phần này thể hiện toàn bộ logic của chương trình và sẽ luôn bị thầy hỏi mỗi lần lên check bài.
  - Những cái nên code ở hàm main: khai báo biến, gọi hàm và tuyệt đối không được code ở hàm main ngoại trừ những bài toàn liên quan quản lý thì có thể code phần điều hướng.
  - Không được sử dụng biến toàn cục (trừ trường hợp có lý do chính đáng để sử dụng sẽ được cho qua)
  - Phải comment cho phần loop, regex, và condition. Trong đó phần regex nên mô tả rõ từng regex một có ý nghĩa và cách sử dụng như nào (như [a-z]: input sẽ khớp các ký tự từ a cho đến z)
  - Phần đặt tên biến, hàm và class rất quan trọng. Đối với tên biến nên đặt rõ để có thể mô tả rõ biến này dùng để làm gì. Đối với tên hàm phải được đặt theo kiểu [DoSomething] thể hiện hàm dùng để làm gì. Với tên Class thì phải được đặt để dại diện cho các hàm bên trong class.


* Các bước khi học LAB211 TuanVM
- Gồm 4 bước: Code, Test, Check, Review
- Review: xem có phải đấy là code của mình ko
- 3 bước còn lại là code ra 1 chương trình và có thầy hỗ trợ trong quá trình đấy
- Bước code là bước tự làm
- Sau đó test, test đúng rồi thì chuyển qua bước check
- Tại bước check: giúp các bạn chưa có kinh nghiệm và chưa làm bao h, là bước tự hỏi: tự quyết định cái gì đó, tự đặt cho mình câu hỏi đấy, và đưa ra quyết định làm như nào.
- Code xong thì phải test, nếu có lỗi thì sửa, sửa xong thì test lại.
**Thầy hỗ trợ cái gì ? , thầy sẽ không code hộ, thầy ko test hộ, thầy hướng dẫn.**
**VD:** Lỗi thuộc từ dạng từ a → z, thì sẽ ko làm giúp, mà thầy định hướng giúp mình tìm ra lỗi.


### **Tự test là gì ? Làm gì để test ?**

**Expect:** Kết quả mong muốn → Từ testcase, đề bài, 1 phần do chúng ta phân tích

**Result**: Kết quả thực tế

**Thực hiện việc test**: tiến hành compare 2 cái này với nhau còn khác nhau thì là toang !!!

**Trường hợp được xem là không test:**

- Không xem expect
- Không thèm nhìn vào result
- Không tiến hành so sánh expect và result thực tế

**Chú ý: Xong bước nào test luôn bước đấy.**


### Cái chúng ta muốn kiểm tra thêm, ảnh hưởng thế nào đến chương trình của chúng ta ?

Nếu ảnh hưởng là có thì chắc chắn phải test và ngược lại.

Ví dụ: Nhập mảng có N phần tử thì chúng ta phải check nó ≥ 0 không phải vì nó đúng. Không phải thế, mà là nếu để cho cái mảng đấy mà bằng 0 thì chắc chắn sai vì không có cái mảng nào chứa  số  lượng âm phần tử

Với trường hợp input nhập vào là bình thường.

### Với trường hợp input nhập vào là ngày sinh thì sao ?

Ví dụ: 7-15-2002 

Phải tùy thuộc vào format, nma nếu đề bài ko yêu cầu, thì phải tự tạo một format bắt người dùng nhập theo format đó

Sau khi kiểm tra format thì xem ngày tháng đấy có tồn tại hay không

Sau đó phải kiểm tra xem cái date đấy có tương lai không 

### Làm thế nào từ một requirement → code tương ứng. Step by step.

Code bắt đầu từ đâu:

Dựng ra khung chương trình, code đi theo khung chương trình đấy. Khung chương trình viết ra phải đáp ứng requirement.

Từ khung chương trình đấy tạo ra code 

Đặt khung chương trình trong hàm chính

### **Hàm chính là gì ?**

Phải làm và hoàn thiện đầu tiên.

Các bước dựng lên hàm chính:

//Step 1: InputSizeOfArray (Tại sao lại dựng bước này? Bước này để làm gì)
  int size = InputSize()
  
//Step 2: GenArray
  int[] arr = GenArray(size) (Ouput của step trước sẽ là input của step này. Hãy nhớ rõ điều này)
  
//Step 3: Display
  DisplayArr(arr)
  
  Sau khi code xong hàm chính thì hãy đi code từng bước một cho đến khi đến bước cuối cùng. Cách để kiểm tra tên có đúng thì phải xem cái output mình vừa làm đấy có giống với chức năng tên hàm không.
  
  => Hãy nhớ là tên phải đúng với chức năng không là đi bụi đó
  
  Như đã đề cập về cách viết hàm main thì có ba phần chính sau
  Main(chính)

  1. Call func (tự viết)
  2. Khai báo biến
  3. Code điều hướng (menu)

Đến đây là hết rùi. Chúc mọi người pass LAB211 nhé :>
