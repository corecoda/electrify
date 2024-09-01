package com.corecoda.ikollect.entities;


import com.corecoda.ikollect.entities.common.BaseEntity;
import com.corecoda.ikollect.entities.common.enums.AccountStatus;
import com.corecoda.ikollect.entities.common.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SystemAdmin extends BaseEntity implements UserDetails {

    @Column(
            name = "FirstName",
            length = 35
    )
    private String firstName;
    @Column(
            name = "LastName",
            length = 35
    )
    private String lastName;
    @Column(
            name = "Username",
            length = 35
    )
    private String username;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    private String authorizationPin;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String disableReason;

    private Integer retryCount = 0;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(accountType.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return authorizationPin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
