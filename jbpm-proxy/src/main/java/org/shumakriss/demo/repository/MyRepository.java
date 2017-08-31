package org.shumakriss.demo.repository;

import org.shumakriss.demo.data.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chris on 9/4/16.
 */
@Transactional(readOnly = true)
public interface MyRepository extends JpaRepository<MyEntity, Long> {
    List<MyEntity> findByName(String name);
    List<MyEntity> findByNameAndSecondName(String name, String secondName);
    List<MyEntity> findByNameOrSecondName(String name, String secondName);
    List<MyEntity> findByNameAndSecondNameOrThirdName(String name, String secondName, String thirdName);
}
