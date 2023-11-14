package DavideSalzani.U2W3D2BE.users;

import DavideSalzani.U2W3D2BE.devices.Device;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "assignedTo")
    @Cascade(value = CascadeType.ALL)
    @JsonIgnore
    private List<Device> assignedCompanyDevices;
    private String password;
    private String avatarUrl;
}
