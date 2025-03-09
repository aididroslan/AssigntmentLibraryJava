package aidid.library.controller;

import aidid.library.model.Borrower;
import aidid.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @PostMapping
    public Borrower registerBorrower(@RequestBody Borrower borrower) {
        return borrowerService.registerBorrower(borrower);
    }
}