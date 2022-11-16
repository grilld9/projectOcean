package com.oceanapplication.ocean.repo;

import com.oceanapplication.ocean.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
