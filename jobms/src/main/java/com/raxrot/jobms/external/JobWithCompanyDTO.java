package com.raxrot.jobms.external;

import com.raxrot.jobms.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
