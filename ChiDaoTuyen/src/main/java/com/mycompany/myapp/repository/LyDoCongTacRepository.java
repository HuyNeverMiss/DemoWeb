package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LyDoCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LyDoCongTac entity.
 */
@SuppressWarnings("unused")/*Vô hiệu hóa một số cảnh báo của trình biên dịch các biến cục bộ hoặc phương thức riêng không được sử dụng */
@Repository
public interface LyDoCongTacRepository
    extends
        JpaRepository<LyDoCongTac, Long> {}/*Giao diện LyDoCongTacRepository: khai bao thư viện mở rộng JpaRepository với miền là LyDoCongTac và kiểu dử liệu của id là long */
