package com.john.coupons.tasks;

import com.john.coupons.dto.Coupon;
import com.john.coupons.enums.ErrorType;
import com.john.coupons.exceptions.ApplicationException;
import com.john.coupons.service.CouponsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class DeleteExpiredCouponsTimerTask extends TimerTask {

    private final CouponsService couponsService;

    @Autowired
    public DeleteExpiredCouponsTimerTask(CouponsService couponsService) {
        this.couponsService = couponsService;
    }

    @PostConstruct
    public void init() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        Timer timer = new Timer();
        timer.schedule(this, date.getTime());
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Running to check if there is expired coupons to delete");
        try {
            List<Coupon> couponList = couponsService.getAllExpiredCoupons();
            for (Coupon coupon: couponList) {
                couponsService.deleteCoupon(coupon.getId());
            }

        } catch (ApplicationException e) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR);
        }
    }
}


