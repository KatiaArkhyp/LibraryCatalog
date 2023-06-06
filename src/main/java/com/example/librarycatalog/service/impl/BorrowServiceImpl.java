package com.example.librarycatalog.service.impl;

import com.example.librarycatalog.models.Book;
import com.example.librarycatalog.models.BookStatus;
import com.example.librarycatalog.models.Borrow;
import com.example.librarycatalog.models.UserWithRole;
import com.example.librarycatalog.repository.BorrowRepository;
import com.example.librarycatalog.service.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository;

    @Override
    public Borrow getBorrowById(long id) {
        return borrowRepository.getById(id);
    }

    @Override
    public List<Borrow> getBorrowsByUser(UserWithRole user) {
        return borrowRepository.findAll().stream()
                .filter(borrow -> borrow.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void saveBorrow(Borrow borrow) {
        borrowRepository.save(borrow);
    }

    @Override
    public List<Borrow> getAllBorrowedBooks() {
        return borrowRepository.findAll();
    }

    @Override
    public void returnBook(Long borrowId) {
        borrowRepository.deleteById(borrowId);
    }
}
