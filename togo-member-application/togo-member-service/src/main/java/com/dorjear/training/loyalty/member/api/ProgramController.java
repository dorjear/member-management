package com.dorjear.training.loyalty.member.api;

import com.dorjear.training.loyalty.api.ResourceNotFoundException;
import com.dorjear.training.loyalty.member.service.ProgramService;
import com.dorjear.training.loyalty.member.model.Program;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramController implements ProgramApi {

  private final ProgramService programService;

  @Autowired
  ProgramController(final ProgramService programService) {
    this.programService = programService;
  }

  @Override
  public List<Program> getPrograms() {
    final List<Program> foundPrograms = programService.getPrograms();

    if (foundPrograms.isEmpty()) {
      throw new ResourceNotFoundException("No programs exist.");
    }

    return foundPrograms;
  }

  @Override
  public Program getProgram(@PathVariable final String programId) {

    final Optional<Program> foundProgram = programService.getProgram(programId);

    if (foundProgram.isEmpty()) {
      throw new ResourceNotFoundException("No program exists with programId=" + programId);
    }

    return foundProgram.get();
  }

}
