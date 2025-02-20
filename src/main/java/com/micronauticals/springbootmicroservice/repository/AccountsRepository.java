package com.micronauticals.springbootmicroservice.repository;
import com.micronauticals.springbootmicroservice.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
