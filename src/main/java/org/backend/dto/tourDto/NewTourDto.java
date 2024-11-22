package org.backend.dto.tourDto;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTourDto {
    @NotBlank(message = "Tour title should not be empty")
    private String title;

    @NotBlank (message = "Tour description should not be empty")
    private String description;

    @NotBlank (message = "Tour price should not be empty")
    private Long price;

    @NotBlank (message = "Tour duration should not be empty")
    private Long duration;

    @NotBlank (message = "Tour startDate should not be empty")
    private LocalDate startDate;

    @NotBlank (message = "Tour endDate should not be empty")
    private LocalDate endDate;

    @NotBlank (message = "Tour country should not be empty")
    private String country;

    @NotBlank (message = "Tour city should not be empty")
    private String city;

    private String imageUrl;
}



