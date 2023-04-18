package com.example.demo.major.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.major.project.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query(value = "select b.id as id,s.slot_number as slotNo ,bi.building_number as buildingNo,b.status as status,b.booking_date as bookingdate"
			+ "  from slot s inner join booking b on s.id=b.slot_id join building bi on bi.id=s.building_id where b.user_id=:id order by b.booking_date", nativeQuery = true)
	List<Bookings> endUserResult(@Param("id") Integer id);
}
