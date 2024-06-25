package com.shopnow.user.config;

import com.shopnow.user.model.RoleEnum;
import com.shopnow.user.model.entity.PermissionEntity;
import com.shopnow.user.model.entity.RoleEntity;
import com.shopnow.user.model.entity.UserEntity;
import com.shopnow.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class PreloadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            /**
             * Create PERMISSIONS
             */
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            PermissionEntity supervisionPermission = PermissionEntity.builder()
                    .name("SUPERVISION")
                    .build();

            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            /**
             * Create ROLES
             */
            RoleEntity adminRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, supervisionPermission))
                    .build();

            RoleEntity vendorRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.VENDOR)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission))
                    .build();

            RoleEntity customerRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.CUSTOMER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission))
                    .build();

            RoleEntity guestRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.GUEST)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity developerRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, supervisionPermission, refactorPermission))
                    .build();

            /**
             * Create USERS
             */
            UserEntity developerUser = UserEntity.builder()
                    .name("Cristian Lavilla")
                    .email("clavilla@gmail.com")
                    .password("$2a$10$jkLpa1BV9mKESboj0N8MQef85BGRg4zpsFaZNC5KAXC9282HU5Lc6")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(developerRole))
                    .build();

            userRepository.save(developerUser);

        };
    }
}
