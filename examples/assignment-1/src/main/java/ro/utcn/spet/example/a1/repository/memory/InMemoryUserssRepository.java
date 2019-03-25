package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.repository.UserssRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserssRepository implements UserssRepository {
	// we want to be thread-safe, because this class will be a singleton (just one instance)
	// in order to not use synchronized methods, we use thread-safe classes (ConcurrentHashMap and AtomicInteger)
	private final Map<Integer, Userss> data = new ConcurrentHashMap<>();
	private final AtomicInteger currentId = new AtomicInteger(0);

	@Override
	public Userss save(Userss student) {
		if (student.getId() == null) {
			student.setId(currentId.incrementAndGet());
		}
		data.put(student.getId(), student);
		return student;
	}

	@Override
	public void remove(Userss student) {
		data.remove(student.getId());
	}

	@Override
	public Optional<Userss> findById(int id) {
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public List<Userss> findAll() {
		return new ArrayList<>(data.values());
	}
}
