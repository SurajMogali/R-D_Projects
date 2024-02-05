package com.ratelimit.rateLimit.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers,String> {
}
