package com.example.realtimemessaging.component;

import com.example.realtimemessaging.entity.Role;
import com.example.realtimemessaging.enums.PermissionEnum;
import com.example.realtimemessaging.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Override
    public void run(String... args) throws Exception {
        if (ddlAuto.equals("create")) {
            Role role = new Role();
            role.setName("USER");
            role.setPermissions(List.of(
                    PermissionEnum.ADD_MESSAGE,
                    PermissionEnum.DELETE_OWN_MESSAGE,
                    PermissionEnum.EDIT_OWN_MESSAGE,
                    PermissionEnum.GET_ALL_MESSAGES,
                    PermissionEnum.GET_USER)
            );
            roleRepository.save(role);

            Role role2 = new Role();
            role2.setName("ADMIN");
            role2.setPermissions(Arrays.stream(PermissionEnum.values()).toList());
            roleRepository.save(role2);
        }
    }
}
