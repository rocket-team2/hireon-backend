package com.hireon.backend.Repository;
import com.hireon.backend.Model.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriveRepo extends JpaRepository<Drive,Integer> {

}
