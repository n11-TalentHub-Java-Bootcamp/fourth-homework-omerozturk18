package com.omerozturk.fourthhomework.pym.api.controller;



import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentSaveRequestDto;

import com.omerozturk.fourthhomework.pym.services.abstracts.PymPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin
@RequiredArgsConstructor
public class PymPaymentController {

    private final PymPaymentService pymPaymentService;

    @GetMapping
    public ResponseEntity getAll(){
        var result = pymPaymentService.findAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        var result =  pymPaymentService.findById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PymPaymentSaveRequestDto pymPaymentSaveRequestDto){
        var result = pymPaymentService.save(pymPaymentSaveRequestDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        var result = pymPaymentService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/date-range/{startDate}/{endDate}")
    public ResponseEntity getByDateRange(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        var result =  pymPaymentService.getByDateRange(startDate,endDate);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity getByUserId(@PathVariable Long id){
        var result =  pymPaymentService.findByUserId(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/{id}/total-paid-late")
    public ResponseEntity findTotalPaidLateFeeAmountByUser(@PathVariable Long id){
        var result =  pymPaymentService.findTotalPaidLateFeeAmountByUser(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping("/{id}/paid-late-fees")
    public ResponseEntity findPaidLateFeesByUser(@PathVariable Long id){
        var result =  pymPaymentService.findPaidLateFeesByUser(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
