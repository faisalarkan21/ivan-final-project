package com.bcaf.ivan.FinalProject.Controller;


import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.User;
import com.bcaf.ivan.FinalProject.Request.RegisterRequest;
import com.bcaf.ivan.FinalProject.Service.UserService;
import com.bcaf.ivan.FinalProject.Util.AgencyDao;
import com.bcaf.ivan.FinalProject.Util.BusDao;
import com.bcaf.ivan.FinalProject.Util.RoleDao;
import com.bcaf.ivan.FinalProject.Util.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BusApiController {

    @Autowired
    private BusDao busDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AgencyDao AgencyDao;


    @GetMapping("/getAllBus")
    public String getAllBus() throws JsonProcessingException {
        List<Bus> listBus = busDao.findAll();
        if (listBus == null)
            listBus = new LinkedList<>();
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listBus);
        return rs;
    }

    @PostMapping("/updateBus")
    public String updateBus(@RequestBody List<Bus> listBus) throws JsonProcessingException {
        for (Bus b : listBus) {
            b.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            busDao.save(b);
        }
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listBus);
        return rs;
    }

    @PostMapping("/addBus")
    public HttpStatus addBus(@RequestBody Bus bus) throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userDto = userDao.findEmailValidation(auth.getName());
        Agency agencyDto = AgencyDao.findByUserId(userDto.getId());
        System.out.println("userDto " +agencyDto.getId());
        bus.setAgencyId(agencyDto.getId());
        bus.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        busDao.save(bus);
        return HttpStatus.OK;
    }

    @PostMapping("/deleteBus")
    public String deleteBus(@RequestBody List<Bus> listBus) throws JsonProcessingException {
        for (Bus b : listBus) {
            busDao.delete(b);
        }
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listBus);
        return rs;
    }
}
