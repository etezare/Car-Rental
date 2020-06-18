package edu.miu.cs425.eCarRental.utility;

import edu.miu.cs425.eCarRental.utility.validators.ConsistentDateParameters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PeriodRequested {
	
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate start;


	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate end;

	@ConsistentDateParameters

	public PeriodRequested(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
	}
	
	public PeriodRequested() {}

	public LocalDate getStart() {
		return start;
	}
	@ConsistentDateParameters
	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}
	@ConsistentDateParameters
	public void setEnd(LocalDate end) {
		this.end = end;
	}

}
