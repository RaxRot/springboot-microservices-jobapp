package com.raxrot.jobms.external;

import com.raxrot.jobms.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobFullDTO {
    private Job job;
    private Company company;
    private List<Review> review;
}
