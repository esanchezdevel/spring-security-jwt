package esanchez.devel.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esanchez.devel.jwt.model.Coupon;
import esanchez.devel.jwt.repository.CouponRepository;

@RestController
@RequestMapping("/couponapi")
public class CouponController {

	@Autowired
	private CouponRepository couponRepository;
	
	@PostMapping("/coupons")
	public Coupon create(@RequestBody Coupon coupon) {
		return couponRepository.save(coupon);
	}
	
	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable String code) {
		return couponRepository.findByCode(code);
	}
}
