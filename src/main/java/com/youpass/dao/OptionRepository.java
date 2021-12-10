package com.youpass.dao;

import com.youpass.pojo.Option;
import com.youpass.pojo.pk.OptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, OptionId> {
}
