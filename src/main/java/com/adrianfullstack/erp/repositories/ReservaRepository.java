/**
 * 
 */
package com.adrianfullstack.erp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adrianfullstack.erp.models.Reserva;

/**
 * @author AdrianFullStack
 *
 */
public interface ReservaRepository extends JpaRepository<Reserva, String> {
	@Query(value="Select r from Reserva r where r.fechaIngresoRes = :fechaInicio and r.fechaSalidaRes = :fechaSalida", nativeQuery = true)
	public List<Reserva> find(@Param("fechaInicio") Date fechaInicio, @Param("fechaSalida") Date fechaSalida);
}
