package com.john.coupons.tasks;

import com.john.coupons.dto.Coupon;
import com.john.coupons.service.CouponsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TimerTask;

@Component
@EnableScheduling
public class DeleteExpiredCouponsTimerTask extends TimerTask {

    private final CouponsService couponsService;

    @Autowired
    public DeleteExpiredCouponsTimerTask(CouponsService couponsService) {
        this.couponsService = couponsService;
    }

    @SneakyThrows
    @Override
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void run() {
        System.out.println("Running to check if there is expired coupons to delete");
        List<Coupon> couponList = couponsService.getAllExpiredCoupons();
        for (Coupon coupon : couponList) {
            couponsService.deleteCoupon(coupon.getId());
        }

    }


}



