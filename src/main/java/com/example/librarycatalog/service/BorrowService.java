package com.example.librarycatalog.service;

import com.example.librarycatalog.models.Borrow;
import com.example.librarycatalog.models.UserWithRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BorrowService {

    Borrow getBorrowById(long id);

    List<Borrow> getBorrowsByUser(UserWithRole user);
    void saveBorrow(Borrow borrow);
}
