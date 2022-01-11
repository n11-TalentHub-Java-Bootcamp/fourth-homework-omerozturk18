package com.omerozturk.fourthhomework.usr.api.controller;


import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserDto;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserSaveRequestDto;
import com.omerozturk.fourthhomework.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UsrUserController {

    private final UsrUserService usrUserService;

    @GetMapping
    public ResponseEntity getAll(){

        List<UsrUserDto> usrUserDtoList = usrUserService.findAll();

        return ResponseEntity.ok(usrUserDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){

        UsrUserDto usrUserDto = usrUserService.findById(id);

        return ResponseEntity.ok(usrUserDto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity getByUsername(@PathVariable String username){

        UsrUserDto usrUserDto = usrUserService.findByUsername(username);

        return ResponseEntity.ok(usrUserDto);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UsrUserSaveRequestDto usrUserSaveRequestDto){

        UsrUserDto usrUserDto = usrUserService.save(usrUserSaveRequestDto);

        return ResponseEntity.ok(usrUserDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        usrUserService.delete(id);
    }
}
