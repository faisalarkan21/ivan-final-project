package com.bcaf.ivan.FinalProject.Controller;

import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.Trip;
import com.bcaf.ivan.FinalProject.Entity.User;
import com.bcaf.ivan.FinalProject.Util.AgencyDao;
import com.bcaf.ivan.FinalProject.Util.BusDao;
import com.bcaf.ivan.FinalProject.Util.TripDao;
import com.bcaf.ivan.FinalProject.Util.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api")
public class TripApiController {

    @Autowired
    private BusDao busDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private TripDao tripDao;

    @PostMapping("/addTrip")
    public HttpStatus addBus(@RequestBody Trip trip) throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userDto = userDao.findEmailValidation(auth.getName());
        Agency agencyDto = agencyDao.findByUserId(userDto.getId());
        Bus bus = busDao.findByBusAgencyId(agencyDto.getId());
        System.out.println("userDto " +agencyDto.getId());
        trip.setAgencyId(agencyDto.getId());
        trip.setBusId(bus.getId());
        trip.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        tripDao.save(trip);
        return HttpStatus.OK;
    }

}
