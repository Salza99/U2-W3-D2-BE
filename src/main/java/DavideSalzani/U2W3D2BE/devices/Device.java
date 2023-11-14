package DavideSalzani.U2W3D2BE.devices;

import DavideSalzani.U2W3D2BE.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeOfDevice type;
    @Enumerated(EnumType.STRING)
    private Conditions deviceStatus;
    @ManyToOne()
    @JoinColumn(name = "User_id")
    private User assignedTo;
}
