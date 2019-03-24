package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.Userss;

import java.util.List;
import java.util.Optional;

public interface UserssRepository {
	List<Userss> findAll();
	Userss save(Userss userss);
	void remove(Userss userss);
	Optional<Userss> findById(int id);
}
