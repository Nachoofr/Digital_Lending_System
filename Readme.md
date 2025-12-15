### Code Practices:
1. rename project from DigitalLendingSystem to dital-lending-system.
2. Create separate constant with static final cosntants for api endpoints constants.
   @PostMapping("/api/banks")

3. Use validators in dto level with appropriate error messages.
   eg: BankDto

4. Implement proper logging in service layer and business logic outputds for tracing error and datas.
eg: SL4J

5. Maintain proper line spacing and line brakes with standard formats.
   @PutMapping("api/bankUsers/{id}")
   public ResponseEntity<BankUserDto> updateBankUser(
   @PathVariable long id,
   @RequestBody BankUserDto bankUserDto
   ){
   return bankUserService.updateBankUser(id, bankUserDto);
   }

6. Calling twice may hamper code performance,for better reusability use,fetching entity or optional for proper checking and exception handlings.
   public ResponseEntity<BankUserDto> getBankUserById(long id){
   if (bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id) == null) {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
   return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.OK);

   }

7. Use Optional for checking result and validating properly during update.
   public ResponseEntity<BankUserDto> updateBankUser(long id, BankUserDto bankUserDto) {
   if (bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id) == null) {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   var bankUser = bankUserRepo.findByIdAndIsActiveTrueAndBankIdIsActiveTrue(id);
   bankUserMapper.update(bankUserDto, bankUser);
   bankUser.setActive(true);
   bankUserRepo.save(bankUser);
   return new ResponseEntity<>(bankUserMapper.toDto(bankUser), HttpStatus.OK);
   }

8. Use separate setter class or mapper utils for setting data in service layer.
9. Create Calculation functions in separate utils for specially string manipulation,mathematics calculations.(utils layer)
10. Create separate request and response packaging appropriately.

11. Create proper packing with impl inside service layer.
12. Implement proper data fetching from repo.ie:
   public List<LoanApplicationDto> getAllLoanApplication(String status, long bankId, long customerId){
      var loanApplication = loanApplicationRepo.findAll();
Fetch only required client or entity wise data. 
 donot fetch all data,it will affect the performance.

13. Use log4j instead of sout.
    System.out.println("Total Disbursement Amount: " + totalDisbursementAmount);
    System.out.println("Total Repaid Amount: " + totalRepaidAmount);
    System.out.println("Interest: " + interest);
    System.out.println("Needed Amount: " + neededAmount);
    System.out.println("Total Outstanding Amount: " + totalOutstandingAmount);
    System.out.println(loanRepaymentDto.getAmountPaid() - totalOutstandingAmount);

14. Use swagger for api documentation.