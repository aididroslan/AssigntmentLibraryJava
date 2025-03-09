package aidid.library.service.impl;

import aidid.library.model.Borrower;
import aidid.library.repo.BorrowerRepository;
import aidid.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerServiceImpl implements BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }
}