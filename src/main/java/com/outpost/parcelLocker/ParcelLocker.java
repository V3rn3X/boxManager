package com.outpost.parcelLocker;

import com.outpost.box.Box;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelLocker {

    @Id
    @Pattern(regexp = "^([0-9]{3}[A-Z]{3})$", message = "example 000KAT")
    private String id;

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 20, message = "Name is too short or long")
    private String name;

    @NotNull(message = "Street cannot be empty")
    @Size(min = 1, max = 30, message = "Street is too short or long")
    private String street;

    @NotNull(message = "City cannot be empty")
    @Size(min = 1, max = 30, message = "City is too short or long")
    private String city;

    @NotNull(message = "Post code cannot be empty")
    @Pattern(regexp = "^([0-9]{2})-([0-9]{3})$", message = "example 00-000")
    private String postCode;

    @NotNull(message = "Capacity cannot be empty")
    @Min(value = 0)
    @Max(value = 999)
    private Integer smallBoxCapacity;

    @NotNull(message = "Capacity cannot be empty")
    @Min(value = 0)
    @Max(value = 999)
    private Integer mediumBoxCapacity;

    @NotNull(message = "Capacity cannot be empty")
    @Min(value = 0)
    @Max(value = 999)
    private Integer bigBoxCapacity;

    @NotNull(message = "Status cannot be empty")
    private Integer smallBoxStatus = 0;

    @NotNull(message = "Status cannot be empty")
    private Integer mediumBoxStatus = 0;

    @NotNull(message = "Status cannot be empty")
    private Integer bigBoxStatus = 0;

    @OneToMany(mappedBy = "senderParcelLocker")
    private List<Box> senderBoxes;

    @OneToMany(mappedBy = "recipientParcelLocker")
    private List<Box> recipientBoxes;





}
