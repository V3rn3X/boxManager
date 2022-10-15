package com.outpost.box;

import com.outpost.parcelLocker.ParcelLocker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Box {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull(message = "Size Box cannot be empty")
    @Enumerated(EnumType.STRING)
    private SizeBox sizeBox;

    @NotNull(message = "Capacity cannot be empty")
    @Min(value = 0)
    @Max(value = 999)
    private Double weight;

    @NotNull(message = "Name cannot be empty")
    @Pattern(regexp = "^([A-Z][a-z]{1,19}) ([A-Z][a-z]{1,19})$", message = "Name LastName")
    private String sender;

    @NotNull(message = "Name cannot be empty")
    @Pattern(regexp = "^([A-Z][a-z]{1,19}) ([A-Z][a-z]{1,19})$", message = "Name LastName")
    private String recipient;

    @ManyToOne
    @JoinColumn(name = "sender_parcel_locker_id")
    @NotNull(message = "Choose correct parcel locker")
    private ParcelLocker senderParcelLocker;

    @ManyToOne
    @JoinColumn(name = "recipient_parcel_locker_id")
    @NotNull(message = "Choose correct parcel locker")
    private ParcelLocker recipientParcelLocker;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
