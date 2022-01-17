package com.omerozturk.fourthhomework.dbt.api.controller;



import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtSaveRequestDto;
import com.omerozturk.fourthhomework.dbt.service.abstracts.DbtDebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/debts")
@CrossOrigin
@RequiredArgsConstructor
public class DbtDebtController {

    private final DbtDebtService dbtDebtService;

    @GetMapping
    public ResponseEntity getAll(){
        var result = dbtDebtService.findAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        var result =  dbtDebtService.findById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DbtDebtSaveRequestDto dbtDebtSaveRequestDto){
        var result = dbtDebtService.save(dbtDebtSaveRequestDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var result = dbtDebtService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/date-range/{startDate}/{endDate}")
    public ResponseEntity getByDateRange(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        var result =  dbtDebtService.getByDateRange(startDate,endDate);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity getByUserId(@PathVariable Long id){
        var result =  dbtDebtService.findByUserId(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/{id}/overdue-payable")
    public ResponseEntity findOverdueDebtByUser(@PathVariable Long id){
        var result =  dbtDebtService.findOverdueDebtByUser(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/{id}/total-debt")
    public ResponseEntity findTotalDebtByUser(@PathVariable Long id){
        var result =  dbtDebtService.findTotalDebtByUser(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/{id}/total-overdue-debt")
    public ResponseEntity findTotalOverdueDebtByUser(@PathVariable Long id){
        var result =  dbtDebtService.findTotalOverdueDebtByUser(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/{id}/late-fee-amount")
    public ResponseEntity findLateFeeAmountByUser(@PathVariable Long id){
        var result =  dbtDebtService.findLateFeeAmountByUser(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


}
