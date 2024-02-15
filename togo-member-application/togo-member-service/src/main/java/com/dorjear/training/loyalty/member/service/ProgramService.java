package com.dorjear.training.loyalty.member.service;

import com.dorjear.training.loyalty.member.repository.ProgramRepository;
import com.dorjear.training.loyalty.member.entity.ProgramEntity;
import com.dorjear.training.loyalty.member.model.Program;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProgramService {

  private final ProgramRepository programRepository;

  @Autowired
  public ProgramService(final ProgramRepository programRepository) {
    this.programRepository = programRepository;
  }

  public List<Program> getPrograms() {

    log.info("Finding all programs");

    final List<ProgramEntity> foundPrograms = programRepository.findAll();

    log.info("Found '{}' programs", foundPrograms.size());

    return foundPrograms.stream()
        .map(this::programEntityToProgram)
        .toList();
  }

  public Optional<Program> getProgram(final String programId) {

    log.info("Finding a program by id '{}'", programId);

    final Optional<ProgramEntity> optionalFoundProgramEntity = programRepository.findById(programId);

    if (optionalFoundProgramEntity.isEmpty()) {
      return Optional.empty();
    }

    log.info("Found a program by id '{}'", programId);

    final ProgramEntity foundProgramEntity = optionalFoundProgramEntity.get();

    return Optional.of(programEntityToProgram(foundProgramEntity));
  }

  private Program programEntityToProgram(final ProgramEntity programEntity) {
    return Program.builder()
      .programId(programEntity.getProgramId())
      .marketingName(programEntity.getProgramName())
      .summaryDescription(programEntity.getProgramDescription())
      .build();
  }
}
