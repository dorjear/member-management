package com.dorjear.training.loyalty.member.repository;

import com.dorjear.training.loyalty.member.entity.ProgramEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProgramRepository extends CrudRepository<ProgramEntity, String> {

  List<ProgramEntity> findAll();

  List<ProgramEntity> findByProgramIdIn(List<String> programIds);
}
