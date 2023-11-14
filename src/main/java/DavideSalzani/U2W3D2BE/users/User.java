package DavideSalzani.U2W3D2BE.users;

import DavideSalzani.U2W3D2BE.devices.Device;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
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
    @JsonIgnore
    private String password;
    private String avatarUrl;
    @CreationTimestamp
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private Role ruolo;
}
