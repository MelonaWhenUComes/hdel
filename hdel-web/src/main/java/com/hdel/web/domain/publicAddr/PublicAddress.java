package com.hdel.web.domain.publicAddr;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TB_PUBLIC_ADDRESS")
@Entity
@Getter
public class PublicAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, name="PROJ_NO")
    private String projNo;
    @Column(nullable = true, name="HO_NO")
    private String hoNo;
    @Column(nullable = true, name="PUBLIC_ADDR")
    private String publicAddr;
    @Column(nullable = true, name="PUBLIC_LON")
    private double publicLon;
    @Column(nullable = true, name="PUBLIC_LAT")
    private double publicLat;
}
